package studia.inz.inzynierka.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studia.inz.inzynierka.Entites.MealEntity;
import studia.inz.inzynierka.Entites.MealIngredientsEntity;
import studia.inz.inzynierka.Service.MealIngredientsService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/v1/meal")
public class MealIngredientsController {

    private final MealIngredientsService mealIngredientsService;

    @PostMapping(value = "/ingredients")
    public ResponseEntity<List<MealIngredientsEntity>> getMealIngreients(@RequestBody MealEntity meal){
        return mealIngredientsService.getMealProducts(meal);
    }
}
