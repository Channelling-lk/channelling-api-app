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
import lk.channelling.exception.ObjectNotUniqueException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.CountryRepository;
import lk.channelling.services.CountryService;
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
    public Country save(Country country) {
        LoginAuthenticationHandler.validateUser();

        country.setCreatedUser(LoginAuthenticationHandler.getUserName());

        Optional<Country> fetchedCountry = countryRepository.findByCode(country.getCode());

        if (fetchedCountry.isPresent())
            throw new ObjectNotUniqueException();

        return countryRepository.saveAndFlush(country);
    }
}
