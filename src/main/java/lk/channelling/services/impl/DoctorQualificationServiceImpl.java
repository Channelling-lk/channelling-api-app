package lk.channelling.services.impl;

import lk.channelling.entity.DoctorQualification;
import lk.channelling.enums.Status;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.DoctorQualificationRepository;
import lk.channelling.services.DoctorQualificationService;
import lk.channelling.util.TimeUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@Log4j2
public class DoctorQualificationServiceImpl implements DoctorQualificationService {

    private final DoctorQualificationRepository doctorQualificationRepository;

    @Autowired
    public DoctorQualificationServiceImpl(DoctorQualificationRepository doctorQualificationRepository) {
        this.doctorQualificationRepository = doctorQualificationRepository;
    }

    @Override
    public List<DoctorQualification> findAll() {
        return doctorQualificationRepository.findAll();
    }

    @Override
    public DoctorQualification findById(Long id) {
        Optional<DoctorQualification> doctorQualification = doctorQualificationRepository.findById(id);
        if (doctorQualification.isEmpty())
            throw new RecordNotFoundException("No doctor qualification record found for the id: " + id);
        return doctorQualification.get();
    }

    @Override
    public DoctorQualification save(DoctorQualification doctorQualification) {
        LoginAuthenticationHandler.validateUser();

        doctorQualification.setCreatedUser(LoginAuthenticationHandler.getUserName());
        doctorQualification.setStatus(Status.ACTIVE);
        doctorQualification.setCreatedDate(TimeUtil.getCurrentTimeStamp());
        return doctorQualificationRepository.save(doctorQualification);
    }

    @Override
    public void delete(Long id) {
        DoctorQualification doctorQualification = findById(id);
        doctorQualificationRepository.delete(doctorQualification);
    }

    @Override
    public DoctorQualification update(Long id, DoctorQualification newDoctorQualification) {
        Optional<DoctorQualification> updatedDoctorQualification = doctorQualificationRepository.findById(id).map(doctorQualification -> {
            doctorQualification.setStartDate(newDoctorQualification.getStartDate());
            doctorQualification.setEndDate(newDoctorQualification.getEndDate());
            doctorQualification.setRemarks(newDoctorQualification.getRemarks());
            doctorQualification.setGrade(newDoctorQualification.getGrade());
            doctorQualification.setDoctorId(newDoctorQualification.getDoctorId());
            doctorQualification.setInstitutionId(newDoctorQualification.getInstitutionId());
            doctorQualification.setQualificationLevelId(newDoctorQualification.getQualificationLevelId());
            doctorQualification.setStatus(newDoctorQualification.getStatus());
            doctorQualification.setModifiedUser(LoginAuthenticationHandler.getUserName());
            doctorQualification.setModifiedDate(TimeUtil.getCurrentTimeStamp());

            return doctorQualificationRepository.save(doctorQualification);
        });
        if (updatedDoctorQualification.isPresent()) return updatedDoctorQualification.get();
        throw new RecordNotFoundException("No doctor qualification record found for the id: " + id);
    }
}
