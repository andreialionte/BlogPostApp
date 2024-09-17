package andreialionte.realestatebackend.controllers;

import andreialionte.realestatebackend.business.abstracts.AuthService;
import andreialionte.realestatebackend.entities.payloads.UserForLoginDto;
import andreialionte.realestatebackend.entities.payloads.UserForRegistrationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController()
@RequestMapping("api/auths")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("register")

    public CompletableFuture<ResponseEntity<?>> Register(@RequestBody UserForRegistrationDto userReg) {
        return authService.Register(userReg)
                .thenApplyAsync(ResponseEntity::ok);
    }

    @PostMapping("login")
    public CompletableFuture<ResponseEntity<?>> Login(@RequestBody UserForLoginDto userLog) {
        return authService.Login(userLog)
                .thenApplyAsync(ResponseEntity::ok);
    }

}
