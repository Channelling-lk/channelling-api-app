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
import lk.channelling.entity.QualificationLevel;
import lk.channelling.enums.Status;
import lk.channelling.services.QualificationLevelService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to QualificationLevel entities.
 *
 * <p>This class serves as the entry point for handling requests related to QualificationLevel entities. It delegates the processing
 * to the {@link QualificationLevelService} and orchestrates the flow of data between the client, service layer, and underlying
 * data store. It may handle HTTP methods, request mapping , and model/view rendering in a web application context.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/qualification-level")
@CrossOrigin(origins = "*")
@Log4j2
public class QualificationLevelController {

    /**
     * The QualificationLevel service for handling QualificationLevel related business logic.
     */
    private QualificationLevelService qualificationLevelService;

    /**
     * Injects the QualificationLevelService reference.
     *
     * @param qualificationLevelService The QualificationLevel service to be injected.
     */
    @Autowired
    public void setQualificationLevelService(QualificationLevelService qualificationLevelService) {
        this.qualificationLevelService = qualificationLevelService;
    }

    /**
     * Handles HTTP Get requests to retrieve details of all countries.
     *
     * @return The details of all countries as a Response Entity.
     */
    @GetMapping("/all")
    public ResponseEntity<List<QualificationLevel>> findAll() {
        List<QualificationLevel> countries = qualificationLevelService.findAll();

        if (countries.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    /**
     * Returns {@code QualificationLevel} by its id.
     *
     * @param id the id of the QualificationLevel.
     * @return the Response Entity with QualificationLevel.
     */
    @GetMapping("/id={id}")
    public ResponseEntity<QualificationLevel> findById(@PathVariable Long id) {
        QualificationLevel QualificationLevel = qualificationLevelService.findById(id);
        return new ResponseEntity<>(QualificationLevel, HttpStatus.OK);
    }

    /**
     * Returns the QualificationLevel by its code.
     *
     * @param code The code of the QualificationLevel.
     * @return The Response Entity with QualificationLevel object.
     */
    @GetMapping("/code={code}")
    public ResponseEntity<QualificationLevel> findByCode(@PathVariable String code) {
        QualificationLevel QualificationLevel = qualificationLevelService.findByCode(code);
        return new ResponseEntity<>(QualificationLevel, HttpStatus.OK);
    }

    /**
     * Returns the list of QualificationLevel by the given status.
     *
     * @param status The status of the QualificationLevel. It should be either ACTIVE or INACTIVE.
     * @return the List of countries.
     */
    @GetMapping("/status={status}")
    public ResponseEntity<List<QualificationLevel>> findByStatus(@PathVariable Status status) {
        List<QualificationLevel> countries = qualificationLevelService.findByStatus(status);

        if (countries.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    /**
     * Endpoint to save a new QualificationLevel.
     *
     * @param QualificationLevel The QualificationLevel object representing the QualificationLevel data to be saved.
     * @return ResponseEntity with the saved QualificationLevel and HTTP status.
     */
    @PostMapping("/save")
    public ResponseEntity<QualificationLevel> save(@Valid @RequestBody QualificationLevel QualificationLevel) {
        QualificationLevel savedQualificationLevel = qualificationLevelService.save(QualificationLevel);
        return new ResponseEntity<>(savedQualificationLevel, HttpStatus.CREATED);
    }

    /**
     * Deletes the QualificationLevel by the given QualificationLevel id.
     *
     * @param id The id of the QualificationLevel.
     * @return the Response Entity with NO CONTENT response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQualificationLevel(@PathVariable Long id) {
        qualificationLevelService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates the existing QualificationLevel record by using the given details.
     *
     * @param id                 the id of the QualificationLevel to be updated.
     * @param QualificationLevel the QualificationLevel  object which contains the update details
     * @return the Response Entity with update QualificationLevel details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<QualificationLevel> update(@PathVariable Long id, @Valid @RequestBody QualificationLevel QualificationLevel) {
        QualificationLevel updatedQualificationLevel = qualificationLevelService.update(id, QualificationLevel);
        return new ResponseEntity<>(updatedQualificationLevel, HttpStatus.OK);
    }
}
