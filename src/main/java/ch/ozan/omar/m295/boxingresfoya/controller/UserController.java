package ch.ozan.omar.m295.boxingresfoya.controller;

import ch.ozan.omar.m295.boxingresfoya.entity.User;
import ch.ozan.omar.m295.boxingresfoya.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for managing User resources.
 */
@RestController
@SecurityRequirement(name = "bearerAuth")
@Validated
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/user")
    public ResponseEntity<List<User>> all() {
        logger.info("Fetching all users");
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/api/user/{id}")
    public ResponseEntity<User> one(@PathVariable Long id) {
        logger.info("Fetching user with ID: {}", id);
        User user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/api/user/byname/{name}")
    public ResponseEntity<User> oneByName(@PathVariable String name) {
        logger.info("Fetching user with name: {}", name);
        User user = userService.getUserByName(name);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/api/user")
    public ResponseEntity<User> newUser(@RequestBody User user, @RequestParam String password) {
        logger.info("Creating a new user: {}", user);
        User savedUser = userService.createUser(user, password);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/api/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        logger.info("Updating user with ID: {} with data: {}", id, user);
        User updatedUser = userService.updateUser(user, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        logger.info("Deleting user with ID: {}", id);
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/api/user/authenticate")
    public ResponseEntity<?> authenticateUser(@RequestParam String name, @RequestParam String password) {
        boolean authenticated = userService.authenticateUser(name, password);
        return authenticated ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
