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

import lk.channelling.entity.ContactMethod;
import lk.channelling.enums.Status;
import lk.channelling.exception.ObjectNotUniqueException;
import lk.channelling.exception.OldObjectException;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.ContactMethodRepository;
import lk.channelling.services.ContactMethodService;
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
public class ContactMethodServiceImpl implements ContactMethodService {

    private ContactMethodRepository contactMethodRepository;

    @Autowired
    public void setContactMethodRepository(ContactMethodRepository contactMethodRepository) {
        this.contactMethodRepository = contactMethodRepository;
    }

    @Override
    public List<ContactMethod> findAll() {
        return contactMethodRepository.findAll();
    }

    @Override
    public ContactMethod findById(Long id) {
        Optional<ContactMethod> country = contactMethodRepository.findById(id);

        if (country.isEmpty()) throw new RecordNotFoundException("No contact method record found for the id : " + id);

        return country.get();
    }

    @Override
    public ContactMethod findByCode(String code) {
        Optional<ContactMethod> country = contactMethodRepository.findByCode(code);

        if (country.isEmpty())
            throw new RecordNotFoundException("No contact method record found for the code : " + code);

        return country.get();
    }

    @Override
    public List<ContactMethod> findByStatus(Status status) {
        return contactMethodRepository.findByStatus(status);
    }

    @Override
    public ContactMethod save(ContactMethod contactMethod) {
        LoginAuthenticationHandler.validateUser();
        Optional<ContactMethod> fetchedContactMethod = contactMethodRepository.findByCode(contactMethod.getCode());

        if (fetchedContactMethod.isPresent())
            throw new ObjectNotUniqueException("The entered contact method already exists in the database.");

        contactMethod.setCreatedUser(LoginAuthenticationHandler.getUserName());
        contactMethod.setStatus(Status.ACTIVE);
        contactMethod.setCreatedDate(TimeUtil.getCurrentTimeStamp());

        return contactMethodRepository.saveAndFlush(contactMethod);
    }

    @Override
    public void delete(Long id) {
        ContactMethod fetchedContactMethod = findById(id);

        if (fetchedContactMethod == null)
            throw new RecordNotFoundException("No contact method record found for the id : " + id);

        contactMethodRepository.delete(fetchedContactMethod);
    }

    @Override
    public ContactMethod update(Long id, ContactMethod newContactMethod) {
        LoginAuthenticationHandler.validateUser();

        Optional<ContactMethod> updatedContactMethod = contactMethodRepository.findById(id).map(cm -> {
            if (cm.getVersion() != newContactMethod.getVersion())
                throw new OldObjectException();

            cm.setDescription(newContactMethod.getDescription());
            cm.setStatus(newContactMethod.getStatus());
            cm.setModifiedUser(LoginAuthenticationHandler.getUserName());
            cm.setModifiedDate(TimeUtil.getCurrentTimeStamp());

            return contactMethodRepository.save(cm);
        });

        if (updatedContactMethod.isPresent()) return updatedContactMethod.get();
        throw new RecordNotFoundException("No contact method record found for the id : " + id);
    }
}
