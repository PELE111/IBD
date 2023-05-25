package studia.inz.inzynierka.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import studia.inz.inzynierka.ApiRequest.AddMealToUser;
import studia.inz.inzynierka.DTO.UserMealsDTO;
import studia.inz.inzynierka.Entites.ClientEntity;
import studia.inz.inzynierka.Entites.ClientEntity_;
import studia.inz.inzynierka.Entites.UserMealEntity;
import studia.inz.inzynierka.Entites.UserMealEntity_;
import studia.inz.inzynierka.Mapper.UserMealMapper;
import studia.inz.inzynierka.Repos.ClientRepository;
import studia.inz.inzynierka.Repos.MealRepository;
import studia.inz.inzynierka.Repos.UserMealRepository;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserMealService {

    private final UserMealRepository userMealRepository;
    private final MealRepository mealRepository;
    private final ClientRepository clientRepository;
    private final UserMealMapper userMealMapper = UserMealMapper.INSTANCE;




    public ResponseEntity<List<UserMealsDTO>> getUserMeals(String login){
        if(!clientRepository.existsByLogin(login)) return ResponseEntity.notFound().build();
        List<UserMealEntity> userMeals = (userMealRepository.findAll(
                where(
                        (root, query, criteriaBuilder) -> {
                            return criteriaBuilder.equal(
                                    criteriaBuilder.lower(root.get(UserMealEntity_.CLIENT).get(ClientEntity_.LOGIN)), login.toLowerCase()
                            );
                        }
                )
        )).stream().toList();
        return ResponseEntity.ok(userMealMapper.userMealsToDtos(userMeals));
    }

    public ResponseEntity<UserMealEntity> addMealToUser(AddMealToUser addMealToUser){
        if(!clientRepository.existsById(addMealToUser.getUserId()) || !mealRepository.existsById(addMealToUser.getMealId())) return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        UserMealEntity userMealEntity = new UserMealEntity();
        userMealEntity.setMeal(mealRepository.findById(addMealToUser.getMealId()).get());
        userMealEntity.setClient(clientRepository.findById(addMealToUser.getUserId()).get());
        userMealEntity.setDate(addMealToUser.getDate());
        userMealRepository.save(userMealEntity);
        return ResponseEntity.status(HttpStatus.OK).body(userMealEntity);
    }





}
