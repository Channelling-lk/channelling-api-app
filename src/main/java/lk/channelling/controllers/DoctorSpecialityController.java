package lk.channelling.controllers;

import lk.channelling.entity.DoctorSpeciality;
import lk.channelling.services.DoctorSpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor-specialities")
@CrossOrigin(origins = "*")
public class DoctorSpecialityController {

    private final DoctorSpecialityService doctorSpecialityService;

    @Autowired
    public DoctorSpecialityController(DoctorSpecialityService doctorSpecialityService) {
        this.doctorSpecialityService = doctorSpecialityService;
    }

    @GetMapping("/")
    public ResponseEntity<List<DoctorSpeciality>> getAllDoctorSpecialities() {
        List<DoctorSpeciality> doctorSpecialities = doctorSpecialityService.findAll();
        return ResponseEntity.ok().body(doctorSpecialities);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorSpeciality> getDoctorSpecialityById(@PathVariable Long id) {
        DoctorSpeciality doctorSpeciality = doctorSpecialityService.findById(id);
        return ResponseEntity.ok().body(doctorSpeciality);
    }

    @PostMapping("/")
    public ResponseEntity<DoctorSpeciality> createDoctorSpeciality(@RequestBody DoctorSpeciality doctorSpeciality) {
        DoctorSpeciality createdDoctorSpeciality = doctorSpecialityService.save(doctorSpeciality);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDoctorSpeciality);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorSpeciality> updateDoctorSpeciality(@PathVariable Long id, @RequestBody DoctorSpeciality doctorSpeciality) {
        DoctorSpeciality updatedDoctorSpeciality = doctorSpecialityService.update(id, doctorSpeciality);
        return ResponseEntity.ok().body(updatedDoctorSpeciality);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorSpeciality(@PathVariable Long id) {
        doctorSpecialityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
