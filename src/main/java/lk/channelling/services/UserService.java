package lk.channelling.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	Optional<User> findById(Long id) throws Exception;

}
