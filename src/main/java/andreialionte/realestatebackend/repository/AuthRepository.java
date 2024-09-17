package andreialionte.realestatebackend.repository;

import andreialionte.realestatebackend.entities.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<Auth, Long> {
//    ResponseEntity<?> Register();
//    ResponseEntity<?> Login();
    Auth findByEmail(String email);
}
