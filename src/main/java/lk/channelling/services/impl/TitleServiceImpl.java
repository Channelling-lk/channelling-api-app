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

import lk.channelling.entity.Title;
import lk.channelling.enums.Status;
import lk.channelling.exception.ObjectNotUniqueException;
import lk.channelling.exception.OldObjectException;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.TitleRepository;
import lk.channelling.services.TitleService;
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
public class TitleServiceImpl implements TitleService {

    private TitleRepository titleRepository;

    @Autowired
    public void setTitleRepository(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @Override
    public List<Title> findAll() {
        return titleRepository.findAll();
    }

    @Override
    public Title findById(Long id) {
        Optional<Title> title = titleRepository.findById(id);

        if (title.isEmpty()) throw new RecordNotFoundException("No title record found for the id : " + id);

        return title.get();
    }

    @Override
    public Title findByCode(String code) {
        Optional<Title> title = titleRepository.findByCode(code);

        if (title.isEmpty()) throw new RecordNotFoundException("No title record found for the code : " + code);

        return title.get();
    }

    @Override
    public List<Title> findByStatus(Status status) {
        return titleRepository.findByStatus(status);
    }

    @Override
    public Title save(Title title) {
        LoginAuthenticationHandler.validateUser();

        Optional<Title> fetchedTitle = titleRepository.findByCode(title.getCode());

        if (fetchedTitle.isPresent())
            throw new ObjectNotUniqueException("The entered title already exists in the database.");

        title.setStatus(Status.ACTIVE);
        title.setCreatedUser(LoginAuthenticationHandler.getUserName());
        title.setCreatedDate(TimeUtil.getCurrentTimeStamp());

        return titleRepository.saveAndFlush(title);
    }

    @Override
    public void delete(Long id) {
        Title fetchedTitle = findById(id);
        if (fetchedTitle == null) throw new RecordNotFoundException("No title record found for the id : " + id);

        titleRepository.delete(fetchedTitle);
    }

    @Override
    public Title update(Long id, Title newTitle) {
        LoginAuthenticationHandler.validateUser();

        Optional<Title> updatedTitle = titleRepository.findById(id).map(title -> {
            if (title.getVersion() != newTitle.getVersion()) throw new OldObjectException();

            title.setDescription(newTitle.getDescription());
            title.setStatus(newTitle.getStatus());
            title.setModifiedUser(LoginAuthenticationHandler.getUserName());
            title.setModifiedDate(TimeUtil.getCurrentTimeStamp());

            return titleRepository.save(title);
        });

        if (updatedTitle.isPresent()) return updatedTitle.get();
        throw new RecordNotFoundException("No title record found for the id : " + id);
    }
}
