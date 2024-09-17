package andreialionte.realestatebackend.business.concretes;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class StorageManager implements andreialionte.realestatebackend.business.abstracts.StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    @Override
    public String uploadPhoto(MultipartFile file) throws IOException {
        String fileName = System.currentTimeMillis() + "_" + UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        try (InputStream inputStream = file.getInputStream()) {
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, null)
                    .withCannedAcl(CannedAccessControlList.PublicRead)); // we make the file publicly accessible to see the photo
        }

        // return the public URL of the uploaded file
        return s3Client.getUrl(bucketName, fileName).toString();
    }
}
