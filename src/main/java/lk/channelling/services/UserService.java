package lk.channelling.services;

import lk.channelling.entity.User;
import lk.channelling.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findById(Long id) throws Exception {
        return userRepository.findById(id);
    }

}
