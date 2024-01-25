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

import lk.channelling.entity.ContactMethod;
import lk.channelling.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing ContactMethod-related business logic.
 *
 * <p>This interface defines methods for performing various operations related to ContactMethod entities, such as retrieving
 * ContactMethod details, performing validations and handling business logics. Implementing classes should provide concrete
 * implementations of these methods to fulfill the business requirements.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@Service
public interface ContactMethodService {

    /**
     * Retrieves a list of all ContactMethods.
     *
     * @return A list containing all ContactMethods.
     */
    List<ContactMethod> findAll();

    /**
     * Returns the ContactMethod by its id.
     *
     * @param id The id of the ContactMethod
     * @return the ContactMethod
     */
    ContactMethod findById(Long id);

    /**
     * Returns the ContactMethod by its code.
     *
     * @param code the code of the ContactMethod.
     * @return the ContactMethod
     */
    ContactMethod findByCode(String code);

    /**
     * Returns the list of ContactMethods by its status.
     *
     * @param status the status, this should be either ACTIVE or INACTIVE.
     * @return the list of ContactMethods.
     */
    List<ContactMethod> findByStatus(Status status);

    /**
     * Saves the given ContactMethod.
     *
     * @param contactMethod The ContactMethod object to be saved.
     * @return The saved ContactMethod.
     */
    ContactMethod save(ContactMethod contactMethod);

    /**
     * Deletes the ContactMethod by its id.
     *
     * @param id the id of the ContactMethod to be deleted.
     */
    void delete(Long id);

    /**
     * Updates the given ContactMethod id by the given details.
     *
     * @param id      the id of the ContactMethod to be updated.
     * @param contactMethod the ContactMethod details which contains update details.
     * @return the updated ContactMethod details.
     */
    ContactMethod update(Long id, ContactMethod contactMethod);
}
