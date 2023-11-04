package lk.channelling.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import lk.channelling.model.User;
import lk.channelling.repository.UserRepository;

@Component
@Transactional(rollbackOn = Exception.class)

public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
		
		

	}

}