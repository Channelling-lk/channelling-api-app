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

import lk.channelling.entity.DoctorSession;
import lk.channelling.services.DoctorSessionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor-sessions")
@CrossOrigin(origins = "*")
@Log4j2
public class DoctorSessionController {

    private final DoctorSessionService doctorSessionService;

    @Autowired
    public DoctorSessionController(DoctorSessionService doctorSessionService) {
        this.doctorSessionService = doctorSessionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DoctorSession>> findAll() {
        List<DoctorSession> doctorSessions = doctorSessionService.findAll();
        if (doctorSessions.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(doctorSessions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorSession> findById(@PathVariable Long id) {
        DoctorSession doctorSession = doctorSessionService.findById(id);
        return new ResponseEntity<>(doctorSession, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<DoctorSession> save(@RequestBody DoctorSession doctorSession) {
        DoctorSession savedDoctorSession = doctorSessionService.save(doctorSession);
        return new ResponseEntity<>(savedDoctorSession, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorSessionService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorSession> update(@PathVariable Long id, @RequestBody DoctorSession doctorSession) {
        DoctorSession updatedDoctorSession = doctorSessionService.update(id, doctorSession);
        return new ResponseEntity<>(updatedDoctorSession, HttpStatus.OK);
    }
}
