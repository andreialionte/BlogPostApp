package andreialionte.realestatebackend.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String Url;
    @ManyToOne
    @JoinColumn(name = "blog_post_id", nullable = false) // Foreign key to BlogPost
    private BlogPost blogPost; // This field must match the 'mappedBy' value in BlogPost

}
