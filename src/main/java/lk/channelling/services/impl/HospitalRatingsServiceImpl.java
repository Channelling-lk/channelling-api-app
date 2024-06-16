package lk.channelling.services.impl;

import lk.channelling.entity.HospitalRatings;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.repository.HospitalRatingsRepository;
import lk.channelling.services.HospitalRatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class HospitalRatingsServiceImpl implements HospitalRatingsService {

    private final HospitalRatingsRepository hospitalRatingsRepository;

    @Autowired
    public HospitalRatingsServiceImpl(HospitalRatingsRepository hospitalRatingsRepository) {
        this.hospitalRatingsRepository = hospitalRatingsRepository;
    }

    @Override
    public List<HospitalRatings> findAll() {
        return hospitalRatingsRepository.findAll();
    }

    @Override
    public HospitalRatings findById(Long id) {
        Optional<HospitalRatings> hospitalRatings = hospitalRatingsRepository.findById(id);
        if (hospitalRatings.isEmpty()) {
            throw new RecordNotFoundException("Hospital ratings not found with id: " + id);
        }
        return hospitalRatings.get();
    }

    @Override
    public HospitalRatings save(HospitalRatings hospitalRatings) {
        return hospitalRatingsRepository.save(hospitalRatings);
    }

    @Override
    public void delete(Long id) {
        hospitalRatingsRepository.deleteById(id);
    }

    @Override
    public HospitalRatings update(Long id, HospitalRatings hospitalRatings) {
        Optional<HospitalRatings> existingHospitalRatings = hospitalRatingsRepository.findById(id);
        if (existingHospitalRatings.isEmpty()) {
            throw new RecordNotFoundException("Hospital ratings not found with id: " + id);
        }

        HospitalRatings updatedHospitalRatings = existingHospitalRatings.get();
        updatedHospitalRatings.setPatientId(hospitalRatings.getPatientId());
        updatedHospitalRatings.setSessionId(hospitalRatings.getSessionId());
        updatedHospitalRatings.setHospitalId(hospitalRatings.getHospitalId());
        updatedHospitalRatings.setCategoryId(hospitalRatings.getCategoryId());
        updatedHospitalRatings.setRating(hospitalRatings.getRating());
        updatedHospitalRatings.setComment(hospitalRatings.getComment());
        updatedHospitalRatings.setRatingDate(hospitalRatings.getRatingDate());

        return hospitalRatingsRepository.save(updatedHospitalRatings);
    }
}
