package studia.inz.inzynierka.Repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import studia.inz.inzynierka.Models.Entites.MealEntity;

import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<MealEntity, Integer>, JpaSpecificationExecutor {
    List<MealEntity> findAllByDiabetesAndNameContaining(Boolean diabetes, String name, Pageable pageable);

    List<MealEntity> findAllByNameContaining(String name, Pageable pageable);

}
