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

import lk.channelling.entity.Title;
import lk.channelling.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing title-related business logic.
 *
 * <p>This interface defines methods for performing various operations related to title entities, such as retrieving
 * title details, performing validations and handling business logics. Implementing classes should provide concrete
 * implementations of these methods to fulfill the business requirements.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@Service
public interface TitleService {

    /**
     * Retrieves a list of all countries.
     *
     * @return A list containing all countries.
     */
    List<Title> findAll();

    /**
     * Returns the title by its id.
     *
     * @param id The id of the title
     * @return the Title
     */
    Title findById(Long id);

    /**
     * Returns the title by its code.
     *
     * @param code the code of the title.
     * @return the Title
     */
    Title findByCode(String code);

    /**
     * Returns the list of countries by its status.
     *
     * @param status the status, this should be either ACTIVE or INACTIVE.
     * @return the list of countries.
     */
    List<Title> findByStatus(Status status);

    /**
     * Saves the given title.
     *
     * @param title The title object to be saved.
     * @return The saved title.
     */
    Title save(Title title);

    /**
     * Deletes the title by its id.
     *
     * @param id the id of the title to be deleted.
     */
    void delete(Long id);

    /**
     * Updates the given title id by the given details.
     *
     * @param id    the id of the title to be updated.
     * @param title the title details which contains update details.
     * @return the updated title details.
     */
    Title update(Long id, Title title);
}
