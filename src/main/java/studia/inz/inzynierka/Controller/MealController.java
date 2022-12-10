package studia.inz.inzynierka.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studia.inz.inzynierka.Entites.MealEntity;
import studia.inz.inzynierka.Service.MealService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/v1/meal")
public class MealController {

    private final MealService mealService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<MealEntity>> getAll(){
        return mealService.getAll();
    }

}
