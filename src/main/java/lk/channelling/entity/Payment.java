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
@Table(name = "payment")
@Data
public class Payment extends BaseEntity implements Serializable {

    @Column(name = "appointment_id", nullable = false)
    @JsonProperty("appointment_id")
    private Long appointmentId;

    @Column(name = "payment_date", nullable = false)
    @JsonProperty("payment_date")
    private Timestamp paymentDate;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "payment_method")
    @JsonProperty("payment_method")
    private String paymentMethod;
}
