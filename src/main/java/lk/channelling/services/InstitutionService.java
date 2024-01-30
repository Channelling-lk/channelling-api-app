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

import lk.channelling.entity.Institution;
import lk.channelling.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing Institution-related business logic.
 *
 * <p>This interface defines methods for performing various operations related to Institution entities, such as retrieving
 * Institution details, performing validations and handling business logics. Implementing classes should provide concrete
 * implementations of these methods to fulfill the business requirements.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@Service
public interface InstitutionService {

    /**
     * Retrieves a list of all Institutions.
     *
     * @return A list containing all Institutions.
     */
    List<Institution> findAll();

    /**
     * Returns the Institution by its id.
     *
     * @param id The id of the Institution
     * @return the Institution
     */
    Institution findById(Long id);

    /**
     * Returns the Institution by its code.
     *
     * @param code the code of the Institution.
     * @return the Institution
     */
    Institution findByCode(String code);

    /**
     * Returns the list of Institutions by its status.
     *
     * @param status the status, this should be either ACTIVE or INACTIVE.
     * @return the list of Institutions.
     */
    List<Institution> findByStatus(Status status);

    /**
     * Saves the given Institution.
     *
     * @param institution The Institution object to be saved.
     * @return The saved Institution.
     */
    Institution save(Institution institution);

    /**
     * Deletes the Institution by its id.
     *
     * @param id the id of the Institution to be deleted.
     */
    void delete(Long id);

    /**
     * Updates the given Institution id by the given details.
     *
     * @param id          the id of the Institution to be updated.
     * @param institution the Institution details which contains update details.
     * @return the updated Institution details.
     */
    Institution update(Long id, Institution institution);

    /**
     * Returns the institution by the country id.
     *
     * @param countryId The country id
     * @return the list of institutions
     */
    List<Institution> findByCountryId(Long countryId);
}
