package ch.ozan.omar.m295.boxingresfoya.service;

import ch.ozan.omar.m295.boxingresfoya.entity.User;
import ch.ozan.omar.m295.boxingresfoya.repository.UserRepository;
import ch.ozan.omar.m295.boxingresfoya.security.Roles;
import ch.ozan.omar.m295.boxingresfoya.storage.EntityNotFoundException;
import jakarta.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder(); // bcrypt encoder initialisieren
    }

    @RolesAllowed(Roles.Read)
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    @RolesAllowed(Roles.Read)
    public User getUser(Long id) {
        logger.info("Fetching user with ID: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User with ID: {} not found", id);
                    return new EntityNotFoundException(id, User.class);
                });
    }

    @RolesAllowed(Roles.Read)
    public User getUserByName(String name) {
        logger.info("Fetching user with name: {}", name);
        return userRepository.findByName(name)
                .orElseThrow(() -> {
                    logger.error("User with name: {} not found", name);
                    return new EntityNotFoundException(name, User.class);
                });
    }

    @RolesAllowed(Roles.Admin)
    public User createUser(User user, String plainPassword) {
        logger.info("Creating user: {}", user);
        String hashedPassword = passwordEncoder.encode(plainPassword); // Passwort mit bcrypt hashen
        user.setPasswordHash(hashedPassword);
        return userRepository.save(user);
    }

    @RolesAllowed(Roles.Read)
    public User updateUser(User user, Long id) {
        logger.info("Updating user with ID: {} with data: {}", id, user);
        return userRepository.findById(id)
                .map(existingUser -> {
                    user.setId(id);
                    if (user.getPasswordHash() != null) {
                        // Passwort nur hashen, wenn es geändert wurde
                        String hashedPassword = passwordEncoder.encode(user.getPasswordHash());
                        user.setPasswordHash(hashedPassword);
                    } else {
                        // Falls kein neues Passwort bereitgestellt wird, das bestehende beibehalten
                        user.setPasswordHash(existingUser.getPasswordHash());
                    }
                    return userRepository.save(user);
                })
                .orElseThrow(() -> {
                    logger.error("User with ID: {} not found", id);
                    return new EntityNotFoundException(id, User.class);
                });
    }

    @RolesAllowed(Roles.Admin)
    public void deleteUser(Long id) {
        logger.info("Deleting user with ID: {}", id);
        if (!userRepository.existsById(id)) {
            logger.error("User with ID: {} not found", id);
            throw new EntityNotFoundException(id, User.class);
        }
        userRepository.deleteById(id);
    }

    public boolean authenticateUser(String name, String plainPassword) {
        User user = getUserByName(name);
        return passwordEncoder.matches(plainPassword, user.getPasswordHash()); // bcrypt verwendet den gespeicherten Hash
    }
}
