package studia.inz.inzynierka.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.Models.Entites.ClientEntity;
import studia.inz.inzynierka.Repos.ClientRepository;

import java.util.Collections;
import java.util.Set;

@Service
public class SecurityUserDetailService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    public SecurityUserDetailService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientEntity client = clientRepository.findByLogin(username);

        Set<GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("USER"));
        return new User(
                client.getLogin(),
                client.getPassword(),
                authorities);
    }

    public void createUser(UserDetails user) {
        clientRepository.save((ClientEntity) user);
    }
}
