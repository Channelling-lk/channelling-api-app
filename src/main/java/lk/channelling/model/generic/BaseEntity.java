package lk.channelling.model.generic;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	protected Long id;

//	@Version
//	private Long version;
//
//	@Column(name = "created_user")
//	protected String createdUser;
//
//	@Column(name = "created_date")
//	protected Timestamp createdDate;
//
//	@Column(name = "modified_user")
//	protected String modifiedUser;
//
//	@Column(name = "modified_date")
//	protected Timestamp modifiedDate;
//
//	@Column(name = "status")
//	protected String status;

}
