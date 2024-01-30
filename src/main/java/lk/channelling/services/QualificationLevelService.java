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

import lk.channelling.entity.QualificationLevel;
import lk.channelling.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing QualificationLevel-related business logic.
 *
 * <p>This interface defines methods for performing various operations related to QualificationLevel entities, such as retrieving
 * QualificationLevel details, performing validations and handling business logics. Implementing classes should provide concrete
 * implementations of these methods to fulfill the business requirements.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@Service
public interface QualificationLevelService {

    /**
     * Retrieves a list of all QualificationLevels.
     *
     * @return A list containing all QualificationLevels.
     */
    List<QualificationLevel> findAll();

    /**
     * Returns the QualificationLevel by its id.
     *
     * @param id The id of the QualificationLevel
     * @return the QualificationLevel
     */
    QualificationLevel findById(Long id);

    /**
     * Returns the QualificationLevel by its code.
     *
     * @param code the code of the QualificationLevel.
     * @return the QualificationLevel
     */
    QualificationLevel findByCode(String code);

    /**
     * Returns the list of QualificationLevels by its status.
     *
     * @param status the status, this should be either ACTIVE or INACTIVE.
     * @return the list of QualificationLevels.
     */
    List<QualificationLevel> findByStatus(Status status);

    /**
     * Saves the given QualificationLevel.
     *
     * @param qualificationLevel The QualificationLevel object to be saved.
     * @return The saved QualificationLevel.
     */
    QualificationLevel save(QualificationLevel qualificationLevel);

    /**
     * Deletes the QualificationLevel by its id.
     *
     * @param id the id of the QualificationLevel to be deleted.
     */
    void delete(Long id);

    /**
     * Updates the given QualificationLevel id by the given details.
     *
     * @param id                 the id of the QualificationLevel to be updated.
     * @param qualificationLevel the QualificationLevel details which contains update details.
     * @return the updated QualificationLevel details.
     */
    QualificationLevel update(Long id, QualificationLevel qualificationLevel);
}
