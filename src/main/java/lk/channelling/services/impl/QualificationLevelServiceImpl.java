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

import lk.channelling.entity.QualificationLevel;
import lk.channelling.enums.Status;
import lk.channelling.exception.ObjectNotUniqueException;
import lk.channelling.exception.OldObjectException;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.QualificationLevelRepository;
import lk.channelling.services.QualificationLevelService;
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
public class QualificationLevelServiceImpl implements QualificationLevelService {

    private QualificationLevelRepository qualificationLevelRepository;

    @Autowired
    public void setQualificationLevelRepository(QualificationLevelRepository qualificationLevelRepository) {
        this.qualificationLevelRepository = qualificationLevelRepository;
    }

    @Override
    public List<QualificationLevel> findAll() {
        return qualificationLevelRepository.findAll();
    }

    @Override
    public QualificationLevel findById(Long id) {
        Optional<QualificationLevel> country = qualificationLevelRepository.findById(id);

        if (country.isEmpty()) throw new RecordNotFoundException("No Qualification Level record found for the id : " + id);

        return country.get();
    }

    @Override
    public QualificationLevel findByCode(String code) {
        Optional<QualificationLevel> country = qualificationLevelRepository.findByCode(code);

        if (country.isEmpty())
            throw new RecordNotFoundException("No Qualification Level record found for the code : " + code);

        return country.get();
    }

    @Override
    public List<QualificationLevel> findByStatus(Status status) {
        return qualificationLevelRepository.findByStatus(status);
    }

    @Override
    public QualificationLevel save(QualificationLevel qualificationLevel) {
        LoginAuthenticationHandler.validateUser();
        Optional<QualificationLevel> fetchedQualificationLevel = qualificationLevelRepository.findByCode(qualificationLevel.getCode());

        if (fetchedQualificationLevel.isPresent())
            throw new ObjectNotUniqueException("The entered Qualification Level already exists in the database.");

        qualificationLevel.setCreatedUser(LoginAuthenticationHandler.getUserName());
        qualificationLevel.setStatus(Status.ACTIVE);
        qualificationLevel.setCreatedDate(TimeUtil.getCurrentTimeStamp());

        return qualificationLevelRepository.saveAndFlush(qualificationLevel);
    }

    @Override
    public void delete(Long id) {
        QualificationLevel fetchedQualificationLevel = findById(id);

        if (fetchedQualificationLevel == null)
            throw new RecordNotFoundException("No Qualification Level record found for the id : " + id);

        qualificationLevelRepository.delete(fetchedQualificationLevel);
    }

    @Override
    public QualificationLevel update(Long id, QualificationLevel newQualificationLevel) {
        LoginAuthenticationHandler.validateUser();

        Optional<QualificationLevel> updatedQualificationLevel = qualificationLevelRepository.findById(id).map(cm -> {
            if (cm.getVersion() != newQualificationLevel.getVersion())
                throw new OldObjectException();

            cm.setDescription(newQualificationLevel.getDescription());
            cm.setStatus(newQualificationLevel.getStatus());
            cm.setModifiedUser(LoginAuthenticationHandler.getUserName());
            cm.setModifiedDate(TimeUtil.getCurrentTimeStamp());

            return qualificationLevelRepository.save(cm);
        });

        if (updatedQualificationLevel.isPresent()) return updatedQualificationLevel.get();
        throw new RecordNotFoundException("No Qualification Level record found for the id : " + id);
    }
}
