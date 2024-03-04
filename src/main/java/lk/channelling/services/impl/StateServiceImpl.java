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

import lk.channelling.entity.State;
import lk.channelling.enums.Status;
import lk.channelling.exception.OldObjectException;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.StateRepository;
import lk.channelling.services.StateService;
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
public class StateServiceImpl implements StateService {

    private StateRepository stateRepository;

    @Autowired
    public void setStateRepository(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public List<State> findAll() {
        return stateRepository.findAll();
    }

    @Override
    public State findById(Long id) {
        Optional<State> state = stateRepository.findById(id);

        if (state.isEmpty()) throw new RecordNotFoundException("No state record found for the id : " + id);

        return state.get();
    }

    @Override
    public List<State> findByStatus(Status status) {
        return stateRepository.findByStatus(status);
    }

    @Override
    public State save(State state) {
        LoginAuthenticationHandler.validateUser();

        state.setCreatedUser(LoginAuthenticationHandler.getUserName());
        state.setStatus(Status.ACTIVE);
        state.setCreatedDate(TimeUtil.getCurrentTimeStamp());

        return stateRepository.saveAndFlush(state);
    }

    @Override
    public void delete(Long id) {
        State fetchedInstitution = findById(id);

        if (fetchedInstitution == null) throw new RecordNotFoundException("No state record found for the id : " + id);

        stateRepository.delete(fetchedInstitution);
    }

    @Override
    public State update(Long id, State newState) {
        LoginAuthenticationHandler.validateUser();

        Optional<State> updatedInstitution = stateRepository.findById(id).map(state -> {
            if (state.getVersion() != newState.getVersion()) throw new OldObjectException();

            state.setDescription(newState.getDescription());
            state.setStatus(newState.getStatus());
            state.setCountryId(newState.getCountryId());
            state.setModifiedUser(LoginAuthenticationHandler.getUserName());
            state.setModifiedDate(TimeUtil.getCurrentTimeStamp());

            return stateRepository.save(state);
        });

        if (updatedInstitution.isPresent()) return updatedInstitution.get();
        throw new RecordNotFoundException("No state record found for the id : " + id);
    }

    @Override
    public List<State> findByCountryId(Long countryId) {
        return stateRepository.findByCountryId(countryId);
    }
}
