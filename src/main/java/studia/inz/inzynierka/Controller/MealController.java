package studia.inz.inzynierka.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import studia.inz.inzynierka.Models.ApiRequest.CreateMeal;
import studia.inz.inzynierka.Models.ApiRequest.MealFilter;
import studia.inz.inzynierka.Models.ApiRequest.MealValues;
import studia.inz.inzynierka.Models.Entites.MealEntity;
import studia.inz.inzynierka.Service.MealService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/v1/meal")
public class MealController {

    private final MealService mealService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<MealEntity>> getAll() {
        return mealService.getAll();
    }

    @PostMapping(value = "/filter")
    public ResponseEntity<List<MealValues>> getAll(@RequestBody MealFilter mealFilter, Pageable pageable, Authentication authentication) {
        return mealService.getFiltered(mealFilter, pageable);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<MealEntity> createMeal(@RequestBody CreateMeal createMeal, Authentication authentication) {
        return mealService.createMeal(createMeal);
    }

}
