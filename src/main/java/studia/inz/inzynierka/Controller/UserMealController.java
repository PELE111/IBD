package studia.inz.inzynierka.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studia.inz.inzynierka.ApiRequest.AddMealToUser;
import studia.inz.inzynierka.Entites.ClientEntity;
import studia.inz.inzynierka.Entites.UserMealEntity;

import studia.inz.inzynierka.Service.UserMealService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/v1/usermeal")
public class UserMealController {

    private final UserMealService userMealService;

    @PostMapping(value = "/user")
    public ResponseEntity<List<UserMealEntity>> getByUser(@RequestBody ClientEntity client){
        return userMealService.getUserMeals(client);
    }

    @PostMapping(value = "/addmeal")
    public ResponseEntity<UserMealEntity> addMealToUser(@RequestBody AddMealToUser addMealToUser){
        return userMealService.addMealToUser(addMealToUser);
    }
}
