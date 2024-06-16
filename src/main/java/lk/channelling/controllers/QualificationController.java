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
import lk.channelling.entity.Qualification;
import lk.channelling.enums.Status;
import lk.channelling.services.QualificationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to Qualification entities.
 *
 * <p>This class serves as the entry point for handling requests related to Qualification entities. It delegates the processing
 * to the {@link QualificationService} and orchestrates the flow of data between the client, service layer, and underlying
 * data store. It may handle HTTP methods, request mapping , and model/view rendering in a web application context.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/qualification")
@CrossOrigin(origins = "*")
@Log4j2
public class QualificationController {

    /**
     * The Qualification service for handling Qualification related business logic.
     */
    private QualificationService qualificationService;

    /**
     * Injects the QualificationService reference.
     *
     * @param qualificationService The Qualification service to be injected.
     */
    @Autowired
    public void setQualificationService(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    /**
     * Handles HTTP Get requests to retrieve details of all Qualifications.
     *
     * @return The details of all Qualifications as a Response Entity.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Qualification>> findAll() {
        List<Qualification> Qualifications = qualificationService.findAll();

        if (Qualifications.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(Qualifications, HttpStatus.OK);
    }

    /**
     * Returns {@code Qualification} by its id.
     *
     * @param id the id of the Qualification.
     * @return the Response Entity with Qualification.
     */
    @GetMapping("/id={id}")
    public ResponseEntity<Qualification> findById(@PathVariable Long id) {
        Qualification Qualification = qualificationService.findById(id);
        return new ResponseEntity<>(Qualification, HttpStatus.OK);
    }

    /**
     * Returns the Qualification by its code.
     *
     * @param code The code of the Qualification.
     * @return The Response Entity with Qualification object.
     */
    @GetMapping("/code={code}")
    public ResponseEntity<Qualification> findByCode(@PathVariable String code) {
        Qualification Qualification = qualificationService.findByCode(code);
        return new ResponseEntity<>(Qualification, HttpStatus.OK);
    }

    /**
     * Returns the list of Qualification by the given status.
     *
     * @param status The status of the Qualification. It should be either ACTIVE or INACTIVE.
     * @return the List of Qualifications.
     */
    @GetMapping("/status={status}")
    public ResponseEntity<List<Qualification>> findByStatus(@PathVariable Status status) {
        List<Qualification> Qualifications = qualificationService.findByStatus(status);

        if (Qualifications.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(Qualifications, HttpStatus.OK);
    }

    /**
     * Endpoint to save a new Qualification.
     *
     * @param qualification The Qualification object representing the Qualification data to be saved.
     * @return ResponseEntity with the saved Qualification and HTTP status.
     */
    @PostMapping("/save")
    public ResponseEntity<Qualification> save(@Valid @RequestBody Qualification qualification) {
        Qualification savedQualification = qualificationService.save(qualification);
        return new ResponseEntity<>(savedQualification, HttpStatus.CREATED);
    }

    /**
     * Deletes the Qualification by the given Qualification id.
     *
     * @param id The id of the Qualification.
     * @return the Response Entity with NO CONTENT response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualification(@PathVariable Long id) {
        qualificationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates the existing Qualification record by using the given details.
     *
     * @param id            the id of the Qualification to be updated.
     * @param qualification the Qualification  object which contains the update details
     * @return the Response Entity with update Qualification details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Qualification> update(@PathVariable Long id, @Valid @RequestBody Qualification qualification) {
        Qualification updatedQualification = qualificationService.update(id, qualification);
        return new ResponseEntity<>(updatedQualification, HttpStatus.OK);
    }

    @GetMapping("/qualification-level-id={qualificationLevelId}")
    public ResponseEntity<List<Qualification>> findByQualificationLevelid(@PathVariable Long qualificationLevelId) {
        List<Qualification> Qualifications = qualificationService.findByQualificationLevelId(qualificationLevelId);

        if (Qualifications.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(Qualifications, HttpStatus.OK);
    }
}
