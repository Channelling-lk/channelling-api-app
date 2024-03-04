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

import lk.channelling.entity.City;
import lk.channelling.enums.Status;
import lk.channelling.exception.OldObjectException;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.CityRepository;
import lk.channelling.services.CityService;
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
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    @Autowired
    public void setCityRepository(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findById(Long id) {
        Optional<City> city = cityRepository.findById(id);

        if (city.isEmpty()) throw new RecordNotFoundException("No city record found for the id : " + id);

        return city.get();
    }

    @Override
    public List<City> findByStatus(Status status) {
        return cityRepository.findByStatus(status);
    }

    @Override
    public City save(City city) {
        LoginAuthenticationHandler.validateUser();

        city.setCreatedUser(LoginAuthenticationHandler.getUserName());
        city.setStatus(Status.ACTIVE);
        city.setCreatedDate(TimeUtil.getCurrentTimeStamp());

        return cityRepository.saveAndFlush(city);
    }

    @Override
    public void delete(Long id) {
        City fetchedInstitution = findById(id);

        if (fetchedInstitution == null) throw new RecordNotFoundException("No city record found for the id : " + id);

        cityRepository.delete(fetchedInstitution);
    }

    @Override
    public City update(Long id, City newCity) {
        LoginAuthenticationHandler.validateUser();

        Optional<City> updatedInstitution = cityRepository.findById(id).map(city -> {
            if (city.getVersion() != newCity.getVersion()) throw new OldObjectException();

            city.setDescription(newCity.getDescription());
            city.setStatus(newCity.getStatus());
            city.setStateId(newCity.getStateId());
            city.setModifiedUser(LoginAuthenticationHandler.getUserName());
            city.setModifiedDate(TimeUtil.getCurrentTimeStamp());

            return cityRepository.save(city);
        });

        if (updatedInstitution.isPresent()) return updatedInstitution.get();
        throw new RecordNotFoundException("No city record found for the id : " + id);
    }

    @Override
    public List<City> findByStateId(Long stateId) {
        return cityRepository.findByStateId(stateId);
    }
}
