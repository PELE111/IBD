package studia.inz.inzynierka.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import studia.inz.inzynierka.Entites.UserMealEntity;

public interface UserMealRepository extends JpaRepository<UserMealEntity, Integer>, JpaSpecificationExecutor {
}
