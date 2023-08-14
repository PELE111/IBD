package studia.inz.inzynierka.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import studia.inz.inzynierka.Models.Entites.DietEntity;

@Repository
public interface DietRepository extends JpaRepository<DietEntity, Integer>, JpaSpecificationExecutor {
    DietEntity findByClientID_Login(String login);

    boolean existsByClientID_Login(String login);
}
