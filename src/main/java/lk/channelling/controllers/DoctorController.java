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
import lk.channelling.entity.Doctor;
import lk.channelling.enums.Status;
import lk.channelling.resources.ApiResponse;
import lk.channelling.services.DoctorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to Doctor entities.
 *
 * <p>This class serves as the entry point for handling requests related to Doctor entities. It delegates the processing
 * to the {@link DoctorService} and orchestrates the flow of data between the client, service layer, and underlying
 * data store. It may handle HTTP methods, request mapping , and model/view rendering in a web application context.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/doctor")
@CrossOrigin(origins = "*")
@Log4j2
public class DoctorController {

    /**
     * The Doctor service for handling Doctor related business logic.
     */
    private DoctorService doctorService;

    /**
     * Injects the DoctorService reference.
     *
     * @param doctorService The Doctor service to be injected.
     */
    @Autowired
    public void setInstitutionService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * Handles HTTP Get requests to retrieve details of all doctors.
     *
     * @return The details of all doctors as a Response Entity.
     */
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> findAll() {
        ApiResponse doctors = doctorService.findAll();

        if (doctors == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    /**
     * Returns {@code Doctor} by its id.
     *
     * @param id the id of the Doctor.
     * @return the Response Entity with Doctor.
     */
    @GetMapping("/id={id}")
    public ResponseEntity<Doctor> findById(@PathVariable Long id) {
        Doctor doctor = doctorService.findById(id);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }


    /**
     * Returns the list of Doctor by the given status.
     *
     * @param status The status of the Doctor. It should be either ACTIVE or INACTIVE.
     * @return the List of doctors.
     */
    @GetMapping("/status={status}")
    public ResponseEntity<List<Doctor>> findByStatus(@PathVariable Status status) {
        List<Doctor> doctors = doctorService.findByStatus(status);

        if (doctors.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    /**
     * Endpoint to save a new Doctor.
     *
     * @param doctor The Doctor object representing the Doctor data to be saved.
     * @return ResponseEntity with the saved Doctor and HTTP status.
     */
    @PostMapping("/save")
    public ResponseEntity<Doctor> save(@Valid @RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.save(doctor);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    /**
     * Deletes the Doctor by the given Doctor id.
     *
     * @param id The id of the Doctor.
     * @return the Response Entity with NO CONTENT response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstitution(@PathVariable Long id) {
        doctorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates the existing Doctor record by using the given details.
     *
     * @param id     the id of the Doctor to be updated.
     * @param doctor the Doctor  object which contains the update details
     * @return the Response Entity with update Doctor details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> update(@PathVariable Long id, @Valid @RequestBody Doctor doctor) {
        Doctor updatedDoctor = doctorService.update(id, doctor);
        return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
    }

}
