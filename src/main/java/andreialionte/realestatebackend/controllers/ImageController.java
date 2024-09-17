package andreialionte.realestatebackend.controllers;

import andreialionte.realestatebackend.business.abstracts.StorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/storage")
public class ImageController {

    private final StorageService storageService;

    public ImageController(StorageService storageService) {
        this.storageService = storageService;
    }

//    curl -X POST "http://localhost:5050/api/storage/upload" -F "file=@C:/Users/User/Downloads/download.jpeg"
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            String result = storageService.uploadPhoto(file);
            return ResponseEntity.ok(result);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        }
    }
}
