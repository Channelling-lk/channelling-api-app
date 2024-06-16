package lk.channelling.services;

import lk.channelling.entity.HospitalRatings;

import java.util.List;

public interface HospitalRatingsService {

    List<HospitalRatings> findAll();

    HospitalRatings findById(Long id);

    HospitalRatings save(HospitalRatings hospitalRatings);

    void delete(Long id);

    HospitalRatings update(Long id, HospitalRatings hospitalRatings);
}
