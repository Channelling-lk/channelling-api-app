package lk.channelling.model;

import jakarta.persistence.*;
import lk.channelling.enums.TokenType;
import lk.channelling.model.generic.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;


}
