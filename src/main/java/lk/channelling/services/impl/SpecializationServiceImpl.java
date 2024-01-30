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

import lk.channelling.entity.Specialization;
import lk.channelling.enums.Status;
import lk.channelling.exception.ObjectNotUniqueException;
import lk.channelling.exception.OldObjectException;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.SpecializationRepository;
import lk.channelling.services.SpecializationService;
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
public class SpecializationServiceImpl implements SpecializationService {

    private SpecializationRepository specializationRepository;

    @Autowired
    public void setSpecializationRepository(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Override
    public List<Specialization> findAll() {
        return specializationRepository.findAll();
    }

    @Override
    public Specialization findById(Long id) {
        Optional<Specialization> country = specializationRepository.findById(id);

        if (country.isEmpty()) throw new RecordNotFoundException("No contact method record found for the id : " + id);

        return country.get();
    }

    @Override
    public Specialization findByCode(String code) {
        Optional<Specialization> country = specializationRepository.findByCode(code);

        if (country.isEmpty())
            throw new RecordNotFoundException("No contact method record found for the code : " + code);

        return country.get();
    }

    @Override
    public List<Specialization> findByStatus(Status status) {
        return specializationRepository.findByStatus(status);
    }

    @Override
    public Specialization save(Specialization specialization) {
        LoginAuthenticationHandler.validateUser();
        Optional<Specialization> fetchedSpecialization = specializationRepository.findByCode(specialization.getCode());

        if (fetchedSpecialization.isPresent())
            throw new ObjectNotUniqueException("The entered contact method already exists in the database.");

        specialization.setCreatedUser(LoginAuthenticationHandler.getUserName());
        specialization.setStatus(Status.ACTIVE);
        specialization.setCreatedDate(TimeUtil.getCurrentTimeStamp());

        return specializationRepository.saveAndFlush(specialization);
    }

    @Override
    public void delete(Long id) {
        Specialization fetchedSpecialization = findById(id);

        if (fetchedSpecialization == null)
            throw new RecordNotFoundException("No contact method record found for the id : " + id);

        specializationRepository.delete(fetchedSpecialization);
    }

    @Override
    public Specialization update(Long id, Specialization newSpecialization) {
        LoginAuthenticationHandler.validateUser();

        Optional<Specialization> updatedSpecialization = specializationRepository.findById(id).map(cm -> {
            if (cm.getVersion() != newSpecialization.getVersion())
                throw new OldObjectException();

            cm.setDescription(newSpecialization.getDescription());
            cm.setStatus(newSpecialization.getStatus());
            cm.setModifiedUser(LoginAuthenticationHandler.getUserName());
            cm.setModifiedDate(TimeUtil.getCurrentTimeStamp());

            return specializationRepository.save(cm);
        });

        if (updatedSpecialization.isPresent()) return updatedSpecialization.get();
        throw new RecordNotFoundException("No contact method record found for the id : " + id);
    }
}
