package lk.channelling.repository;

import lk.channelling.entity.HospitalRatings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRatingsRepository extends JpaRepository<HospitalRatings, Long> {
    // Add custom query methods if needed
}
