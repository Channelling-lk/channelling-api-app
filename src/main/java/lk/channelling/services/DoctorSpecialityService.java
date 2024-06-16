package lk.channelling.services;

import lk.channelling.entity.DoctorSpeciality;

import java.util.List;

public interface DoctorSpecialityService {

    List<DoctorSpeciality> findAll();

    DoctorSpeciality findById(Long id);

    DoctorSpeciality save(DoctorSpeciality doctorSpeciality);

    void delete(Long id);

    DoctorSpeciality update(Long id, DoctorSpeciality doctorSpeciality);
}
