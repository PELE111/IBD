package studia.inz.inzynierka.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.Mapper.UserMealMapper;
import studia.inz.inzynierka.Models.ApiRequest.AddMealToUser;
import studia.inz.inzynierka.Models.ApiRequest.MealValues;
import studia.inz.inzynierka.Models.Entites.MealIngredientsEntity;
import studia.inz.inzynierka.Models.Entites.ProductSpecEntity;
import studia.inz.inzynierka.Models.Entites.UserMealEntity;
import studia.inz.inzynierka.Repos.ClientRepository;
import studia.inz.inzynierka.Repos.MealRepository;
import studia.inz.inzynierka.Repos.UserMealRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserMealService {

    private final UserMealRepository userMealRepository;
    private final MealRepository mealRepository;
    private final ClientRepository clientRepository;
    private final UserMealMapper userMealMapper = UserMealMapper.INSTANCE;
    private final SummaryService summaryService;
    private final ProductSpecService productSpecService;


    public ResponseEntity removeUserMeal(int id, String login) {
        if (!userMealRepository.existsByClient_LoginAndUserMealId(login, id))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        userMealRepository.deleteById(id);
        return ResponseEntity.ok("Ususnięto");
    }

    public ResponseEntity<List<MealValues>> getUserMeals(Date date, String login) {
        List<UserMealEntity> meals = userMealRepository.findAllByClient_LoginAndDate(login, date);


        List<MealValues> mealValues = new ArrayList<>();

        for (UserMealEntity userMeal : meals) {

            float amount = 0, calories = 0, sugar = 0, fats = 0, protein = 0;

            for (MealIngredientsEntity mealIngredient : userMeal.getMeal().getMealIngredients()) {
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

            mealValues.add(new MealValues(userMeal.getMeal().getName(), userMeal.getUserMealId(), amount, calories, sugar, fats, protein));
        }

        return ResponseEntity.ok(mealValues);
    }

    public ResponseEntity<UserMealEntity> addMealToUser(AddMealToUser addMealToUser) {

        if (!clientRepository.existsById(addMealToUser.getUserId())
                || !mealRepository.existsById(addMealToUser.getMealId()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);

        UserMealEntity userMealEntity = new UserMealEntity();
        userMealEntity.setMeal(mealRepository.findById(addMealToUser.getMealId()).get());
        userMealEntity.setClient(clientRepository.findById(addMealToUser.getUserId()).get());
        userMealEntity.setDate(addMealToUser.getDate());
        userMealRepository.save(userMealEntity);
        summaryService.updateSummary(clientRepository.findById(addMealToUser.getUserId()).get().getLogin(), addMealToUser.getDate());
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
