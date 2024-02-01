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
import lk.channelling.entity.Title;
import lk.channelling.enums.Status;
import lk.channelling.services.TitleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to title entities.
 *
 * <p>This class serves as the entry point for handling requests related to title entities. It delegates the processing
 * to the {@link TitleService} and orchestrates the flow of data between the client, service layer, and underlying
 * data store. It may handle HTTP methods, request mapping , and model/view rendering in a web application context.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/titles")
@CrossOrigin(origins = "*")
@Log4j2
public class TitleController {

    /**
     * The title service for handling title related business logic.
     */
    private TitleService titleService;

    /**
     * Injects the TitleService reference.
     *
     * @param titleService The title service to be injected.
     */
    @Autowired
    public void setTitleService(TitleService titleService) {
        this.titleService = titleService;
    }

    /**
     * Handles HTTP Get requests to retrieve details of all countries.
     *
     * @return The details of all countries as a Response Entity.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Title>> findAll() {
        List<Title> countries = titleService.findAll();

        if (countries.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    /**
     * Returns {@code Title} by its id.
     *
     * @param id the id of the title.
     * @return the Response Entity with title.
     */
    @GetMapping("/id={id}")
    public ResponseEntity<Title> findById(@PathVariable Long id) {
        Title title = titleService.findById(id);
        return new ResponseEntity<>(title, HttpStatus.OK);
    }

    /**
     * Returns the title by its code.
     *
     * @param code The code of the title.
     * @return The Response Entity with title object.
     */
    @GetMapping("/code={code}")
    public ResponseEntity<Title> findByCode(@PathVariable String code) {
        Title title = titleService.findByCode(code);
        return new ResponseEntity<>(title, HttpStatus.OK);
    }

    /**
     * Returns the list of title by the given status.
     *
     * @param status The status of the title. It should be either ACTIVE or INACTIVE.
     * @return the List of countries.
     */
    @GetMapping("/status={status}")
    public ResponseEntity<List<Title>> findByStatus(@PathVariable Status status) {
        List<Title> countries = titleService.findByStatus(status);

        if (countries.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    /**
     * Endpoint to save a new title.
     *
     * @param title The title object representing the title data to be saved.
     * @return ResponseEntity with the saved title and HTTP status.
     */
    @PostMapping("/save")
    public ResponseEntity<Title> save(@Valid @RequestBody Title title) {
        Title savedTitle = titleService.save(title);
        return new ResponseEntity<>(savedTitle, HttpStatus.CREATED);
    }

    /**
     * Deletes the title by the given title id.
     *
     * @param id The id of the title.
     * @return the Response Entity with NO CONTENT response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTitle(@PathVariable Long id) {
        titleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates the existing title record by using the given details.
     *
     * @param id    the id of the title to be updated.
     * @param title the title  object which contains the update details
     * @return the Response Entity with update title details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Title> update(@PathVariable Long id, @Valid @RequestBody Title title) {
        Title updatedTitle = titleService.update(id, title);
        return new ResponseEntity<>(updatedTitle, HttpStatus.OK);
    }
}
