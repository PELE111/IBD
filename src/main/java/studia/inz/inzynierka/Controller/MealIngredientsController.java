package studia.inz.inzynierka.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import studia.inz.inzynierka.Models.DTO.MealIngredientsDto;
import studia.inz.inzynierka.Service.MealIngredientsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/v1/meal")
public class MealIngredientsController {

    private final MealIngredientsService mealIngredientsService;

    @GetMapping(value = "/ingredients")
    public ResponseEntity<List<MealIngredientsDto>> getMealIngredients(@RequestParam int mealId, Authentication authentication) {
        return mealIngredientsService.getMealProducts(mealId);
    }

}
