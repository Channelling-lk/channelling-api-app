package lk.channelling.controllers;

import lk.channelling.entity.HospitalFees;
import lk.channelling.services.HospitalFeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hospital-fees")
@CrossOrigin(origins = "*")
public class HospitalFeesController {

    private final HospitalFeesService hospitalFeesService;

    @Autowired
    public HospitalFeesController(HospitalFeesService hospitalFeesService) {
        this.hospitalFeesService = hospitalFeesService;
    }

    @GetMapping("/")
    public ResponseEntity<List<HospitalFees>> getAllHospitalFees() {
        List<HospitalFees> hospitalFeesList = hospitalFeesService.findAll();
        return ResponseEntity.ok().body(hospitalFeesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HospitalFees> getHospitalFeesById(@PathVariable Long id) {
        HospitalFees hospitalFees = hospitalFeesService.findById(id);
        return ResponseEntity.ok().body(hospitalFees);
    }

    @PostMapping("/")
    public ResponseEntity<HospitalFees> createHospitalFees(@RequestBody HospitalFees hospitalFees) {
        HospitalFees createdHospitalFees = hospitalFeesService.save(hospitalFees);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHospitalFees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HospitalFees> updateHospitalFees(@PathVariable Long id, @RequestBody HospitalFees hospitalFees) {
        HospitalFees updatedHospitalFees = hospitalFeesService.update(id, hospitalFees);
        return ResponseEntity.ok().body(updatedHospitalFees);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHospitalFees(@PathVariable Long id) {
        hospitalFeesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
