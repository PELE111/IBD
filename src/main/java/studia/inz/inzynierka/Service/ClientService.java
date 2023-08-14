package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.Models.DTO.ClientDTO;
import studia.inz.inzynierka.Models.Entites.ClientEntity;
import studia.inz.inzynierka.Repos.ClientRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    final private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public ClientEntity getClientByUsername(String username) {
        if (username.isEmpty()) return null;
        return clientRepository.findByLogin(username);
    }

    public ResponseEntity saveClient(ClientDTO clientDTO) {
        if (clientRepository.existsByLogin(clientDTO.getLogin()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        ClientEntity client = new ClientEntity();
        client.setLogin(clientDTO.getLogin());
        client.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        clientRepository.save(client);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }

    public boolean checkUsernameExists(String login) {
        return clientRepository.existsByLogin(login);
    }
}
