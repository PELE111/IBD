package studia.inz.inzynierka.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import studia.inz.inzynierka.DTO.ClientDTO;
import studia.inz.inzynierka.Service.ClientService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/v1/client")
public class ClientController {

    final private ClientService clientService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity registration(@RequestBody ClientDTO clientDto){
        boolean exists = clientService.checkUsernameExists(clientDto.getLogin());

        if(exists){
           return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already exists");
        }

        clientService.saveClient(clientDto);
        return ResponseEntity.accepted().body("User created");
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody ClientDTO clientDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                clientDTO.getLogin(), clientDTO.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return  ResponseEntity.ok("Logged in");
    }


    @GetMapping("/info")
    public ResponseEntity getUserInfo(Authentication authentication){
        String login = authentication.getName();
        if (login.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(login);
    }




}
