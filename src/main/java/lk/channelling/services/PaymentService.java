package lk.channelling.services;

import lk.channelling.entity.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> findAll();

    Payment findById(Long id);

    Payment save(Payment payment);

    void delete(Long id);

    Payment update(Long id, Payment payment);
}
