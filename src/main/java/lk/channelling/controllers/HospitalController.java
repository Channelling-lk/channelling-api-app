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
import lk.channelling.entity.Hospital;
import lk.channelling.enums.Status;
import lk.channelling.services.HospitalService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to Hospital entities.
 *
 * <p>This class serves as the entry point for handling requests related to Hospital entities. It delegates the processing
 * to the {@link HospitalService} and orchestrates the flow of data between the client, service layer, and underlying
 * data store. It may handle HTTP methods, request mapping , and model/view rendering in a web application context.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/hospital")
@CrossOrigin(origins = "*")
@Log4j2
public class HospitalController {

    /**
     * The Hospital service for handling Hospital related business logic.
     */
    private HospitalService hospitalService;

    /**
     * Injects the HospitalService reference.
     *
     * @param hospitalService The Hospital service to be injected.
     */
    @Autowired
    public void setInstitutionService(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }

    /**
     * Handles HTTP Get requests to retrieve details of all hospitals.
     *
     * @return The details of all hospitals as a Response Entity.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Hospital>> findAll() {
        List<Hospital> hospitals = hospitalService.findAll();

        if (hospitals.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }

    /**
     * Returns {@code Hospital} by its id.
     *
     * @param id the id of the Hospital.
     * @return the Response Entity with Hospital.
     */
    @GetMapping("/id={id}")
    public ResponseEntity<Hospital> findById(@PathVariable Long id) {
        Hospital hospital = hospitalService.findById(id);
        return new ResponseEntity<>(hospital, HttpStatus.OK);
    }


    /**
     * Returns the list of Hospital by the given status.
     *
     * @param status The status of the Hospital. It should be either ACTIVE or INACTIVE.
     * @return the List of hospitals.
     */
    @GetMapping("/status={status}")
    public ResponseEntity<List<Hospital>> findByStatus(@PathVariable Status status) {
        List<Hospital> hospitals = hospitalService.findByStatus(status);

        if (hospitals.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }

    /**
     * Endpoint to save a new Hospital.
     *
     * @param hospital The Hospital object representing the Hospital data to be saved.
     * @return ResponseEntity with the saved Hospital and HTTP status.
     */
    @PostMapping("/save")
    public ResponseEntity<Hospital> save(@Valid @RequestBody Hospital hospital) {
        Hospital savedHospital = hospitalService.save(hospital);
        return new ResponseEntity<>(savedHospital, HttpStatus.CREATED);
    }

    /**
     * Deletes the Hospital by the given Hospital id.
     *
     * @param id The id of the Hospital.
     * @return the Response Entity with NO CONTENT response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstitution(@PathVariable Long id) {
        hospitalService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates the existing Hospital record by using the given details.
     *
     * @param id       the id of the Hospital to be updated.
     * @param hospital the Hospital  object which contains the update details
     * @return the Response Entity with update Hospital details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Hospital> update(@PathVariable Long id, @Valid @RequestBody Hospital hospital) {
        Hospital updatedHospital = hospitalService.update(id, hospital);
        return new ResponseEntity<>(updatedHospital, HttpStatus.OK);
    }

    @GetMapping("/city-id={cityId}")
    public ResponseEntity<List<Hospital>> findByStateId(@PathVariable Long cityId) {
        List<Hospital> hospitals = hospitalService.findByCityId(cityId);

        if (hospitals.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(hospitals, HttpStatus.OK);
    }
}
