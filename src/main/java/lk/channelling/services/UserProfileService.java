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

import lk.channelling.entity.UserProfile;
import lk.channelling.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing userProfile-related business logic.
 *
 * <p>This interface defines methods for performing various operations related to userProfile entities, such as retrieving
 * userProfile details, performing validations and handling business logics. Implementing classes should provide concrete
 * implementations of these methods to fulfill the business requirements.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@Service
public interface UserProfileService {

    /**
     * Retrieves a list of all countries.
     *
     * @return A list containing all countries.
     */
    List<UserProfile> findAll();

    /**
     * Returns the userProfile by its id.
     *
     * @param id The id of the userProfile
     * @return the UserProfile
     */
    UserProfile findById(Long id);


    /**
     * Returns the list of countries by its status.
     *
     * @param status the status, this should be either ACTIVE or INACTIVE.
     * @return the list of countries.
     */
    List<UserProfile> findByStatus(Status status);

    /**
     * Saves the given userProfile.
     *
     * @param userProfile The userProfile object to be saved.
     * @return The saved userProfile.
     */
    UserProfile save(UserProfile userProfile);

    /**
     * Deletes the userProfile by its id.
     *
     * @param id the id of the userProfile to be deleted.
     */
    void delete(Long id);

    /**
     * Updates the given userProfile id by the given details.
     *
     * @param id          the id of the userProfile to be updated.
     * @param userProfile the userProfile details which contains update details.
     * @return the updated userProfile details.
     */
    UserProfile update(Long id, UserProfile userProfile);
}
