package lk.channelling.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lk.channelling.model.generic.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user")
@NoArgsConstructor
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column
	private String name;

	public User(Long id, String name) {
		this.id = id;
		this.name = name;
	}

}
