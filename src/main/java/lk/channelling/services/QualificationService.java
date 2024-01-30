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

import lk.channelling.entity.Qualification;
import lk.channelling.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing Qualification-related business logic.
 *
 * <p>This interface defines methods for performing various operations related to Qualification entities, such as retrieving
 * Qualification details, performing validations and handling business logics. Implementing classes should provide concrete
 * implementations of these methods to fulfill the business requirements.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@Service
public interface QualificationService {

    /**
     * Retrieves a list of all Qualifications.
     *
     * @return A list containing all Qualifications.
     */
    List<Qualification> findAll();

    /**
     * Returns the Qualification by its id.
     *
     * @param id The id of the Qualification
     * @return the Qualification
     */
    Qualification findById(Long id);

    /**
     * Returns the Qualification by its code.
     *
     * @param code the code of the Qualification.
     * @return the Qualification
     */
    Qualification findByCode(String code);

    /**
     * Returns the list of Qualifications by its status.
     *
     * @param status the status, this should be either ACTIVE or INACTIVE.
     * @return the list of Qualifications.
     */
    List<Qualification> findByStatus(Status status);

    /**
     * Saves the given Qualification.
     *
     * @param qualification The Qualification object to be saved.
     * @return The saved Qualification.
     */
    Qualification save(Qualification qualification);

    /**
     * Deletes the Qualification by its id.
     *
     * @param id the id of the Qualification to be deleted.
     */
    void delete(Long id);

    /**
     * Updates the given Qualification id by the given details.
     *
     * @param id            the id of the Qualification to be updated.
     * @param qualification the Qualification details which contains update details.
     * @return the updated Qualification details.
     */
    Qualification update(Long id, Qualification qualification);

    /**
     * Returns the qualification by the qualificationLevelId
     *
     * @param qualificationLevelId The qualificationLevelId
     * @return the list of qualifications
     */
    List<Qualification> findByQualificationLevelId(Long qualificationLevelId);
}
