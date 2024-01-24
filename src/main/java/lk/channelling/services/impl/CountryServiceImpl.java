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
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.CountryRepository;
import lk.channelling.services.CountryService;
import lk.channelling.util.TimeUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@Transactional(rollbackFor = Exception.class)
@Log4j2
public class CountryServiceImpl implements CountryService {

    private CountryRepository countryRepository;

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

        if (!country.isPresent()) throw new RecordNotFoundException("No country record found for the id : " + id);

        return country.get();
    }

    @Override
    public Country findByCode(String code) {
        Optional<Country> country = countryRepository.findByCode(code);

        if (!country.isPresent()) throw new RecordNotFoundException("No country record found for the code : " + code);

        return country.get();
    }

    @Override
    public List<Country> findByStatus(Status status) {
        return countryRepository.findByStatus(status);
    }

    @Override
    public Country save(Country country) {
        LoginAuthenticationHandler.validateUser();

        country.setCreatedUser(LoginAuthenticationHandler.getUserName());
        country.setStatus(Status.ACTIVE);
        country.setCreatedDate(TimeUtil.getCurrentTimeStamp());

        Optional<Country> fetchedCountry = countryRepository.findByCode(country.getCode());

        if (fetchedCountry.isPresent())
            throw new ObjectNotUniqueException("The entered country already exists in the database.");

        return countryRepository.saveAndFlush(country);
    }


    @Override
    public void delete(Long id) {
        Country fetchedCountry = findById(id);

        if (fetchedCountry == null) throw new RecordNotFoundException("No country record found for the id : " + id);

        countryRepository.delete(fetchedCountry);

    }

    @Override
    public Country update(Long id, Country newCountry) {
        LoginAuthenticationHandler.validateUser();

        Optional<Country> updatedCountry = countryRepository.findById(id).map(country -> {
            country.setDescription(newCountry.getDescription());
            country.setModifiedUser(LoginAuthenticationHandler.getUserName());
            country.setModifiedDate(TimeUtil.getCurrentTimeStamp());

            return countryRepository.save(country);
        });

        if (updatedCountry.isPresent()) return updatedCountry.get();
        throw new RecordNotFoundException("No country record found for the id : " + id);
    }
}
