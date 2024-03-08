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

import lk.channelling.entity.Doctor;
import lk.channelling.enums.Status;
import lk.channelling.response.ApiResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing Doctor-related business logic.
 *
 * <p>This interface defines methods for performing various operations related to Doctor entities, such as retrieving
 * Doctor details, performing validations and handling business logics. Implementing classes should provide concrete
 * implementations of these methods to fulfill the business requirements.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@Service
public interface DoctorService {

    /**
     * Retrieves a list of all Institutions.
     *
     * @return A list containing all Institutions.
     */
    ApiResponse findAll();

    /**
     * Returns the Doctor by its id.
     *
     * @param id The id of the Doctor
     * @return the Doctor
     */
    Doctor findById(Long id);


    /**
     * Returns the list of Institutions by its status.
     *
     * @param status the status, this should be either ACTIVE or INACTIVE.
     * @return the list of Institutions.
     */
    List<Doctor> findByStatus(Status status);

    /**
     * Saves the given Doctor.
     *
     * @param doctor The Doctor object to be saved.
     * @return The saved Doctor.
     */
    Doctor save(Doctor doctor);

    /**
     * Deletes the Doctor by its id.
     *
     * @param id the id of the Doctor to be deleted.
     */
    void delete(Long id);

    /**
     * Updates the given Doctor id by the given details.
     *
     * @param id     the id of the Doctor to be updated.
     * @param doctor the Doctor details which contains update details.
     * @return the updated Doctor details.
     */
    Doctor update(Long id, Doctor doctor);

}
