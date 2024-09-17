package andreialionte.realestatebackend.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "blog_post")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(length = 2000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User author;

    @OneToMany(mappedBy = "blogPost", cascade = CascadeType.ALL) // Ensure "blogPost" matches the Image entity field
    private Set<Image> images;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;
}
