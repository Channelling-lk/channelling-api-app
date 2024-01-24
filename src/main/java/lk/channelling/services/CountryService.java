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

import lk.channelling.entity.Country;
import lk.channelling.enums.Status;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing country-related business logic.
 *
 * <p>This interface defines methods for performing various operations related to country entities, such as retrieving
 * country details, performing validations and handling business logics. Implementing classes should provide concrete
 * implementations of these methods to fulfill the business requirements.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@Service
public interface CountryService {

    /**
     * Retrieves a list of all countries.
     *
     * @return A list containing all countries.
     */
    List<Country> findAll();

    Country findById(Long id);

    Country findByCode(String code);

    List<Country> findByStatus(Status status);

    /**
     * Saves the given country.
     *
     * @param country The country object to be saved.
     * @return The saved country.
     */
    Country save(Country country);

    void delete(Long id);

    Country update(Long id, Country country);
}
