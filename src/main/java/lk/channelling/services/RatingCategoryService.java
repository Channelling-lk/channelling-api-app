package lk.channelling.services;

import lk.channelling.entity.RatingCategory;

import java.util.List;

public interface RatingCategoryService {

    List<RatingCategory> findAll();

    RatingCategory findById(Long id);

    RatingCategory save(RatingCategory ratingCategory);

    void delete(Long id);

    RatingCategory update(Long id, RatingCategory ratingCategory);
}
