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
import lk.channelling.entity.Institution;
import lk.channelling.enums.Status;
import lk.channelling.services.InstitutionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to Institution entities.
 *
 * <p>This class serves as the entry point for handling requests related to Institution entities. It delegates the processing
 * to the {@link InstitutionService} and orchestrates the flow of data between the client, service layer, and underlying
 * data store. It may handle HTTP methods, request mapping , and model/view rendering in a web application context.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/institution")
@CrossOrigin(origins = "*")
@Log4j2
public class InstitutionController {

    /**
     * The Institution service for handling Institution related business logic.
     */
    private InstitutionService institutionService;

    /**
     * Injects the InstitutionService reference.
     *
     * @param institutionService The Institution service to be injected.
     */
    @Autowired
    public void setInstitutionService(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    /**
     * Handles HTTP Get requests to retrieve details of all Institutions.
     *
     * @return The details of all Institutions as a Response Entity.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Institution>> findAll() {
        List<Institution> Institutions = institutionService.findAll();

        if (Institutions.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(Institutions, HttpStatus.OK);
    }

    /**
     * Returns {@code Institution} by its id.
     *
     * @param id the id of the Institution.
     * @return the Response Entity with Institution.
     */
    @GetMapping("/id={id}")
    public ResponseEntity<Institution> findById(@PathVariable Long id) {
        Institution Institution = institutionService.findById(id);
        return new ResponseEntity<>(Institution, HttpStatus.OK);
    }

    /**
     * Returns the Institution by its code.
     *
     * @param code The code of the Institution.
     * @return The Response Entity with Institution object.
     */
    @GetMapping("/code={code}")
    public ResponseEntity<Institution> findByCode(@PathVariable String code) {
        Institution Institution = institutionService.findByCode(code);
        return new ResponseEntity<>(Institution, HttpStatus.OK);
    }

    /**
     * Returns the list of Institution by the given status.
     *
     * @param status The status of the Institution. It should be either ACTIVE or INACTIVE.
     * @return the List of Institutions.
     */
    @GetMapping("/status={status}")
    public ResponseEntity<List<Institution>> findByStatus(@PathVariable Status status) {
        List<Institution> Institutions = institutionService.findByStatus(status);

        if (Institutions.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(Institutions, HttpStatus.OK);
    }

    /**
     * Endpoint to save a new Institution.
     *
     * @param Institution The Institution object representing the Institution data to be saved.
     * @return ResponseEntity with the saved Institution and HTTP status.
     */
    @PostMapping("/save")
    public ResponseEntity<Institution> save(@Valid @RequestBody Institution Institution) {
        Institution savedInstitution = institutionService.save(Institution);
        return new ResponseEntity<>(savedInstitution, HttpStatus.CREATED);
    }

    /**
     * Deletes the Institution by the given Institution id.
     *
     * @param id The id of the Institution.
     * @return the Response Entity with NO CONTENT response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstitution(@PathVariable Long id) {
        institutionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates the existing Institution record by using the given details.
     *
     * @param id          the id of the Institution to be updated.
     * @param institution the Institution  object which contains the update details
     * @return the Response Entity with update Institution details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Institution> update(@PathVariable Long id, @Valid @RequestBody Institution institution) {
        Institution updatedInstitution = institutionService.update(id, institution);
        return new ResponseEntity<>(updatedInstitution, HttpStatus.OK);
    }

    @GetMapping("/country-id={countryId}")
    public ResponseEntity<List<Institution>> findByCountryId(@PathVariable Long countryId) {
        List<Institution> Institutions = institutionService.findByCountryId(countryId);

        if (Institutions.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(Institutions, HttpStatus.OK);
    }
}
