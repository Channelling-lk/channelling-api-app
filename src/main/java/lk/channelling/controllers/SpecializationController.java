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
import lk.channelling.entity.Specialization;
import lk.channelling.enums.Status;
import lk.channelling.resources.ApiResponse;
import lk.channelling.services.SpecializationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to Specialization entities.
 *
 * <p>This class serves as the entry point for handling requests related to Specialization entities. It delegates the processing
 * to the {@link SpecializationService} and orchestrates the flow of data between the client, service layer, and underlying
 * data store. It may handle HTTP methods, request mapping , and model/view rendering in a web application context.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/specialization")
@CrossOrigin(origins = "*")
@Log4j2
public class SpecializationController {

    /**
     * The Specialization service for handling Specialization related business logic.
     */
    private SpecializationService specializationService;

    /**
     * Injects the SpecializationService reference.
     *
     * @param specializationService The Specialization service to be injected.
     */
    @Autowired
    public void setSpecializationService(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    /**
     * Handles HTTP Get requests to retrieve details of all countries.
     *
     * @return The details of all countries as a Response Entity.
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> findAll() {
        ApiResponse specializations = specializationService.findAll();

        if (specializations == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(specializations, HttpStatus.OK);
    }

    /**
     * Returns {@code Specialization} by its id.
     *
     * @param id the id of the Specialization.
     * @return the Response Entity with Specialization.
     */
    @GetMapping("/id={id}")
    public ResponseEntity<Specialization> findById(@PathVariable Long id) {
        Specialization specialization = specializationService.findById(id);
        return new ResponseEntity<>(specialization, HttpStatus.OK);
    }

    /**
     * Returns the Specialization by its code.
     *
     * @param code The code of the Specialization.
     * @return The Response Entity with Specialization object.
     */
    @GetMapping("/code={code}")
    public ResponseEntity<Specialization> findByCode(@PathVariable String code) {
        Specialization specialization = specializationService.findByCode(code);
        return new ResponseEntity<>(specialization, HttpStatus.OK);
    }

    /**
     * Returns the list of Specialization by the given status.
     *
     * @param status The status of the Specialization. It should be either ACTIVE or INACTIVE.
     * @return the List of countries.
     */
    @GetMapping("/status={status}")
    public ResponseEntity<List<Specialization>> findByStatus(@PathVariable Status status) {
        List<Specialization> countries = specializationService.findByStatus(status);

        if (countries.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    /**
     * Endpoint to save a new Specialization.
     *
     * @param specialization The Specialization object representing the Specialization data to be saved.
     * @return ResponseEntity with the saved Specialization and HTTP status.
     */
    @PostMapping("/save")
    public ResponseEntity<Specialization> save(@Valid @RequestBody Specialization specialization) {
        Specialization savedSpecialization = specializationService.save(specialization);
        return new ResponseEntity<>(savedSpecialization, HttpStatus.CREATED);
    }

    /**
     * Deletes the Specialization by the given Specialization id.
     *
     * @param id The id of the Specialization.
     * @return the Response Entity with NO CONTENT response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialization(@PathVariable Long id) {
        specializationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates the existing Specialization record by using the given details.
     *
     * @param id             the id of the Specialization to be updated.
     * @param specialization the Specialization  object which contains the update details
     * @return the Response Entity with update Specialization details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Specialization> update(@PathVariable Long id, @Valid @RequestBody Specialization specialization) {
        Specialization updatedSpecialization = specializationService.update(id, specialization);
        return new ResponseEntity<>(updatedSpecialization, HttpStatus.OK);
    }
}
