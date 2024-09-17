package andreialionte.realestatebackend.business.concretes;

import andreialionte.realestatebackend.business.abstracts.AuthService;
import andreialionte.realestatebackend.business.abstracts.TokenService;
import andreialionte.realestatebackend.entities.Auth;
import andreialionte.realestatebackend.entities.User;
import andreialionte.realestatebackend.entities.payloads.UserForLoginDto;
import andreialionte.realestatebackend.entities.payloads.UserForRegistrationDto;
import andreialionte.realestatebackend.repository.AuthRepository;
import andreialionte.realestatebackend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class AuthManager implements AuthService {

    private final AuthRepository authRepository;
    private final UserRepository userRepository;
    private final Argon2PasswordEncoder argon2PasswordEncoder;
    private final TokenService tokenService;

    public AuthManager(AuthRepository authRepository, UserRepository userRepository, TokenService tokenService) {
        this.authRepository = authRepository;
        this.userRepository = userRepository;
        this.argon2PasswordEncoder = new Argon2PasswordEncoder(16, 32, 1, 65536, 4); // Parameters for Argon2
        this.tokenService = tokenService;
    }

    @Async
    @Override
    public CompletableFuture<ResponseEntity<String>> Register(UserForRegistrationDto userReg) {
        return CompletableFuture.supplyAsync(() -> {
            if (!Objects.equals(userReg.getPassword(), userReg.getConfirmPassword())) {
                return ResponseEntity.status(401).body("The passwords must match!");
            }

            var searchForUser = authRepository.findByEmail(userReg.getEmail());
            if (searchForUser != null) {
                return ResponseEntity.status(400).body("User already exists!");
            }

            String password = userReg.getPassword();
            String hashedPassword = argon2PasswordEncoder.encode(password);

            Auth auth = new Auth();
            auth.setEmail(userReg.getEmail());
            auth.setPasswordhash(hashedPassword);
            authRepository.save(auth);

            User user = new User();
            user.setEmail(userReg.getEmail());
            user.setName(userReg.getName());
            user.setAuth(auth);
            userRepository.save(user);

            return ResponseEntity.status(200).body("User registered successfully");
        }).exceptionally(ex -> {
            return ResponseEntity.status(500).body("Registration failed: " + ex.getMessage());
        });
    }

    @Async
    @Override
    public CompletableFuture<ResponseEntity<?>> Login(UserForLoginDto userLogin) {
        return CompletableFuture.supplyAsync(() -> {
            var auth = authRepository.findByEmail(userLogin.getEmail());
            if (auth == null) {
                return ResponseEntity.status(401).body("User does not exist");
            }

            String storedHash = auth.getPasswordhash();
            boolean passwordMatches = argon2PasswordEncoder.matches(userLogin.getPassword(), storedHash);

            if (passwordMatches) {
                String token = tokenService.generateToken(userLogin);
                return ResponseEntity.ok(Map.of("token", token));
            } else {
                return ResponseEntity.status(401).body("Invalid email or password");
            }
        }).exceptionally(ex -> {
            return ResponseEntity.status(500).body("Login failed: " + ex.getMessage());
        });
    }

    private byte[] generateSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return salt;
    }
}

