package andreialionte.realestatebackend.repository;

import andreialionte.realestatebackend.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<BlogPost, Long> {
}
