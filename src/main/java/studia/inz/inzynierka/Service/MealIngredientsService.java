package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.DTO.MealIngredientsDto;
import studia.inz.inzynierka.Entites.*;
import studia.inz.inzynierka.Entites.MealEntity_;
import studia.inz.inzynierka.Entites.ProductEntity_;
import studia.inz.inzynierka.Mapper.MealIngredientsMapper;
import studia.inz.inzynierka.Repos.MealIngredientsRepository;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;


@Slf4j
@Service
@RequiredArgsConstructor
public class MealIngredientsService {

    private final MealIngredientsRepository mealIngredientsRepository;
    private final ProductSpecService productSpecService;
    private final MealIngredientsMapper mealIngredientsMapper = MealIngredientsMapper.INSTANCE;

    public ResponseEntity<List<MealIngredientsDto>> getMealProducts(MealEntity meal){
        List<MealIngredientsDto> mealIngredientsEntities  = mealIngredientsMapper.mealIngredientsToDtos(mealIngredientsRepository.findByMealId(meal));
        for (MealIngredientsDto meals: mealIngredientsEntities) {
            meals.setProductSpec(productSpecService.syncAmount(meals.getProductSpec(), meals.getAmount()));
        }
        return ResponseEntity.ok(mealIngredientsEntities);
    }
}
