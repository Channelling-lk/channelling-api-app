package lk.channelling.services.impl;

import lk.channelling.entity.RatingCategory;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.repository.RatingCategoryRepository;
import lk.channelling.services.RatingCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class RatingCategoryServiceImpl implements RatingCategoryService {

    private final RatingCategoryRepository ratingCategoryRepository;

    @Autowired
    public RatingCategoryServiceImpl(RatingCategoryRepository ratingCategoryRepository) {
        this.ratingCategoryRepository = ratingCategoryRepository;
    }

    @Override
    public List<RatingCategory> findAll() {
        return ratingCategoryRepository.findAll();
    }

    @Override
    public RatingCategory findById(Long id) {
        Optional<RatingCategory> ratingCategory = ratingCategoryRepository.findById(id);
        if (ratingCategory.isEmpty()) {
            throw new RecordNotFoundException("Rating category not found with id: " + id);
        }
        return ratingCategory.get();
    }

    @Override
    public RatingCategory save(RatingCategory ratingCategory) {
        return ratingCategoryRepository.save(ratingCategory);
    }

    @Override
    public void delete(Long id) {
        ratingCategoryRepository.deleteById(id);
    }

    @Override
    public RatingCategory update(Long id, RatingCategory ratingCategory) {
        Optional<RatingCategory> existingRatingCategory = ratingCategoryRepository.findById(id);
        if (existingRatingCategory.isEmpty()) {
            throw new RecordNotFoundException("Rating category not found with id: " + id);
        }

        RatingCategory updatedRatingCategory = existingRatingCategory.get();
        updatedRatingCategory.setCategoryName(ratingCategory.getCategoryName());

        return ratingCategoryRepository.save(updatedRatingCategory);
    }
}
