package lk.channelling.services.impl;

import lk.channelling.entity.DoctorSpeciality;
import lk.channelling.enums.Status;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.DoctorSpecialityRepository;
import lk.channelling.services.DoctorSpecialityService;
import lk.channelling.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class DoctorSpecialityServiceImpl implements DoctorSpecialityService {

    private final DoctorSpecialityRepository doctorSpecialityRepository;

    @Autowired
    public DoctorSpecialityServiceImpl(DoctorSpecialityRepository doctorSpecialityRepository) {
        this.doctorSpecialityRepository = doctorSpecialityRepository;
    }

    @Override
    public List<DoctorSpeciality> findAll() {
        return doctorSpecialityRepository.findAll();
    }

    @Override
    public DoctorSpeciality findById(Long id) {
        Optional<DoctorSpeciality> doctorSpeciality = doctorSpecialityRepository.findById(id);
        if (doctorSpeciality.isEmpty()) {
            throw new RecordNotFoundException("Doctor Speciality not found with id: " + id);
        }
        return doctorSpeciality.get();
    }

    @Override
    public DoctorSpeciality save(DoctorSpeciality doctorSpeciality) {
        LoginAuthenticationHandler.validateUser();

        doctorSpeciality.setCreatedUser(LoginAuthenticationHandler.getUserName());
        doctorSpeciality.setStatus(Status.ACTIVE);
        doctorSpeciality.setCreatedDate(TimeUtil.getCurrentTimeStamp());
        return doctorSpecialityRepository.save(doctorSpeciality);
    }

    @Override
    public void delete(Long id) {
        doctorSpecialityRepository.deleteById(id);
    }

    @Override
    public DoctorSpeciality update(Long id, DoctorSpeciality doctorSpeciality) {
        Optional<DoctorSpeciality> existingDoctorSpeciality = doctorSpecialityRepository.findById(id);
        if (existingDoctorSpeciality.isEmpty()) {
            throw new RecordNotFoundException("Doctor Speciality not found with id: " + id);
        }

        DoctorSpeciality updatedDoctorSpeciality = existingDoctorSpeciality.get();
        updatedDoctorSpeciality.setDoctorId(doctorSpeciality.getDoctorId());
        updatedDoctorSpeciality.setSpecialityId(doctorSpeciality.getSpecialityId());

        return doctorSpecialityRepository.save(updatedDoctorSpeciality);
    }
}
