package lk.channelling.services.impl;

import lk.channelling.entity.DoctorRating;
import lk.channelling.enums.Status;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.DoctorRatingRepository;
import lk.channelling.services.DoctorRatingService;
import lk.channelling.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class DoctorRatingServiceImpl implements DoctorRatingService {

    private final DoctorRatingRepository doctorRatingRepository;

    @Autowired
    public DoctorRatingServiceImpl(DoctorRatingRepository doctorRatingRepository) {
        this.doctorRatingRepository = doctorRatingRepository;
    }

    @Override
    public List<DoctorRating> findAll() {
        return doctorRatingRepository.findAll();
    }

    @Override
    public DoctorRating findById(Long id) {
        Optional<DoctorRating> doctorRating = doctorRatingRepository.findById(id);
        if (doctorRating.isEmpty()) {
            throw new RecordNotFoundException("Doctor Rating not found with id: " + id);
        }
        return doctorRating.get();
    }

    @Override
    public DoctorRating save(DoctorRating doctorRating) {
        LoginAuthenticationHandler.validateUser();

        doctorRating.setCreatedUser(LoginAuthenticationHandler.getUserName());
        doctorRating.setStatus(Status.ACTIVE);
        doctorRating.setCreatedDate(TimeUtil.getCurrentTimeStamp());
        return doctorRatingRepository.save(doctorRating);
    }

    @Override
    public void delete(Long id) {
        doctorRatingRepository.deleteById(id);
    }

    @Override
    public DoctorRating update(Long id, DoctorRating doctorRating) {
        Optional<DoctorRating> existingDoctorRating = doctorRatingRepository.findById(id);
        if (existingDoctorRating.isEmpty()) {
            throw new RecordNotFoundException("Doctor Rating not found with id: " + id);
        }

        DoctorRating updatedDoctorRating = existingDoctorRating.get();
        updatedDoctorRating.setPatientId(doctorRating.getPatientId());
        updatedDoctorRating.setSessionId(doctorRating.getSessionId());
        updatedDoctorRating.setDoctorId(doctorRating.getDoctorId());
        updatedDoctorRating.setCategoryId(doctorRating.getCategoryId());
        updatedDoctorRating.setRating(doctorRating.getRating());
        updatedDoctorRating.setComment(doctorRating.getComment());
        updatedDoctorRating.setRatingDate(doctorRating.getRatingDate());

        return doctorRatingRepository.save(updatedDoctorRating);
    }
}
