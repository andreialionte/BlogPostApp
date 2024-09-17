package andreialionte.realestatebackend.business.abstracts;

import andreialionte.realestatebackend.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> GetUsers();
    Optional<User> GetUser(Long id);
}
