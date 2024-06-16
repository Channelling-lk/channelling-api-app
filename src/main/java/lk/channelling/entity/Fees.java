package lk.channelling.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "fees")
@Data
public class Fees extends BaseEntity implements Serializable {

    @Column(name = "session_id", nullable = false)
    @JsonProperty("session_id")
    private Long sessionId;

    @Column(name = "transaction_type_id", nullable = false)
    @JsonProperty("transaction_type_id")
    private Long transactionTypeId;

    @Column(name = "amount")
    private BigDecimal amount;
}
