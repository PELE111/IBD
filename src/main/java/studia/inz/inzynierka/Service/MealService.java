package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.ApiRequest.CreateMeal;
import studia.inz.inzynierka.ApiRequest.Ingredient;
import studia.inz.inzynierka.ApiRequest.MealFilter;
import studia.inz.inzynierka.Entites.MealEntity;
import studia.inz.inzynierka.Entites.MealEntity_;
import studia.inz.inzynierka.Entites.MealIngredientsEntity;
import studia.inz.inzynierka.Entites.ProductEntity;
import studia.inz.inzynierka.Repos.MealIngredientsRepository;
import studia.inz.inzynierka.Repos.MealRepository;
import studia.inz.inzynierka.Repos.ProductRepository;
import studia.inz.inzynierka.Repos.ProductSpecRepository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Slf4j
@Service
@RequiredArgsConstructor
public class MealService {
    final private MealRepository mealRepository;
    final private ProductRepository productRepository;
    final private ProductSpecRepository productSpecRepository;
    final private MealIngredientsRepository mealIngredientsRepository;

    public ResponseEntity<List<MealEntity>> getAll(){
        List<MealEntity> meals = mealRepository.findAll();
        return ResponseEntity.ok(meals);
    }


    public ResponseEntity<List<MealEntity>> getFiltered(MealFilter mealFilter){

        List<MealEntity> meals = mealRepository.findAll(
                where(
                (root, query, criteriaBuilder) -> {
                    List<javax.persistence.criteria.Predicate> predicates = new ArrayList<>();


                     if (mealFilter.isDiabetes()) predicates.add(
                            criteriaBuilder.isFalse (root.get(MealEntity_.DIABETES))
                    );

                    Predicate[] predicatesArr = predicates.toArray(new Predicate[0]);
                    return  criteriaBuilder.and(predicatesArr);
                }
        ));

        return ResponseEntity.ok(meals);
    }

    public ResponseEntity<MealEntity> createMeal(CreateMeal createMeal){
        MealEntity mealEntity = new MealEntity();
        mealEntity.setName(createMeal.getName());
        mealRepository.save(mealEntity);
        List<MealIngredientsEntity> mealIngredientsEntities = new ArrayList<>();
        for (Ingredient ingredient: createMeal.getIngredients()) {
            MealIngredientsEntity mealIngredientsEntity = new MealIngredientsEntity();
            mealIngredientsEntity.setMealId(mealEntity);
            mealIngredientsEntity.setProductSpec(productSpecRepository.findById(ingredient.getProductSpecId()).get());
            mealIngredientsEntity.setAmount(ingredient.getAmount());
            mealIngredientsRepository.save(mealIngredientsEntity);
            mealIngredientsEntities.add(mealIngredientsEntity);
        }
        if (isDiabetes(mealEntity, mealIngredientsEntities)) mealEntity.setDiabetes(true);

        return ResponseEntity.ok(mealEntity);
    }

    public boolean isDiabetes(MealEntity mealEntity, List<MealIngredientsEntity> mealIngredientsEntities){
        for (MealIngredientsEntity ingredient : mealIngredientsEntities) {
            if(ingredient.getProductSpec().getProduct().isDiabetes()) return true;
        }
        return false;
    }


}
