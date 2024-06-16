package lk.channelling.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("hospital_id")
    private Long hospitalId;

    @Column(name = "transaction_id", nullable = false)
    @JsonProperty("transaction_id")
    private Long transactionId;

    @Column(name = "effective_from", nullable = false)
    @JsonProperty("effective_from")
    private Timestamp effectiveFrom;

    @Column(name = "effective_to")
    @JsonProperty("effective_to")
    private Timestamp effectiveTo;
}
