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

import lk.channelling.entity.Hospital;
import lk.channelling.enums.Status;
import lk.channelling.resources.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing Hospital-related business logic.
 *
 * <p>This interface defines methods for performing various operations related to Hospital entities, such as retrieving
 * Hospital details, performing validations and handling business logics. Implementing classes should provide concrete
 * implementations of these methods to fulfill the business requirements.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@Service
public interface HospitalService {

    /**
     * Retrieves a list of all Institutions.
     *
     * @return A list containing all Institutions.
     */
    ApiResponse findAll();

    /**
     * Returns the Hospital by its id.
     *
     * @param id The id of the Hospital
     * @return the Hospital
     */
    Hospital findById(Long id);


    /**
     * Returns the list of Institutions by its status.
     *
     * @param status the status, this should be either ACTIVE or INACTIVE.
     * @return the list of Institutions.
     */
    List<Hospital> findByStatus(Status status);

    /**
     * Saves the given Hospital.
     *
     * @param hospital The Hospital object to be saved.
     * @return The saved Hospital.
     */
    Hospital save(Hospital hospital);

    /**
     * Deletes the Hospital by its id.
     *
     * @param id the id of the Hospital to be deleted.
     */
    void delete(Long id);

    /**
     * Updates the given Hospital id by the given details.
     *
     * @param id       the id of the Hospital to be updated.
     * @param hospital the Hospital details which contains update details.
     * @return the updated Hospital details.
     */
    Hospital update(Long id, Hospital hospital);

    /**
     * Returns the hospital by the city id.
     *
     * @param cityId The city id
     * @return the list of states
     */
    List<Hospital> findByCityId(Long cityId);
}
