package andreialionte.realestatebackend.business.concretes;

import andreialionte.realestatebackend.business.abstracts.BlogService;
import andreialionte.realestatebackend.business.abstracts.StorageService;
import andreialionte.realestatebackend.entities.BlogPost;
import andreialionte.realestatebackend.entities.Image;
import andreialionte.realestatebackend.entities.payloads.BlogPostDto;
import andreialionte.realestatebackend.repository.BlogRepository;
import andreialionte.realestatebackend.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Service
public abstract class BlogManager implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private StorageService storageService;

/*    @Override*/
    @Async
    public CompletableFuture<BlogPost> createBlog(BlogPostDto blogDto, MultipartFile[] files) throws IOException {
        // Create a new BlogPost entity
        var blogPost = new BlogPost();
        blogPost.setTitle(blogDto.getTitle());
        blogPost.setContent(blogDto.getContent());
        blogPost.setCreatedDate(LocalDateTime.now());
        blogPost.setUpdatedDate(LocalDateTime.now());

        // Handle image upload and association with the BlogPost
        Set<Image> images = new HashSet<>();
        for (MultipartFile file : files) {
            String url = storageService.uploadPhoto(file);
            Image image = new Image();
            image.setUrl(url);
            image.setBlogPost(blogPost); // Associate image with the blog post
            images.add(image);
        }

        blogPost.setImages(images);

        // Save the blog post to the repository
        blogRepository.save(blogPost);

        return CompletableFuture.completedFuture(blogPost);
    }


/*    @Override*/
    @Async
    public CompletableFuture<List<BlogPost>> getAllBlogs() {
        return CompletableFuture.completedFuture(blogRepository.findAll());
    }

/*    @Override*/
    @Async
    public CompletableFuture<Optional<BlogPost>> getBlogById(Long id) {
        return CompletableFuture.completedFuture(blogRepository.findById(id));
    }

/*    @Override*/
    @Async
    public CompletableFuture<BlogPost> updateBlog(Long id, BlogPostDto blogDto, MultipartFile[] files) throws Exception {
        BlogPost existingBlogPost = blogRepository.findById(id)
                .orElseThrow(() -> new Exception("Blog post not found"));

        existingBlogPost.setTitle(blogDto.getTitle());
        existingBlogPost.setContent(blogDto.getContent());
        existingBlogPost.setUpdatedDate(LocalDateTime.now());

        // Delete old images
        imageRepository.deleteAll(existingBlogPost.getImages());

        // Handle new file uploads
        Set<Image> images = new HashSet<>();
        for (MultipartFile file : files) {
            String url = storageService.uploadPhoto(file);
            Image image = new Image();
            image.setUrl(url);
            image.setBlogPost(existingBlogPost); // Associate the image with the blog post
            images.add(image);
        }

        existingBlogPost.setImages(images);
        blogRepository.save(existingBlogPost);

        return CompletableFuture.completedFuture(existingBlogPost);
    }

/*    @Override*/
    @Async
    public CompletableFuture<Boolean> deleteBlog(Long id) throws Exception {
        if (!blogRepository.existsById(id)) {
            throw new Exception("The property does not exist");
        }

       var blog = blogRepository.findById(id).orElseThrow(() -> new Exception("The property does not exist"));
        imageRepository.deleteAll(blog.getImages());

        blogRepository.deleteById(id);

        return CompletableFuture.completedFuture(true);
    }

    private Image mapImageDtoToImage(Image imageDto, BlogPost blogPost) {
        Image image = new Image();
        image.setUrl(imageDto.getUrl());
        image.setBlogPost(blogPost);
        return image;
    }
}