package andreialionte.realestatebackend.business.abstracts;

import andreialionte.realestatebackend.entities.payloads.AuthDto;
import andreialionte.realestatebackend.entities.payloads.UserForLoginDto;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

public interface TokenService {
    public Claims validateToken(String token);
    public String generateToken(UserForLoginDto auth);
}
