package andreialionte.realestatebackend.controllers;

import andreialionte.realestatebackend.business.concretes.UserManager;
import andreialionte.realestatebackend.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserManager userManager;

    public UserController(UserManager userManager) {
        this.userManager = userManager;
    }

    @GetMapping("/getUsers")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userManager.GetUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getUser/{id}")
    public ResponseEntity<Optional<User>> getUser(@PathVariable Long id) {
        Optional<User> user = userManager.GetUser(id);
        return ResponseEntity.ok(user);
    }
}
