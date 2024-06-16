package lk.channelling.services;

import lk.channelling.entity.HospitalFees;

import java.util.List;

public interface HospitalFeesService {

    List<HospitalFees> findAll();

    HospitalFees findById(Long id);

    HospitalFees save(HospitalFees hospitalFees);

    void delete(Long id);

    HospitalFees update(Long id, HospitalFees hospitalFees);
}
