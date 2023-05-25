package studia.inz.inzynierka.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import studia.inz.inzynierka.Security.TokenService;


@RestController
@RequiredArgsConstructor
public class AuthController {


    private final TokenService tokenService;

    @PostMapping("/token")
    public ResponseEntity token(Authentication authentication){
        return ResponseEntity.accepted().body(tokenService.generateToken(authentication));
    }

    @PostMapping("/v1/logout")
    public ResponseEntity logout(Authentication authentication){
        authentication.setAuthenticated(false);
        return ResponseEntity.accepted().body("Logged out");
    }

}
