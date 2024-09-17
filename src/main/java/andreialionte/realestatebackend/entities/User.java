package andreialionte.realestatebackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "customer")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(length = 150)
    @Email
    private String email;

    @OneToOne
    @JoinColumn(name = "auth_id") // Ensure this is the correct FK column if Auth is a separate entity
    private Auth auth;

    @OneToMany(mappedBy = "author") // Assuming the field in BlogPost is named "author"
    private Set<BlogPost> blogPosts; // Change to plural "blogPosts" to represent a collection
}
