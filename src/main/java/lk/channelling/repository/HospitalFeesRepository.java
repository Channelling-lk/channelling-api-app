package lk.channelling.repository;

import lk.channelling.entity.HospitalFees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalFeesRepository extends JpaRepository<HospitalFees, Long> {

}
