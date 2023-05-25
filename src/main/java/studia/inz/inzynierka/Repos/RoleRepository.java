package studia.inz.inzynierka.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import studia.inz.inzynierka.Entites.RoleEntity;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Integer>, JpaSpecificationExecutor {
    RoleEntity findByName(String name);
}
