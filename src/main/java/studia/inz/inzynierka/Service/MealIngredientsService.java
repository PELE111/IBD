package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.Mapper.MealIngredientsMapper;
import studia.inz.inzynierka.Models.DTO.MealIngredientsDto;
import studia.inz.inzynierka.Models.Entites.MealEntity;
import studia.inz.inzynierka.Models.Entites.MealIngredientsEntity;
import studia.inz.inzynierka.Repos.MealIngredientsRepository;
import studia.inz.inzynierka.Repos.MealRepository;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class MealIngredientsService {

    private final MealIngredientsRepository mealIngredientsRepository;
    private final ProductSpecService productSpecService;
    private final MealRepository mealRepository;
    private final MealIngredientsMapper mealIngredientsMapper = MealIngredientsMapper.INSTANCE;

    public ResponseEntity<List<MealIngredientsDto>> getMealProducts(int mealId) {
        Optional<MealEntity> meal = mealRepository.findById(mealId);
        if (meal.isEmpty()) return ResponseEntity.notFound().build();
        List<MealIngredientsEntity> mealIngredients = null;
        mealIngredients = mealIngredientsRepository.findByMeal(meal.get());
        List<MealIngredientsDto> mealIngredientsDtos = mealIngredientsMapper.mealIngredientsToDtos(mealIngredients);

        for (MealIngredientsDto meals : mealIngredientsDtos) {
            meals.setProductSpec(productSpecService.syncAmount(meals.getProductSpec(), meals.getAmount()));
        }


        return ResponseEntity.status(HttpStatus.OK).body(mealIngredientsDtos);
    }
}
