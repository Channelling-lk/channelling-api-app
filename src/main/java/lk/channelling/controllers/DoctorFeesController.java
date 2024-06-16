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

import lk.channelling.entity.DoctorFees;
import lk.channelling.services.DoctorFeesService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor-fees")
@CrossOrigin(origins = "*")
@Log4j2
public class DoctorFeesController {

    private final DoctorFeesService doctorFeesService;

    @Autowired
    public DoctorFeesController(DoctorFeesService doctorFeesService) {
        this.doctorFeesService = doctorFeesService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DoctorFees>> findAll() {
        List<DoctorFees> doctorFeesList = doctorFeesService.findAll();
        if (doctorFeesList.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(doctorFeesList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorFees> findById(@PathVariable Long id) {
        DoctorFees doctorFees = doctorFeesService.findById(id);
        return new ResponseEntity<>(doctorFees, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<DoctorFees> save(@RequestBody DoctorFees doctorFees) {
        DoctorFees savedDoctorFees = doctorFeesService.save(doctorFees);
        return new ResponseEntity<>(savedDoctorFees, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorFeesService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorFees> update(@PathVariable Long id, @RequestBody DoctorFees doctorFees) {
        DoctorFees updatedDoctorFees = doctorFeesService.update(id, doctorFees);
        return new ResponseEntity<>(updatedDoctorFees, HttpStatus.OK);
    }
}
