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
import lk.channelling.entity.State;
import lk.channelling.enums.Status;
import lk.channelling.services.CityService;
import lk.channelling.services.StateService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to State entities.
 *
 * <p>This class serves as the entry point for handling requests related to State entities. It delegates the processing
 * to the {@link CityService} and orchestrates the flow of data between the client, service layer, and underlying
 * data store. It may handle HTTP methods, request mapping , and model/view rendering in a web application context.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/state")
@CrossOrigin(origins = "*")
@Log4j2
public class StateController {

    /**
     * The State service for handling State related business logic.
     */
    private StateService stateService;

    /**
     * Injects the CityService reference.
     *
     * @param stateService The State service to be injected.
     */
    @Autowired
    public void setStateService(StateService stateService) {
        this.stateService = stateService;
    }

    /**
     * Handles HTTP Get requests to retrieve details of all states.
     *
     * @return The details of all states as a Response Entity.
     */
    @GetMapping("/all")
    public ResponseEntity<List<State>> findAll() {
        List<State> states = stateService.findAll();

        if (states.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(states, HttpStatus.OK);
    }

    /**
     * Returns {@code State} by its id.
     *
     * @param id the id of the State.
     * @return the Response Entity with State.
     */
    @GetMapping("/id={id}")
    public ResponseEntity<State> findById(@PathVariable Long id) {
        State State = stateService.findById(id);
        return new ResponseEntity<>(State, HttpStatus.OK);
    }


    /**
     * Returns the list of State by the given status.
     *
     * @param status The status of the State. It should be either ACTIVE or INACTIVE.
     * @return the List of states.
     */
    @GetMapping("/status={status}")
    public ResponseEntity<List<State>> findByStatus(@PathVariable Status status) {
        List<State> states = stateService.findByStatus(status);

        if (states.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(states, HttpStatus.OK);
    }

    /**
     * Endpoint to save a new State.
     *
     * @param State The State object representing the State data to be saved.
     * @return ResponseEntity with the saved State and HTTP status.
     */
    @PostMapping("/save")
    public ResponseEntity<State> save(@Valid @RequestBody State State) {
        State savedInstitution = stateService.save(State);
        return new ResponseEntity<>(savedInstitution, HttpStatus.CREATED);
    }

    /**
     * Deletes the State by the given State id.
     *
     * @param id The id of the State.
     * @return the Response Entity with NO CONTENT response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstitution(@PathVariable Long id) {
        stateService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates the existing State record by using the given details.
     *
     * @param id          the id of the State to be updated.
     * @param institution the State  object which contains the update details
     * @return the Response Entity with update State details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<State> update(@PathVariable Long id, @Valid @RequestBody State institution) {
        State updatedInstitution = stateService.update(id, institution);
        return new ResponseEntity<>(updatedInstitution, HttpStatus.OK);
    }

    @GetMapping("/country-id={countryId}")
    public ResponseEntity<List<State>> findByCountryId(@PathVariable Long countryId) {
        List<State> states = stateService.findByCountryId(countryId);

        if (states.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(states, HttpStatus.OK);
    }
}
