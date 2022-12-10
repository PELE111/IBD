package studia.inz.inzynierka.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import studia.inz.inzynierka.Entites.ClientEntity;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Integer>, JpaSpecificationExecutor {
    ClientEntity findByLogin(String login);
}
