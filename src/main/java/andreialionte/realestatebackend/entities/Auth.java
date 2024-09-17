package andreialionte.realestatebackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(length = 150)
    private String email;
//    private String passwordsalt;
    private String passwordhash;

}
