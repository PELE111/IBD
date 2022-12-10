package studia.inz.inzynierka.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import studia.inz.inzynierka.Entites.MealIngredientsEntity;

public interface MealIngredientsRepository extends JpaRepository<MealIngredientsEntity, Integer>, JpaSpecificationExecutor {
}
