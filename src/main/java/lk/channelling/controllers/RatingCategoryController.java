package lk.channelling.controllers;

import lk.channelling.entity.RatingCategory;
import lk.channelling.services.RatingCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rating-categories")
@CrossOrigin(origins = "*")
public class RatingCategoryController {

    private final RatingCategoryService ratingCategoryService;

    @Autowired
    public RatingCategoryController(RatingCategoryService ratingCategoryService) {
        this.ratingCategoryService = ratingCategoryService;
    }

    @GetMapping("/")
    public ResponseEntity<List<RatingCategory>> getAllRatingCategories() {
        List<RatingCategory> ratingCategoryList = ratingCategoryService.findAll();
        return ResponseEntity.ok().body(ratingCategoryList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RatingCategory> getRatingCategoryById(@PathVariable Long id) {
        RatingCategory ratingCategory = ratingCategoryService.findById(id);
        return ResponseEntity.ok().body(ratingCategory);
    }

    @PostMapping("/")
    public ResponseEntity<RatingCategory> createRatingCategory(@RequestBody RatingCategory ratingCategory) {
        RatingCategory createdRatingCategory = ratingCategoryService.save(ratingCategory);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRatingCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RatingCategory> updateRatingCategory(@PathVariable Long id, @RequestBody RatingCategory ratingCategory) {
        RatingCategory updatedRatingCategory = ratingCategoryService.update(id, ratingCategory);
        return ResponseEntity.ok().body(updatedRatingCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRatingCategory(@PathVariable Long id) {
        ratingCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
