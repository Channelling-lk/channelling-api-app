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
import lk.channelling.entity.Country;
import lk.channelling.services.CountryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for handling HTTP requests related to country entities.
 *
 * <p>This class serves as the entry point for handling requests related to country entities. It delegates the processing
 * to the {@link CountryService} and orchestrates the flow of data between the client, service layer, and underlying
 * data store. It may handle HTTP methods, request mapping , and model/view rendering in a web application context.</p>
 *
 * @author Chinthaka Manathunga
 * @verion 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/api/country")
@CrossOrigin(origins = "*")
@Log4j2
public class CountryController {

    /**
     * The country service for handling country related business logic.
     */
    private CountryService countryService;

    /**
     * Injects the CountryService reference.
     *
     * @param countryService The country service to be injected.
     */
    @Autowired
    public void setCountryService(CountryService countryService) {
        this.countryService = countryService;
    }

    /**
     * Handles HTTP Get requests to retrieve details of all countries.
     *
     * @return The details of all countries as a Response Entity.
     */
    @GetMapping(value = "/v1/all")
    public ResponseEntity<List<Country>> findAll() {
        List<Country> countries = countryService.findAll();

        if (countries.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(countries, HttpStatus.OK);
        }
    }

    @PostMapping("/v1")
    public ResponseEntity<Object> save(@Valid @RequestBody Country country) {

    }
}
