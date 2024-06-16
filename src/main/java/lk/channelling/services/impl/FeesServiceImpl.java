package lk.channelling.services.impl;

import lk.channelling.entity.Fees;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.repository.FeesRepository;
import lk.channelling.services.FeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class FeesServiceImpl implements FeesService {

    private final FeesRepository feesRepository;

    @Autowired
    public FeesServiceImpl(FeesRepository feesRepository) {
        this.feesRepository = feesRepository;
    }

    @Override
    public List<Fees> findAll() {
        return feesRepository.findAll();
    }

    @Override
    public Fees findById(Long id) {
        Optional<Fees> fees = feesRepository.findById(id);
        if (fees.isEmpty()) {
            throw new RecordNotFoundException("Fees not found with id: " + id);
        }
        return fees.get();
    }

    @Override
    public Fees save(Fees fees) {
        return feesRepository.save(fees);
    }

    @Override
    public void delete(Long id) {
        feesRepository.deleteById(id);
    }

    @Override
    public Fees update(Long id, Fees fees) {
        Optional<Fees> existingFees = feesRepository.findById(id);
        if (existingFees.isEmpty()) {
            throw new RecordNotFoundException("Fees not found with id: " + id);
        }

        Fees updatedFees = existingFees.get();
        updatedFees.setSessionId(fees.getSessionId());
        updatedFees.setTransactionTypeId(fees.getTransactionTypeId());
        updatedFees.setAmount(fees.getAmount());

        return feesRepository.save(updatedFees);
    }
}
