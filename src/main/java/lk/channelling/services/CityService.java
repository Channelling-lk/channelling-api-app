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

import lk.channelling.entity.City;
import lk.channelling.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing City-related business logic.
 *
 * <p>This interface defines methods for performing various operations related to City entities, such as retrieving
 * City details, performing validations and handling business logics. Implementing classes should provide concrete
 * implementations of these methods to fulfill the business requirements.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@Service
public interface CityService {

    /**
     * Retrieves a list of all Institutions.
     *
     * @return A list containing all Institutions.
     */
    List<City> findAll();

    /**
     * Returns the City by its id.
     *
     * @param id The id of the City
     * @return the City
     */
    City findById(Long id);


    /**
     * Returns the list of Institutions by its status.
     *
     * @param status the status, this should be either ACTIVE or INACTIVE.
     * @return the list of Institutions.
     */
    List<City> findByStatus(Status status);

    /**
     * Saves the given City.
     *
     * @param city The City object to be saved.
     * @return The saved City.
     */
    City save(City city);

    /**
     * Deletes the City by its id.
     *
     * @param id the id of the City to be deleted.
     */
    void delete(Long id);

    /**
     * Updates the given City id by the given details.
     *
     * @param id   the id of the City to be updated.
     * @param city the City details which contains update details.
     * @return the updated City details.
     */
    City update(Long id, City city);

    /**
     * Returns the city by the state id.
     *
     * @param stateId The state id
     * @return the list of states
     */
    List<City> findByStateId(Long stateId);
}
