package lk.channelling.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lk.channelling.model.User;

@Service
public interface UserService {
	Optional<User> findById(Long id) throws Exception;

}
