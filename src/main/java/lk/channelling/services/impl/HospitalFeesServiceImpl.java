package lk.channelling.services.impl;

import lk.channelling.entity.HospitalFees;
import lk.channelling.enums.Status;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.handlers.LoginAuthenticationHandler;
import lk.channelling.repository.HospitalFeesRepository;
import lk.channelling.services.HospitalFeesService;
import lk.channelling.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class HospitalFeesServiceImpl implements HospitalFeesService {

    private final HospitalFeesRepository hospitalFeesRepository;

    @Autowired
    public HospitalFeesServiceImpl(HospitalFeesRepository hospitalFeesRepository) {
        this.hospitalFeesRepository = hospitalFeesRepository;
    }

    @Override
    public List<HospitalFees> findAll() {
        return hospitalFeesRepository.findAll();
    }

    @Override
    public HospitalFees findById(Long id) {
        Optional<HospitalFees> hospitalFees = hospitalFeesRepository.findById(id);
        if (hospitalFees.isEmpty()) {
            throw new RecordNotFoundException("Hospital fees not found with id: " + id);
        }
        return hospitalFees.get();
    }

    @Override
    public HospitalFees save(HospitalFees hospitalFees) {
        LoginAuthenticationHandler.validateUser();

        hospitalFees.setCreatedUser(LoginAuthenticationHandler.getUserName());
        hospitalFees.setStatus(Status.ACTIVE);
        hospitalFees.setCreatedDate(TimeUtil.getCurrentTimeStamp());
        return hospitalFeesRepository.save(hospitalFees);
    }

    @Override
    public void delete(Long id) {
        hospitalFeesRepository.deleteById(id);
    }

    @Override
    public HospitalFees update(Long id, HospitalFees hospitalFees) {
        Optional<HospitalFees> existingHospitalFees = hospitalFeesRepository.findById(id);
        if (existingHospitalFees.isEmpty()) {
            throw new RecordNotFoundException("Hospital fees not found with id: " + id);
        }

        HospitalFees updatedHospitalFees = existingHospitalFees.get();
        updatedHospitalFees.setAmount(hospitalFees.getAmount());
        updatedHospitalFees.setHospitalId(hospitalFees.getHospitalId());
        updatedHospitalFees.setTransactionId(hospitalFees.getTransactionId());
        updatedHospitalFees.setEffectiveFrom(hospitalFees.getEffectiveFrom());
        updatedHospitalFees.setEffectiveTo(hospitalFees.getEffectiveTo());

        return hospitalFeesRepository.save(updatedHospitalFees);
    }
}
