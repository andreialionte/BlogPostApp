package andreialionte.realestatebackend.entities.payloads;

import andreialionte.realestatebackend.entities.Photo;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BlogPostDto {
    private String title;
    private String content;
    private Long authorId;
    private Set<Photo> images;
}
