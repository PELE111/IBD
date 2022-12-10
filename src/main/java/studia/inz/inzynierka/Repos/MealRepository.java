package studia.inz.inzynierka.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import studia.inz.inzynierka.Entites.MealEntity;

@Repository
public interface MealRepository extends JpaRepository<MealEntity, Integer>, JpaSpecificationExecutor {
}
