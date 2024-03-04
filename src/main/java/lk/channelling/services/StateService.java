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
package lk.channelling.services;

import lk.channelling.entity.State;
import lk.channelling.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing State-related business logic.
 *
 * <p>This interface defines methods for performing various operations related to State entities, such as retrieving
 * State details, performing validations and handling business logics. Implementing classes should provide concrete
 * implementations of these methods to fulfill the business requirements.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@Service
public interface StateService {

    /**
     * Retrieves a list of all Institutions.
     *
     * @return A list containing all Institutions.
     */
    List<State> findAll();

    /**
     * Returns the State by its id.
     *
     * @param id The id of the State
     * @return the State
     */
    State findById(Long id);


    /**
     * Returns the list of Institutions by its status.
     *
     * @param status the status, this should be either ACTIVE or INACTIVE.
     * @return the list of Institutions.
     */
    List<State> findByStatus(Status status);

    /**
     * Saves the given State.
     *
     * @param state The State object to be saved.
     * @return The saved State.
     */
    State save(State state);

    /**
     * Deletes the State by its id.
     *
     * @param id the id of the State to be deleted.
     */
    void delete(Long id);

    /**
     * Updates the given State id by the given details.
     *
     * @param id    the id of the State to be updated.
     * @param state the State details which contains update details.
     * @return the updated State details.
     */
    State update(Long id, State state);

    /**
     * Returns the state by the country id.
     *
     * @param countryId The country id
     * @return the list of states
     */
    List<State> findByCountryId(Long countryId);
}
