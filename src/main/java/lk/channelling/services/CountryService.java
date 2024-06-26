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
import lk.channelling.resources.PageArray;
import lk.channelling.resources.PagingRequest;
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

    /**
     * Returns the country by its id.
     *
     * @param id The id of the country
     * @return the Country
     */
    Country findById(Long id);

    /**
     * Returns the country by its code.
     *
     * @param code the code of the country.
     * @return the Country
     */
    Country findByCode(String code);

    /**
     * Returns the list of countries by its status.
     *
     * @param status the status, this should be either ACTIVE or INACTIVE.
     * @return the list of countries.
     */
    List<Country> findByStatus(Status status);

    /**
     * Saves the given country.
     *
     * @param country The country object to be saved.
     * @return The saved country.
     */
    Country save(Country country);

    /**
     * Deletes the country by its id.
     *
     * @param id the id of the country to be deleted.
     */
    void delete(Long id);

    /**
     * Updates the given country id by the given details.
     *
     * @param id      the id of the country to be updated.
     * @param country the country details which contains update details.
     * @return the updated country details.
     */
    Country update(Long id, Country country);

    PageArray getData(PagingRequest pagingRequest);
}
