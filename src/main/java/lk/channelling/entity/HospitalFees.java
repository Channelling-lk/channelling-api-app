package lk.channelling.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "hospital_fees")
@Data
public class HospitalFees extends BaseEntity implements Serializable {

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "hospital_id", nullable = false)
    private Long hospitalId;

    @Column(name = "transaction_id", nullable = false)
    private Long transactionId;

    @Column(name = "effective_from", nullable = false)
    private Timestamp effectiveFrom;

    @Column(name = "effective_to")
    private Timestamp effectiveTo;
}
