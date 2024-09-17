package andreialionte.realestatebackend.business.concretes;

import andreialionte.realestatebackend.business.abstracts.TokenService;
import andreialionte.realestatebackend.entities.payloads.UserForLoginDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenManager implements TokenService {
    private final String secretKey = "FJKfhjasoifa(#@(90880ăîîîîîîaddadș@!#!@*#!@*#:>./;ț";

    @Override
    public String generateToken(UserForLoginDto auth) {
        long expirationTimeMiliseconds =  6 * 60 * 60 * 1000;

        return Jwts.builder()
                .setSubject(auth.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMiliseconds))
                .signWith(SignatureAlgorithm.HS256, "secretKey")
                .compact();
    }

    @Override
    public Claims validateToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }


}
