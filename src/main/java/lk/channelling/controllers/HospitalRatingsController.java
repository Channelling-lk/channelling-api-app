package lk.channelling.controllers;

import lk.channelling.entity.HospitalRatings;
import lk.channelling.services.HospitalRatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hospital-ratings")
@CrossOrigin(origins = "*")
public class HospitalRatingsController {

    private final HospitalRatingsService hospitalRatingsService;

    @Autowired
    public HospitalRatingsController(HospitalRatingsService hospitalRatingsService) {
        this.hospitalRatingsService = hospitalRatingsService;
    }

    @GetMapping("/")
    public ResponseEntity<List<HospitalRatings>> getAllHospitalRatings() {
        List<HospitalRatings> hospitalRatingsList = hospitalRatingsService.findAll();
        return ResponseEntity.ok().body(hospitalRatingsList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalRatings> getHospitalRatingsById(@PathVariable Long id) {
        HospitalRatings hospitalRatings = hospitalRatingsService.findById(id);
        return ResponseEntity.ok().body(hospitalRatings);
    }

    @PostMapping("/")
    public ResponseEntity<HospitalRatings> createHospitalRatings(@RequestBody HospitalRatings hospitalRatings) {
        HospitalRatings createdHospitalRatings = hospitalRatingsService.save(hospitalRatings);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHospitalRatings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospitalRatings> updateHospitalRatings(@PathVariable Long id, @RequestBody HospitalRatings hospitalRatings) {
        HospitalRatings updatedHospitalRatings = hospitalRatingsService.update(id, hospitalRatings);
        return ResponseEntity.ok().body(updatedHospitalRatings);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospitalRatings(@PathVariable Long id) {
        hospitalRatingsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
