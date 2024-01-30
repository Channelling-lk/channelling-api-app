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

import lk.channelling.entity.Qualification;
import lk.channelling.enums.Status;
import lk.channelling.exception.ObjectNotUniqueException;
import lk.channelling.exception.OldObjectException;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.QualificationRepository;
import lk.channelling.services.QualificationService;
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
public class QualificationServiceImpl implements QualificationService {

    private QualificationRepository qualificationRepository;

    @Autowired
    public void setQualificationRepository(QualificationRepository qualificationRepository) {
        this.qualificationRepository = qualificationRepository;
    }

    @Override
    public List<Qualification> findAll() {
        return qualificationRepository.findAll();
    }

    @Override
    public Qualification findById(Long id) {
        Optional<Qualification> qualification = qualificationRepository.findById(id);

        if (qualification.isEmpty())
            throw new RecordNotFoundException("No qualification Level record found for the id : " + id);

        return qualification.get();
    }

    @Override
    public Qualification findByCode(String code) {
        Optional<Qualification> qualification = qualificationRepository.findByCode(code);

        if (qualification.isEmpty())
            throw new RecordNotFoundException("No qualification Level record found for the code : " + code);

        return qualification.get();
    }

    @Override
    public List<Qualification> findByStatus(Status status) {
        return qualificationRepository.findByStatus(status);
    }

    @Override
    public Qualification save(Qualification qualification) {
        LoginAuthenticationHandler.validateUser();

        Optional<Qualification> fetchedQualification = qualificationRepository.findByCode(qualification.getCode());

        if (fetchedQualification.isPresent())
            throw new ObjectNotUniqueException("The entered qualification Level already exists in the database.");

        qualification.setCreatedUser(LoginAuthenticationHandler.getUserName());
        qualification.setStatus(Status.ACTIVE);
        qualification.setCreatedDate(TimeUtil.getCurrentTimeStamp());

        return qualificationRepository.saveAndFlush(qualification);
    }

    @Override
    public void delete(Long id) {
        Qualification fetchedQualification = findById(id);

        if (fetchedQualification == null)
            throw new RecordNotFoundException("No qualification record found for the id : " + id);

        qualificationRepository.delete(fetchedQualification);
    }

    @Override
    public Qualification update(Long id, Qualification newQualification) {
        LoginAuthenticationHandler.validateUser();

        Optional<Qualification> updatedQualification = qualificationRepository.findById(id).map(qualification -> {
            if (qualification.getVersion() != newQualification.getVersion()) throw new OldObjectException();

            qualification.setDescription(newQualification.getDescription());
            qualification.setStatus(newQualification.getStatus());
            qualification.setQualificationLevelId(newQualification.getQualificationLevelId());
            qualification.setModifiedUser(LoginAuthenticationHandler.getUserName());
            qualification.setModifiedDate(TimeUtil.getCurrentTimeStamp());

            return qualificationRepository.save(qualification);
        });

        if (updatedQualification.isPresent()) return updatedQualification.get();
        throw new RecordNotFoundException("No qualification record found for the id : " + id);
    }

    @Override
    public List<Qualification> findByQualificationLevelId(Long qualificationLevelId) {
        return qualificationRepository.findByQualificationLevelId(qualificationLevelId);
    }
}
