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
package lk.channelling.controllers;

import jakarta.validation.Valid;
import lk.channelling.entity.City;
import lk.channelling.enums.Status;
import lk.channelling.services.CityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to City entities.
 *
 * <p>This class serves as the entry point for handling requests related to City entities. It delegates the processing
 * to the {@link CityService} and orchestrates the flow of data between the client, service layer, and underlying
 * data store. It may handle HTTP methods, request mapping , and model/view rendering in a web application context.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/city")
@CrossOrigin(origins = "*")
@Log4j2
public class CityController {

    /**
     * The City service for handling City related business logic.
     */
    private CityService cityService;

    /**
     * Injects the CityService reference.
     *
     * @param cityService The City service to be injected.
     */
    @Autowired
    public void setInstitutionService(CityService cityService) {
        this.cityService = cityService;
    }

    /**
     * Handles HTTP Get requests to retrieve details of all cities.
     *
     * @return The details of all cities as a Response Entity.
     */
    @GetMapping("/all")
    public ResponseEntity<List<City>> findAll() {
        List<City> cities = cityService.findAll();

        if (cities.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    /**
     * Returns {@code City} by its id.
     *
     * @param id the id of the City.
     * @return the Response Entity with City.
     */
    @GetMapping("/id={id}")
    public ResponseEntity<City> findById(@PathVariable Long id) {
        City City = cityService.findById(id);
        return new ResponseEntity<>(City, HttpStatus.OK);
    }


    /**
     * Returns the list of City by the given status.
     *
     * @param status The status of the City. It should be either ACTIVE or INACTIVE.
     * @return the List of cities.
     */
    @GetMapping("/status={status}")
    public ResponseEntity<List<City>> findByStatus(@PathVariable Status status) {
        List<City> cities = cityService.findByStatus(status);

        if (cities.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    /**
     * Endpoint to save a new city.
     *
     * @param city The city object representing the city data to be saved.
     * @return ResponseEntity with the saved city and HTTP status.
     */
    @PostMapping("/save")
    public ResponseEntity<City> save(@Valid @RequestBody City city) {
        City savedInstitution = cityService.save(city);
        return new ResponseEntity<>(savedInstitution, HttpStatus.CREATED);
    }

    /**
     * Deletes the City by the given City id.
     *
     * @param id The id of the City.
     * @return the Response Entity with NO CONTENT response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstitution(@PathVariable Long id) {
        cityService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates the existing City record by using the given details.
     *
     * @param id          the id of the City to be updated.
     * @param institution the City  object which contains the update details
     * @return the Response Entity with update City details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<City> update(@PathVariable Long id, @Valid @RequestBody City institution) {
        City updatedInstitution = cityService.update(id, institution);
        return new ResponseEntity<>(updatedInstitution, HttpStatus.OK);
    }

    @GetMapping("/state-id={stateId}")
    public ResponseEntity<List<City>> findByStateId(@PathVariable Long stateId) {
        List<City> cities = cityService.findByStateId(stateId);

        if (cities.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
