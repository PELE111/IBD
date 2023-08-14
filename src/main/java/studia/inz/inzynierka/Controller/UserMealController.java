package studia.inz.inzynierka.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import studia.inz.inzynierka.Models.ApiRequest.AddMealToUser;
import studia.inz.inzynierka.Models.ApiRequest.MealValues;
import studia.inz.inzynierka.Models.Entites.UserMealEntity;
import studia.inz.inzynierka.Repos.ClientRepository;
import studia.inz.inzynierka.Repos.MealRepository;
import studia.inz.inzynierka.Service.SummaryService;
import studia.inz.inzynierka.Service.UserMealService;

import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/v1/usermeal")
public class UserMealController {

    private final UserMealService userMealService;
    private final SummaryService summaryService;
    private final ClientRepository clientRepository;
    private final MealRepository mealRepository;

    @GetMapping
    public ResponseEntity<List<MealValues>> getByUser(@RequestParam Date date, Authentication authentication) {
        return userMealService.getUserMeals(date, authentication.getName());
    }

    @GetMapping(value = "/remove")
    public ResponseEntity removeUserMeal(@RequestParam int id, Authentication authentication) {
        return userMealService.removeUserMeal(id, authentication.getName());
    }


    @PostMapping(value = "/addmeal")
    public ResponseEntity<UserMealEntity> addMealToUser(@RequestBody AddMealToUser addMealToUser, Authentication authentication) {

        return userMealService.addMealToUser(addMealToUser);
    }
}
