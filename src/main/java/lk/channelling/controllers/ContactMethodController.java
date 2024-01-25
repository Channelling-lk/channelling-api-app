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
import lk.channelling.entity.ContactMethod;
import lk.channelling.enums.Status;
import lk.channelling.services.ContactMethodService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to ContactMethod entities.
 *
 * <p>This class serves as the entry point for handling requests related to ContactMethod entities. It delegates the processing
 * to the {@link ContactMethodService} and orchestrates the flow of data between the client, service layer, and underlying
 * data store. It may handle HTTP methods, request mapping , and model/view rendering in a web application context.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/contact-method")
@CrossOrigin(origins = "*")
@Log4j2
public class ContactMethodController {

    /**
     * The ContactMethod service for handling ContactMethod related business logic.
     */
    private ContactMethodService contactMethodService;

    /**
     * Injects the ContactMethodService reference.
     *
     * @param contactMethodService The ContactMethod service to be injected.
     */
    @Autowired
    public void setContactMethodService(ContactMethodService contactMethodService) {
        this.contactMethodService = contactMethodService;
    }

    /**
     * Handles HTTP Get requests to retrieve details of all countries.
     *
     * @return The details of all countries as a Response Entity.
     */
    @GetMapping("/all")
    public ResponseEntity<List<ContactMethod>> findAll() {
        List<ContactMethod> countries = contactMethodService.findAll();

        if (countries.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    /**
     * Returns {@code ContactMethod} by its id.
     *
     * @param id the id of the ContactMethod.
     * @return the Response Entity with ContactMethod.
     */
    @GetMapping("/id={id}")
    public ResponseEntity<ContactMethod> findById(@PathVariable Long id) {
        ContactMethod ContactMethod = contactMethodService.findById(id);
        return new ResponseEntity<>(ContactMethod, HttpStatus.OK);
    }

    /**
     * Returns the ContactMethod by its code.
     *
     * @param code The code of the ContactMethod.
     * @return The Response Entity with ContactMethod object.
     */
    @GetMapping("/code={code}")
    public ResponseEntity<ContactMethod> findByCode(@PathVariable String code) {
        ContactMethod ContactMethod = contactMethodService.findByCode(code);
        return new ResponseEntity<>(ContactMethod, HttpStatus.OK);
    }

    /**
     * Returns the list of ContactMethod by the given status.
     *
     * @param status The status of the ContactMethod. It should be either ACTIVE or INACTIVE.
     * @return the List of countries.
     */
    @GetMapping("/status={status}")
    public ResponseEntity<List<ContactMethod>> findByStatus(@PathVariable Status status) {
        List<ContactMethod> countries = contactMethodService.findByStatus(status);

        if (countries.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    /**
     * Endpoint to save a new ContactMethod.
     *
     * @param ContactMethod The ContactMethod object representing the ContactMethod data to be saved.
     * @return ResponseEntity with the saved ContactMethod and HTTP status.
     */
    @PostMapping("/save")
    public ResponseEntity<ContactMethod> save(@Valid @RequestBody ContactMethod ContactMethod) {
        ContactMethod savedContactMethod = contactMethodService.save(ContactMethod);
        return new ResponseEntity<>(savedContactMethod, HttpStatus.OK);
    }

    /**
     * Deletes the ContactMethod by the given ContactMethod id.
     *
     * @param id The id of the ContactMethod.
     * @return the Response Entity with NO CONTENT response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactMethod(@PathVariable Long id) {
        contactMethodService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates the existing ContactMethod record by using the given details.
     *
     * @param id            the id of the ContactMethod to be updated.
     * @param ContactMethod the ContactMethod  object which contains the update details
     * @return the Response Entity with update ContactMethod details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ContactMethod> update(@PathVariable Long id, @Valid @RequestBody ContactMethod ContactMethod) {
        ContactMethod updatedContactMethod = contactMethodService.update(id, ContactMethod);
        return new ResponseEntity<>(updatedContactMethod, HttpStatus.OK);
    }
}
