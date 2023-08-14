package studia.inz.inzynierka.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import studia.inz.inzynierka.Models.DTO.ClientDTO;
import studia.inz.inzynierka.Service.ClientService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/v1/client")
public class ClientController {

    final private ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity registration(@RequestBody ClientDTO clientDto) {
        return clientService.saveClient(clientDto);
    }

    @GetMapping("/info")
    public ResponseEntity getUserInfo(Authentication authentication) {
        String login = authentication.getName();
        if (login.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(login);
    }


}
