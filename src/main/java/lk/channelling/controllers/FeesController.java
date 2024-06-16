package lk.channelling.controllers;

import lk.channelling.entity.Fees;
import lk.channelling.services.FeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fees")
@CrossOrigin(origins = "*")
public class FeesController {

    private final FeesService feesService;

    @Autowired
    public FeesController(FeesService feesService) {
        this.feesService = feesService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Fees>> getAllFees() {
        List<Fees> feesList = feesService.findAll();
        return ResponseEntity.ok().body(feesList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fees> getFeesById(@PathVariable Long id) {
        Fees fees = feesService.findById(id);
        return ResponseEntity.ok().body(fees);
    }

    @PostMapping("/")
    public ResponseEntity<Fees> createFees(@RequestBody Fees fees) {
        Fees createdFees = feesService.save(fees);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fees> updateFees(@PathVariable Long id, @RequestBody Fees fees) {
        Fees updatedFees = feesService.update(id, fees);
        return ResponseEntity.ok().body(updatedFees);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFees(@PathVariable Long id) {
        feesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
