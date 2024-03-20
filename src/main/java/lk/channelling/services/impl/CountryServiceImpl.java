/*
 * Copyright 2024 Channelling.lk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package lk.channelling.services.impl;

import lk.channelling.entity.Country;
import lk.channelling.enums.Status;
import lk.channelling.exception.ObjectNotUniqueException;
import lk.channelling.exception.OldObjectException;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.CountryRepository;
import lk.channelling.resources.PageArray;
import lk.channelling.resources.PagingRequest;
import lk.channelling.services.CountryService;
import lk.channelling.util.TimeUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Transactional(rollbackFor = Exception.class)
@Log4j2
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;
    private static final String RECORD_NOT_FOUND = "No country record found for the %s : %s";

    @Autowired
    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        Optional<Country> country = countryRepository.findById(id);

        if (country.isEmpty()) throw new RecordNotFoundException(String.format(RECORD_NOT_FOUND, "id", id));

        return country.get();
    }

    @Override
    public Country findByCode(String code) {
        Optional<Country> country = countryRepository.findByCode(code);

        if (country.isEmpty()) throw new RecordNotFoundException(String.format(RECORD_NOT_FOUND, "code", code));

        return country.get();
    }

    @Override
    public List<Country> findByStatus(Status status) {
        return countryRepository.findByStatus(status);
    }

    @Override
    public Country save(Country country) {
        LoginAuthenticationHandler.validateUser();

        Optional<Country> fetchedCountry = countryRepository.findByCode(country.getCode());

        if (fetchedCountry.isPresent())
            throw new ObjectNotUniqueException("The entered country already exists in the database.");

        country.setStatus(Status.ACTIVE);
        country.setCreatedUser(LoginAuthenticationHandler.getUserName());
        country.setCreatedDate(TimeUtil.getCurrentTimeStamp());

        return countryRepository.saveAndFlush(country);
    }

    @Override
    public void delete(Long id) {
        Country fetchedCountry = findById(id);
        if (fetchedCountry == null) throw new RecordNotFoundException(String.format(RECORD_NOT_FOUND, "id", id));

        countryRepository.delete(fetchedCountry);
    }

    @Override
    public Country update(Long id, Country newCountry) {
        LoginAuthenticationHandler.validateUser();

        Optional<Country> updatedCountry = countryRepository.findById(id).map(country -> {
            if (country.getVersion().equals(newCountry.getVersion())) throw new OldObjectException();

            country.setDescription(newCountry.getDescription());
            country.setIsoCode(newCountry.getIsoCode());
            country.setStatus(newCountry.getStatus());
            country.setModifiedUser(LoginAuthenticationHandler.getUserName());
            country.setModifiedDate(TimeUtil.getCurrentTimeStamp());

            return countryRepository.save(country);
        });

        if (updatedCountry.isPresent()) return updatedCountry.get();
        throw new RecordNotFoundException(String.format(RECORD_NOT_FOUND, "id", id));
    }

    @Override
    public PageArray getData(PagingRequest pagingRequest) {
        long total = countryRepository.count();

        Pageable pageable = PageRequest.of(pagingRequest.getStart(), pagingRequest.getLength(), Sort.by("description").ascending());
        List<Country> filtered = countryRepository.findAll(pageable).stream().toList();

        PageArray pageArray = new PageArray();
        pageArray.setRecordsFiltered((int) total);
        pageArray.setRecordsTotal((int) total);
        pageArray.setDraw(pagingRequest.getDraw());
        pageArray.setData(filtered.stream().map(this::toStringList).toList());

        return pageArray;
    }

    private List<String> toStringList(Country country) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return Arrays.asList(country.getCode(),
                country.getDescription(),
                country.getIsoCode(),
                country.getStatus().toString(),
                sdf.format(country.getCreatedDate()),
                country.getCreatedUser());
    }


}


