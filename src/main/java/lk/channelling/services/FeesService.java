package lk.channelling.services;

import lk.channelling.entity.Fees;

import java.util.List;

public interface FeesService {

    List<Fees> findAll();

    Fees findById(Long id);

    Fees save(Fees fees);

    void delete(Long id);

    Fees update(Long id, Fees fees);
}
