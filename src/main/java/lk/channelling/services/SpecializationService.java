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

import lk.channelling.entity.Specialization;
import lk.channelling.enums.Status;
import lk.channelling.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing Specialization-related business logic.
 *
 * <p>This interface defines methods for performing various operations related to Specialization entities, such as retrieving
 * Specialization details, performing validations and handling business logics. Implementing classes should provide concrete
 * implementations of these methods to fulfill the business requirements.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@Service
public interface SpecializationService {

    /**
     * Retrieves a list of all Specializations.
     *
     * @return A list containing all Specializations.
     */
    ApiResponse findAll();

    /**
     * Returns the Specialization by its id.
     *
     * @param id The id of the Specialization
     * @return the Specialization
     */
    Specialization findById(Long id);

    /**
     * Returns the Specialization by its code.
     *
     * @param code the code of the Specialization.
     * @return the Specialization
     */
    Specialization findByCode(String code);

    /**
     * Returns the list of Specializations by its status.
     *
     * @param status the status, this should be either ACTIVE or INACTIVE.
     * @return the list of Specializations.
     */
    List<Specialization> findByStatus(Status status);

    /**
     * Saves the given Specialization.
     *
     * @param specialization The Specialization object to be saved.
     * @return The saved Specialization.
     */
    Specialization save(Specialization specialization);

    /**
     * Deletes the Specialization by its id.
     *
     * @param id the id of the Specialization to be deleted.
     */
    void delete(Long id);

    /**
     * Updates the given Specialization id by the given details.
     *
     * @param id      the id of the Specialization to be updated.
     * @param specialization the Specialization details which contains update details.
     * @return the updated Specialization details.
     */
    Specialization update(Long id, Specialization specialization);
}
