package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.ApiRequest.CreateMeal;
import studia.inz.inzynierka.ApiRequest.Ingredient;
import studia.inz.inzynierka.Entites.MealEntity;
import studia.inz.inzynierka.Entites.MealIngredientsEntity;
import studia.inz.inzynierka.Entites.ProductEntity;
import studia.inz.inzynierka.Repos.MealIngredientsRepository;
import studia.inz.inzynierka.Repos.MealRepository;
import studia.inz.inzynierka.Repos.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MealService {
    final private MealRepository mealRepository;
    final private ProductRepository productRepository;
    final private MealIngredientsRepository mealIngredientsRepository;

    public ResponseEntity<List<MealEntity>> getAll(){
        List<MealEntity> meals = mealRepository.findAll();
        return ResponseEntity.ok(meals);
    }

    public ResponseEntity<MealEntity> createMeal(CreateMeal createMeal){
        MealEntity mealEntity = new MealEntity();
        mealEntity.setName(createMeal.getName());
        mealRepository.save(mealEntity);
        for (Ingredient ingredient: createMeal.getIngredients()) {
            MealIngredientsEntity mealIngredientsEntity = new MealIngredientsEntity();
            mealIngredientsEntity.setMealId(mealEntity);
            mealIngredientsEntity.setProduct(productRepository.findById(ingredient.getProductId()).get());
            mealIngredientsEntity.setAmount(ingredient.getAmount());
            mealIngredientsRepository.save(mealIngredientsEntity);
        }
        return ResponseEntity.ok(mealEntity);
    }


}
