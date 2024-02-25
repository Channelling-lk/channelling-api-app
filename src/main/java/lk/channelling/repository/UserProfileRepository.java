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
package lk.channelling.repository;

import lk.channelling.entity.UserProfile;
import lk.channelling.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for managing and accessing country information.
 *
 * <p>This interface defines methods for retrieving, updating, and managing country data
 * within a data store, such as a database. Implementing classes should provide
 * concrete implementations of these methods to interact with the underlying data
 * source.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    /**
     * Returns the country by its id.
     *
     * @param id must not be {@literal null}. The id of the country.
     * @return the country details.
     */

    Optional<UserProfile> findById(Long id);

    /**
     * Returns the list of countries by the status.
     *
     * @param status the status of the country. It should either ACTIVE or INACTIVE.
     * @return the list of countries.
     */
    List<UserProfile> findByStatus(Status status);


}
