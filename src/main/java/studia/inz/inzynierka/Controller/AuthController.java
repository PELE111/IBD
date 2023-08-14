package studia.inz.inzynierka.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import studia.inz.inzynierka.Models.ApiRequest.TokenRes;
import studia.inz.inzynierka.Repos.ClientRepository;
import studia.inz.inzynierka.Security.TokenService;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final TokenService tokenService;
    private final ClientRepository clientRepository;

    @PostMapping("/token")
    public ResponseEntity<TokenRes> token(Authentication authentication) {
        if (authentication == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        TokenRes tokenRes = new TokenRes(
                authentication.getName(),
                tokenService.generateToken(authentication),
                clientRepository.findByLogin(authentication.getName()).getClientId());
        return ResponseEntity.accepted().body(tokenRes);
    }
}
