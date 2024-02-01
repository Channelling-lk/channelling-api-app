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
import lk.channelling.entity.TransactionType;
import lk.channelling.enums.Status;
import lk.channelling.services.TransactionTypeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to TransactionType entities.
 *
 * <p>This class serves as the entry point for handling requests related to TransactionType entities. It delegates the processing
 * to the {@link TransactionTypeService} and orchestrates the flow of data between the client, service layer, and underlying
 * data store. It may handle HTTP methods, request mapping , and model/view rendering in a web application context.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/transaction-type")
@CrossOrigin(origins = "*")
@Log4j2
public class TransactionTypeController {

    /**
     * The TransactionType service for handling TransactionType related business logic.
     */
    private TransactionTypeService transactionTypeService;

    /**
     * Injects the TransactionTypeService reference.
     *
     * @param transactionTypeService The TransactionType service to be injected.
     */
    @Autowired
    public void setTransactionTypeService(TransactionTypeService transactionTypeService) {
        this.transactionTypeService = transactionTypeService;
    }

    /**
     * Handles HTTP Get requests to retrieve details of all countries.
     *
     * @return The details of all countries as a Response Entity.
     */
    @GetMapping("/all")
    public ResponseEntity<List<TransactionType>> findAll() {
        List<TransactionType> countries = transactionTypeService.findAll();

        if (countries.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    /**
     * Returns {@code TransactionType} by its id.
     *
     * @param id the id of the TransactionType.
     * @return the Response Entity with TransactionType.
     */
    @GetMapping("/id={id}")
    public ResponseEntity<TransactionType> findById(@PathVariable Long id) {
        TransactionType TransactionType = transactionTypeService.findById(id);
        return new ResponseEntity<>(TransactionType, HttpStatus.OK);
    }

    /**
     * Returns the TransactionType by its code.
     *
     * @param code The code of the TransactionType.
     * @return The Response Entity with TransactionType object.
     */
    @GetMapping("/code={code}")
    public ResponseEntity<TransactionType> findByCode(@PathVariable String code) {
        TransactionType TransactionType = transactionTypeService.findByCode(code);
        return new ResponseEntity<>(TransactionType, HttpStatus.OK);
    }

    /**
     * Returns the list of TransactionType by the given status.
     *
     * @param status The status of the TransactionType. It should be either ACTIVE or INACTIVE.
     * @return the List of countries.
     */
    @GetMapping("/status={status}")
    public ResponseEntity<List<TransactionType>> findByStatus(@PathVariable Status status) {
        List<TransactionType> countries = transactionTypeService.findByStatus(status);

        if (countries.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    /**
     * Endpoint to save a new TransactionType.
     *
     * @param TransactionType The TransactionType object representing the TransactionType data to be saved.
     * @return ResponseEntity with the saved TransactionType and HTTP status.
     */
    @PostMapping("/save")
    public ResponseEntity<TransactionType> save(@Valid @RequestBody TransactionType TransactionType) {
        TransactionType savedTransactionType = transactionTypeService.save(TransactionType);
        return new ResponseEntity<>(savedTransactionType, HttpStatus.CREATED);
    }

    /**
     * Deletes the TransactionType by the given TransactionType id.
     *
     * @param id The id of the TransactionType.
     * @return the Response Entity with NO CONTENT response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransactionType(@PathVariable Long id) {
        transactionTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates the existing TransactionType record by using the given details.
     *
     * @param id              the id of the TransactionType to be updated.
     * @param TransactionType the TransactionType  object which contains the update details
     * @return the Response Entity with update TransactionType details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TransactionType> update(@PathVariable Long id, @Valid @RequestBody TransactionType TransactionType) {
        TransactionType updatedTransactionType = transactionTypeService.update(id, TransactionType);
        return new ResponseEntity<>(updatedTransactionType, HttpStatus.OK);
    }
}
