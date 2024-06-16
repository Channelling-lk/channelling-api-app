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

import lk.channelling.entity.Patient;
import lk.channelling.services.PatientService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
@CrossOrigin(origins = "*")
@Log4j2
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> findAll() {
        List<Patient> patients = patientService.findAll();
        if (patients.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable Long id) {
        Patient patient = patientService.findById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<Patient> save(@RequestBody Patient patient) {
        Patient savedPatient = patientService.save(patient);
        return new ResponseEntity<>(savedPatient, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        patientService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id, @RequestBody Patient patient) {
        Patient updatedPatient = patientService.update(id, patient);
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }
}
