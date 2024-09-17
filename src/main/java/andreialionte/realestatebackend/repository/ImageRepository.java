package andreialionte.realestatebackend.repository;

import andreialionte.realestatebackend.entities.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
