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

import lk.channelling.entity.UserProfile;
import lk.channelling.enums.Status;
import lk.channelling.exception.ObjectNotUniqueException;
import lk.channelling.exception.OldObjectException;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.UserProfileRepository;
import lk.channelling.services.UserProfileService;
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
public class UserProfileServiceImpl implements UserProfileService {

    private UserProfileRepository userProfileRepository;

    @Autowired
    public void setUserProfileRepository(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public List<UserProfile> findAll() {
        return userProfileRepository.findAll();
    }

    @Override
    public UserProfile findById(Long id) {
        Optional<UserProfile> userProfile = userProfileRepository.findById(id);

        if (userProfile.isEmpty()) throw new RecordNotFoundException("No userProfile record found for the id : " + id);

        return userProfile.get();
    }

    @Override
    public UserProfile findByCode(String code) {
        Optional<UserProfile> userProfile = userProfileRepository.findByCode(code);

        if (userProfile.isEmpty())
            throw new RecordNotFoundException("No userProfile record found for the code : " + code);

        return userProfile.get();
    }

    @Override
    public List<UserProfile> findByStatus(Status status) {
        return userProfileRepository.findByStatus(status);
    }

    @Override
    public UserProfile save(UserProfile userProfile) {
        LoginAuthenticationHandler.validateUser();

        Optional<UserProfile> fetchedUserProfile = userProfileRepository.findById(userProfile.getId());

        if (fetchedUserProfile.isPresent())
            throw new ObjectNotUniqueException("The entered userProfile already exists in the database.");

        userProfile.setStatus(Status.ACTIVE);
        userProfile.setCreatedUser(LoginAuthenticationHandler.getUserName());
        userProfile.setCreatedDate(TimeUtil.getCurrentTimeStamp());

        return userProfileRepository.saveAndFlush(userProfile);
    }

    @Override
    public void delete(Long id) {
        UserProfile fetchedUserProfile = findById(id);
        if (fetchedUserProfile == null)
            throw new RecordNotFoundException("No userProfile record found for the id : " + id);

        userProfileRepository.delete(fetchedUserProfile);
    }

    @Override
    public UserProfile update(Long id, UserProfile c) {
        LoginAuthenticationHandler.validateUser();

        Optional<UserProfile> updatedUserProfile = userProfileRepository.findById(id).map(userProfile -> {
            if (userProfile.getVersion() != c.getVersion()) throw new OldObjectException();

            userProfile.setFirstName(c.getFirstName());
            userProfile.setLastName(c.getLastName());
            userProfile.setDisplayName(c.getDisplayName());
            userProfile.setIdentificationMethod(c.getIdentificationMethod());
            userProfile.setIdentificationValue(c.getIdentificationValue());
            userProfile.setMobileNo(c.getMobileNo());
            userProfile.setEmail(c.getEmail());
            userProfile.setAddressLine1(c.getAddressLine1());
            userProfile.setAddressLine2(c.getAddressLine2());
            userProfile.setAddressLine3(c.getAddressLine3());
            userProfile.setDateOfBirth(c.getDateOfBirth());
            userProfile.setStatus(c.getStatus());
            userProfile.setModifiedUser(LoginAuthenticationHandler.getUserName());
            userProfile.setModifiedDate(TimeUtil.getCurrentTimeStamp());

            return userProfileRepository.save(userProfile);
        });

        if (updatedUserProfile.isPresent()) return updatedUserProfile.get();
        throw new RecordNotFoundException("No userProfile record found for the id : " + id);
    }
}
