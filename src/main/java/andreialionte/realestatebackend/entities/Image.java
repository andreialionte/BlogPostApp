package andreialionte.realestatebackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String url; //handle with aws S3
    @ManyToOne
    @JoinColumn(name = "BlogPost_id", nullable = false) //this FK should match the column in the database
    private BlogPost blogPost;

}
