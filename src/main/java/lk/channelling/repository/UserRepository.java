package lk.channelling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.channelling.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	

}
