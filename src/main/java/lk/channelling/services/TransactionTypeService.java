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

import lk.channelling.entity.TransactionType;
import lk.channelling.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing TransactionType-related business logic.
 *
 * <p>This interface defines methods for performing various operations related to TransactionType entities, such as retrieving
 * TransactionType details, performing validations and handling business logics. Implementing classes should provide concrete
 * implementations of these methods to fulfill the business requirements.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@Service
public interface TransactionTypeService {

    /**
     * Retrieves a list of all TransactionTypes.
     *
     * @return A list containing all TransactionTypes.
     */
    List<TransactionType> findAll();

    /**
     * Returns the TransactionType by its id.
     *
     * @param id The id of the TransactionType
     * @return the TransactionType
     */
    TransactionType findById(Long id);

    /**
     * Returns the TransactionType by its code.
     *
     * @param code the code of the TransactionType.
     * @return the TransactionType
     */
    TransactionType findByCode(String code);

    /**
     * Returns the list of TransactionTypes by its status.
     *
     * @param status the status, this should be either ACTIVE or INACTIVE.
     * @return the list of TransactionTypes.
     */
    List<TransactionType> findByStatus(Status status);

    /**
     * Saves the given TransactionType.
     *
     * @param transactionType The TransactionType object to be saved.
     * @return The saved TransactionType.
     */
    TransactionType save(TransactionType transactionType);

    /**
     * Deletes the TransactionType by its id.
     *
     * @param id the id of the TransactionType to be deleted.
     */
    void delete(Long id);

    /**
     * Updates the given TransactionType id by the given details.
     *
     * @param id              the id of the TransactionType to be updated.
     * @param transactionType the TransactionType details which contains update details.
     * @return the updated TransactionType details.
     */
    TransactionType update(Long id, TransactionType transactionType);
}
