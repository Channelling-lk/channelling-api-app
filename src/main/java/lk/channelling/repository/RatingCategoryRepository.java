package lk.channelling.repository;

import lk.channelling.entity.RatingCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingCategoryRepository extends JpaRepository<RatingCategory, Long> {
    // Add custom query methods if needed
}
