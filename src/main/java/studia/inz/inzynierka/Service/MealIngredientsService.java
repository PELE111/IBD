package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.Entites.*;
import studia.inz.inzynierka.Entites.MealEntity_;
import studia.inz.inzynierka.Entites.ProductEntity_;
import studia.inz.inzynierka.Repos.MealIngredientsRepository;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;


@Slf4j
@Service
@RequiredArgsConstructor
public class MealIngredientsService {

    private final MealIngredientsRepository mealIngredientsRepository;

    public ResponseEntity<List<MealIngredientsEntity>> getMealProducts(MealEntity meal){
        List<MealIngredientsEntity> mealIngredientsEntities  = mealIngredientsRepository.findAll(
                where(
                        ((root, query, criteriaBuilder) -> {
                            return criteriaBuilder.equal(
                                    root.get(MealEntity_.MEAL_ID), meal.getMealId()
                            );
                        })
                )).stream().toList();
        return ResponseEntity.ok(mealIngredientsEntities);
    }
}
