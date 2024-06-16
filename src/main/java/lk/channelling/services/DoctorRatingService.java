package lk.channelling.services;

import lk.channelling.entity.DoctorRating;

import java.util.List;

public interface DoctorRatingService {

    List<DoctorRating> findAll();

    DoctorRating findById(Long id);

    DoctorRating save(DoctorRating doctorRating);

    void delete(Long id);

    DoctorRating update(Long id, DoctorRating doctorRating);
}
