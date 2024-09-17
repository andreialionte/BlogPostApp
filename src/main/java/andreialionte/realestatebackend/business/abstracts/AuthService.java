package andreialionte.realestatebackend.business.abstracts;

import andreialionte.realestatebackend.entities.payloads.UserForLoginDto;
import andreialionte.realestatebackend.entities.payloads.UserForRegistrationDto;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public interface AuthService {
    public CompletableFuture<ResponseEntity<String>> Register(UserForRegistrationDto userReg);

    public CompletableFuture<ResponseEntity<?>> Login(UserForLoginDto userLogin);
}
