package andreialionte.realestatebackend.repository;

import andreialionte.realestatebackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
