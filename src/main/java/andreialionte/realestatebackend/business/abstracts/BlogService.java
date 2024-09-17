package andreialionte.realestatebackend.business.abstracts;

import andreialionte.realestatebackend.entities.BlogPost;
import andreialionte.realestatebackend.entities.payloads.BlogPostDto;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface BlogService {
/*    CompletableFuture<Property> createBlogPost(BlogPost blogPost, MultipartFile[] files) throws IOException;*/

    @Async
    CompletableFuture<BlogPost> createBlog(BlogPostDto blogDto, MultipartFile[] files) throws IOException;

    @Async
    CompletableFuture<List<BlogPost>> getAllBlogs();

    /*CompletableFuture<List<Property>> getAllBlogPosts();
    CompletableFuture<Optional<Property>> getBlogPostById(Long id);
    CompletableFuture<Property> updateBlogPost(Long id, BlogPost updatedBlogPost, MultipartFile[] files) throws Exception;
*/
    @Async
    CompletableFuture<BlogPost> updateBlog(Long id, BlogPostDto blogDto, MultipartFile[] files) throws Exception;

    @Async
    CompletableFuture<Optional<BlogPost>> getBlogById(Long id);

/*    @Async
    CompletableFuture<Property> deleteBlogPost(Long id) throws Exception;*/

    @Async
    CompletableFuture<Boolean> deleteBlog(Long id) throws Exception;
}
