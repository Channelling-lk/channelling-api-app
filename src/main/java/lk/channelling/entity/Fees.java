package lk.channelling.entity;

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
    private Long sessionId;

    @Column(name = "transaction_type_id", nullable = false)
    private Long transactionTypeId;

    @Column(name = "amount")
    private BigDecimal amount;
}
