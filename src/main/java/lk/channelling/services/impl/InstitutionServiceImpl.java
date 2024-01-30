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

import lk.channelling.entity.Institution;
import lk.channelling.enums.Status;
import lk.channelling.exception.ObjectNotUniqueException;
import lk.channelling.exception.OldObjectException;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.InstitutionRepository;
import lk.channelling.services.InstitutionService;
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
public class InstitutionServiceImpl implements InstitutionService {

    private InstitutionRepository institutionRepository;

    @Autowired
    public void setInstitutionRepository(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }

    @Override
    public Institution findById(Long id) {
        Optional<Institution> institution = institutionRepository.findById(id);

        if (institution.isEmpty()) throw new RecordNotFoundException("No institution record found for the id : " + id);

        return institution.get();
    }

    @Override
    public Institution findByCode(String code) {
        Optional<Institution> institution = institutionRepository.findByCode(code);

        if (institution.isEmpty())
            throw new RecordNotFoundException("No institution record found for the code : " + code);

        return institution.get();
    }

    @Override
    public List<Institution> findByStatus(Status status) {
        return institutionRepository.findByStatus(status);
    }

    @Override
    public Institution save(Institution institution) {
        LoginAuthenticationHandler.validateUser();

        Optional<Institution> fetchedInstitution = institutionRepository.findByCode(institution.getCode());

        if (fetchedInstitution.isPresent())
            throw new ObjectNotUniqueException("The entered institution already exists in the database.");

        institution.setCreatedUser(LoginAuthenticationHandler.getUserName());
        institution.setStatus(Status.ACTIVE);
        institution.setCreatedDate(TimeUtil.getCurrentTimeStamp());

        return institutionRepository.saveAndFlush(institution);
    }

    @Override
    public void delete(Long id) {
        Institution fetchedInstitution = findById(id);

        if (fetchedInstitution == null)
            throw new RecordNotFoundException("No institution record found for the id : " + id);

        institutionRepository.delete(fetchedInstitution);
    }

    @Override
    public Institution update(Long id, Institution newInstitution) {
        LoginAuthenticationHandler.validateUser();

        Optional<Institution> updatedInstitution = institutionRepository.findById(id).map(institution -> {
            if (institution.getVersion() != newInstitution.getVersion()) throw new OldObjectException();

            institution.setDescription(newInstitution.getDescription());
            institution.setStatus(newInstitution.getStatus());
            institution.setCountryId(newInstitution.getCountryId());
            institution.setModifiedUser(LoginAuthenticationHandler.getUserName());
            institution.setModifiedDate(TimeUtil.getCurrentTimeStamp());

            return institutionRepository.save(institution);
        });

        if (updatedInstitution.isPresent()) return updatedInstitution.get();
        throw new RecordNotFoundException("No institution record found for the id : " + id);
    }

    @Override
    public List<Institution> findByCountryId(Long institutionId) {
        return institutionRepository.findByCountryId(institutionId);
    }
}
