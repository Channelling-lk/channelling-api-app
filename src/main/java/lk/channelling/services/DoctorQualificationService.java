package lk.channelling.services;

import lk.channelling.entity.DoctorQualification;

import java.util.List;

public interface DoctorQualificationService {

    List<DoctorQualification> findAll();

    DoctorQualification findById(Long id);

    DoctorQualification save(DoctorQualification doctorQualification);

    void delete(Long id);

    DoctorQualification update(Long id, DoctorQualification doctorQualification);
}
