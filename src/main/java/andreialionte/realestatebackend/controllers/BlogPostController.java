package andreialionte.realestatebackend.controllers;

import andreialionte.realestatebackend.business.concretes.BlogManager;
import andreialionte.realestatebackend.entities.BlogPost;
import andreialionte.realestatebackend.entities.payloads.BlogPostDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("api/blogposts")
public class BlogPostController {

    private final BlogManager blogManager;

    public BlogPostController(BlogManager blogManager) {
        this.blogManager = blogManager;
    }

    @PostMapping("createBlogPost")
    public CompletableFuture<ResponseEntity<BlogPost>> createBlogPost(
            BlogPostDto blogPostDto,
            @RequestParam(value = "files", required = false) MultipartFile[] files) throws IOException {
        return blogManager.createBlog(blogPostDto, files)
                .thenApply(createdBlogPost -> ResponseEntity.status(HttpStatus.CREATED).body(createdBlogPost));
    }

    @GetMapping("getAllBlogPosts")
    public CompletableFuture<ResponseEntity<List<BlogPost>>> getAllBlogPosts() {
        return blogManager.getAllBlogs()
                .thenApply(blogPosts -> ResponseEntity.ok(blogPosts));
    }

    @GetMapping("getBlogPostById/{id}")
    public CompletableFuture<ResponseEntity<Optional<BlogPost>>> getBlogPostById(@PathVariable Long id) {
        return blogManager.getBlogById(id)
                .thenApply(blogPost -> ResponseEntity.ok(blogPost));
    }

    @PutMapping("updateBlogPost/{id}")
    public CompletableFuture<ResponseEntity<BlogPost>> updateBlogPost(
            @PathVariable Long id,
            BlogPostDto blogPostDto,
            @RequestParam(value = "files", required = false) MultipartFile[] files) throws Exception {
        return blogManager.updateBlog(id, blogPostDto, files)
                .thenApply(blogPost -> ResponseEntity.ok(blogPost));
    }

    @DeleteMapping("deleteBlogPost/{id}")
    public CompletableFuture<ResponseEntity<Void>> deleteBlogPost(@PathVariable Long id) throws Exception {
        return blogManager.deleteBlog(id)
                .thenApply(aBoolean -> ResponseEntity.noContent().build());
    }
}
