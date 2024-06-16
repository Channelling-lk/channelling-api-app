package lk.channelling.controllers;

import lk.channelling.entity.DoctorQualification;
import lk.channelling.services.DoctorQualificationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor-qualifications")
@CrossOrigin(origins = "*")
@Log4j2
public class DoctorQualificationController {

    private final DoctorQualificationService doctorQualificationService;

    @Autowired
    public DoctorQualificationController(DoctorQualificationService doctorQualificationService) {
        this.doctorQualificationService = doctorQualificationService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DoctorQualification>> findAll() {
        List<DoctorQualification> doctorQualifications = doctorQualificationService.findAll();
        if (doctorQualifications.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(doctorQualifications, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorQualification> findById(@PathVariable Long id) {
        DoctorQualification doctorQualification = doctorQualificationService.findById(id);
        return new ResponseEntity<>(doctorQualification, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<DoctorQualification> save(@RequestBody DoctorQualification doctorQualification) {
        DoctorQualification savedDoctorQualification = doctorQualificationService.save(doctorQualification);
        return new ResponseEntity<>(savedDoctorQualification, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorQualificationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorQualification> update(@PathVariable Long id, @RequestBody DoctorQualification doctorQualification) {
        DoctorQualification updatedDoctorQualification = doctorQualificationService.update(id, doctorQualification);
        return new ResponseEntity<>(updatedDoctorQualification, HttpStatus.OK);
    }
}
