package lk.channelling.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "payment")
@Data
public class Payment extends BaseEntity implements Serializable {

    @Column(name = "appointment_id", nullable = false)
    private Long appointmentId;

    @Column(name = "payment_date", nullable = false)
    private Timestamp paymentDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "payment_method")
    private String paymentMethod;
}
