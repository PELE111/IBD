package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.DTO.ClientDTO;
import studia.inz.inzynierka.Entites.ClientEntity;
import studia.inz.inzynierka.Entites.ClientEntity_;
import studia.inz.inzynierka.Repos.ClientRepository;
import studia.inz.inzynierka.Repos.RoleRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    final private ClientRepository clientRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    final private RoleRepository roleRepository;

    public ClientEntity getClientByUsername(String username){
        if(username.isEmpty()) return null;
        return clientRepository.findByLogin(username);
    }

    public void saveClient(ClientDTO clientDTO){
        ClientEntity client = new ClientEntity();
        client.setLogin(clientDTO.getLogin());
        client.setPassword(passwordEncoder.encode(clientDTO.getPassword()));
        client.setRole(roleRepository.findByName("USER"));
        clientRepository.save(client);
    }

    public boolean checkUsernameExists(String username){
        return clientRepository.exists(Specification.
                where(
                        ((root, query, criteriaBuilder) -> {
                            return criteriaBuilder.equal(criteriaBuilder.lower(root.get(ClientEntity_.LOGIN)), username.toLowerCase());
                        })
                ));
    }
}
