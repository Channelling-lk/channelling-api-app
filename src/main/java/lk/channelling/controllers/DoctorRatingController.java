package lk.channelling.controllers;

import lk.channelling.entity.DoctorRating;
import lk.channelling.services.DoctorRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/doctor-ratings")
@CrossOrigin(origins = "*")
public class DoctorRatingController {

    private final DoctorRatingService doctorRatingService;

    @Autowired
    public DoctorRatingController(DoctorRatingService doctorRatingService) {
        this.doctorRatingService = doctorRatingService;
    }

    @GetMapping("/")
    public ResponseEntity<List<DoctorRating>> getAllDoctorRatings() {
        List<DoctorRating> doctorRatings = doctorRatingService.findAll();
        return ResponseEntity.ok().body(doctorRatings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorRating> getDoctorRatingById(@PathVariable Long id) {
        DoctorRating doctorRating = doctorRatingService.findById(id);
        return ResponseEntity.ok().body(doctorRating);
    }

    @PostMapping("/")
    public ResponseEntity<DoctorRating> createDoctorRating(@RequestBody DoctorRating doctorRating) {
        DoctorRating createdDoctorRating = doctorRatingService.save(doctorRating);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDoctorRating);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorRating> updateDoctorRating(@PathVariable Long id, @RequestBody DoctorRating doctorRating) {
        DoctorRating updatedDoctorRating = doctorRatingService.update(id, doctorRating);
        return ResponseEntity.ok().body(updatedDoctorRating);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorRating(@PathVariable Long id) {
        doctorRatingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
