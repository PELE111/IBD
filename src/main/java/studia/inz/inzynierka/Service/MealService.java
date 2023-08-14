package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.Models.ApiRequest.CreateMeal;
import studia.inz.inzynierka.Models.ApiRequest.Ingredient;
import studia.inz.inzynierka.Models.ApiRequest.MealFilter;
import studia.inz.inzynierka.Models.ApiRequest.MealValues;
import studia.inz.inzynierka.Models.Entites.MealEntity;
import studia.inz.inzynierka.Models.Entites.MealIngredientsEntity;
import studia.inz.inzynierka.Models.Entites.ProductSpecEntity;
import studia.inz.inzynierka.Repos.MealIngredientsRepository;
import studia.inz.inzynierka.Repos.MealRepository;
import studia.inz.inzynierka.Repos.ProductRepository;
import studia.inz.inzynierka.Repos.ProductSpecRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MealService {
    final private MealRepository mealRepository;
    final private ProductRepository productRepository;
    final private ProductSpecRepository productSpecRepository;
    final private MealIngredientsRepository mealIngredientsRepository;
    final private MealIngredientsService mealIngredientsService;
    private final ProductSpecService productSpecService;


    public ResponseEntity<List<MealEntity>> getAll() {
        List<MealEntity> meals = mealRepository.findAll();
        return ResponseEntity.ok(meals);
    }


    public ResponseEntity<List<MealValues>> getFiltered(MealFilter mealFilter, Pageable pageable) {
        List<MealEntity> meals;
        if (!mealFilter.isDiabetes()) {
            meals = mealRepository.findAllByNameContaining(mealFilter.getName(), pageable);
        } else
            meals = mealRepository.findAllByDiabetesAndNameContaining(mealFilter.isDiabetes(), mealFilter.getName(), pageable);

        List<MealValues> mealValues = new ArrayList<>();

        for (MealEntity meal : meals) {

            float amount = 0, calories = 0, sugar = 0, fats = 0, protein = 0;

            List<MealIngredientsEntity> mealIngredients = meal.getMealIngredients();

            for (MealIngredientsEntity mealIngredient : mealIngredients) {
                ProductSpecEntity productSpec = new ProductSpecEntity();
                if (mealIngredient.getProductSpec() != null) {
                    productSpec = productSpecService.syncAmount(mealIngredient.getProductSpec(), mealIngredient.getAmount());
                }
                ;
                calories += productSpec.getCalories();
                amount += productSpec.getAmount();
                sugar += productSpec.getSugar();
                fats += productSpec.getFats();
                protein += productSpec.getProtein();
            }

            mealValues.add(new MealValues(meal.getName(), meal.getMealId(), amount, calories, sugar, fats, protein));
        }

        return ResponseEntity.ok(mealValues);
    }

    public ResponseEntity<MealEntity> createMeal(CreateMeal createMeal) {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setName(createMeal.getName());
        mealRepository.save(mealEntity);
        List<MealIngredientsEntity> mealIngredientsEntities = new ArrayList<>();
        for (Ingredient ingredient : createMeal.getIngredients()) {
            MealIngredientsEntity mealIngredientsEntity = new MealIngredientsEntity();
            mealIngredientsEntity.setMeal(mealEntity);
            mealIngredientsEntity.setProductSpec(productSpecRepository.findById(ingredient.getProductSpecId()).orElse(null));
            mealIngredientsEntity.setAmount(ingredient.getAmount());
            mealIngredientsRepository.save(mealIngredientsEntity);
            mealIngredientsEntities.add(mealIngredientsEntity);
        }
        if (isDiabetes(mealEntity, mealIngredientsEntities)) mealEntity.setDiabetes(true);

        return ResponseEntity.ok(mealEntity);
    }

    public boolean isDiabetes(MealEntity mealEntity, List<MealIngredientsEntity> mealIngredientsEntities) {
        for (MealIngredientsEntity ingredient : mealIngredientsEntities) {
            if (ingredient.getProductSpec() == null) return false;
            else if (ingredient.getProductSpec().getProduct().isDiabetes()) return true;
        }
        return false;
    }


}
