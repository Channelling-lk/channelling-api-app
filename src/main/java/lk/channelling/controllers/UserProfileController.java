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
import lk.channelling.entity.UserProfile;
import lk.channelling.enums.Status;
import lk.channelling.services.UserProfileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to userProfile entities.
 *
 * <p>This class serves as the entry point for handling requests related to userProfile entities. It delegates the processing
 * to the {@link UserProfileService} and orchestrates the flow of data between the client, service layer, and underlying
 * data store. It may handle HTTP methods, request mapping , and model/view rendering in a web application context.</p>
 *
 * @author Chinthaka Manathunga
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/v1/user-profile")
@CrossOrigin(origins = "*")
@Log4j2
public class UserProfileController {

    /**
     * The userProfile service for handling userProfile related business logic.
     */
    private UserProfileService userProfileService;

    /**
     * Injects the UserProfileService reference.
     *
     * @param userProfileService The userProfile service to be injected.
     */
    @Autowired
    public void setUserProfileService(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    /**
     * Handles HTTP Get requests to retrieve details of all countries.
     *
     * @return The details of all countries as a Response Entity.
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserProfile>> findAll() {
        List<UserProfile> countries = userProfileService.findAll();

        if (countries.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    /**
     * Returns {@code UserProfile} by its id.
     *
     * @param id the id of the userProfile.
     * @return the Response Entity with userProfile.
     */
    @GetMapping("/id={id}")
    public ResponseEntity<UserProfile> findById(@PathVariable Long id) {
        UserProfile userProfile = userProfileService.findById(id);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }


    /**
     * Returns the list of userProfile by the given status.
     *
     * @param status The status of the userProfile. It should be either ACTIVE or INACTIVE.
     * @return the List of countries.
     */
    @GetMapping("/status={status}")
    public ResponseEntity<List<UserProfile>> findByStatus(@PathVariable Status status) {
        List<UserProfile> countries = userProfileService.findByStatus(status);

        if (countries.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    /**
     * Endpoint to save a new userProfile.
     *
     * @param userProfile The userProfile object representing the userProfile data to be saved.
     * @return ResponseEntity with the saved userProfile and HTTP status.
     */
    @PostMapping("/save")
    public ResponseEntity<UserProfile> save(@Valid @RequestBody UserProfile userProfile) {
        UserProfile savedUserProfile = userProfileService.save(userProfile);
        return new ResponseEntity<>(savedUserProfile, HttpStatus.CREATED);
    }

    /**
     * Deletes the userProfile by the given userProfile id.
     *
     * @param id The id of the userProfile.
     * @return the Response Entity with NO CONTENT response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserProfile(@PathVariable Long id) {
        userProfileService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Updates the existing userProfile record by using the given details.
     *
     * @param id          the id of the userProfile to be updated.
     * @param userProfile the userProfile  object which contains the update details
     * @return the Response Entity with update userProfile details.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserProfile> update(@PathVariable Long id, @Valid @RequestBody UserProfile userProfile) {
        UserProfile updatedUserProfile = userProfileService.update(id, userProfile);
        return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
    }
}
