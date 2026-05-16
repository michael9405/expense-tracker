package at.graf.michael.expensetracker.services;

import at.graf.michael.expensetracker.entities.User;
import at.graf.michael.expensetracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserService {
    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserById(int userId) {
        logger.info("Getting the user by id: " + userId);
        return userRepository.findById(userId);
    }

    public Optional<User> getUserByEmail(String email) {
        logger.info("Getting the user by email: " + email);
        return userRepository.findByEmail(email);
    }
}
