package lk.channelling.services.impl;

import lk.channelling.entity.Payment;
import lk.channelling.exception.RecordNotFoundException;
import lk.channelling.repository.PaymentRepository;
import lk.channelling.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment findById(Long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isEmpty()) {
            throw new RecordNotFoundException("Payment not found with id: " + id);
        }
        return payment.get();
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public Payment update(Long id, Payment payment) {
        Optional<Payment> existingPayment = paymentRepository.findById(id);
        if (existingPayment.isEmpty()) {
            throw new RecordNotFoundException("Payment not found with id: " + id);
        }

        Payment updatedPayment = existingPayment.get();
        updatedPayment.setAppointmentId(payment.getAppointmentId());
        updatedPayment.setPaymentDate(payment.getPaymentDate());
        updatedPayment.setAmount(payment.getAmount());
        updatedPayment.setPaymentMethod(payment.getPaymentMethod());

        return paymentRepository.save(updatedPayment);
    }
}
