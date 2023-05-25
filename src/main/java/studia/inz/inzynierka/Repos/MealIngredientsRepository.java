package studia.inz.inzynierka.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import studia.inz.inzynierka.Entites.MealEntity;
import studia.inz.inzynierka.Entites.MealIngredientsEntity;

import java.util.List;

public interface MealIngredientsRepository extends JpaRepository<MealIngredientsEntity, Integer>, JpaSpecificationExecutor {
    List<MealIngredientsEntity> findByMealId(MealEntity meal);
}
