package andreialionte.realestatebackend.business.abstracts;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    public String uploadPhoto(MultipartFile file) throws IOException;
}
