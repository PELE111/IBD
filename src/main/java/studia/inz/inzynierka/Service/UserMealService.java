package studia.inz.inzynierka.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.Entites.ClientEntity;
import studia.inz.inzynierka.Entites.ClientEntity_;
import studia.inz.inzynierka.Entites.UserMealEntity;
import studia.inz.inzynierka.Entites.UserMealEntity_;
import studia.inz.inzynierka.Repos.UserMealRepository;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserMealService {

    private final UserMealRepository userMealRepository;



    public ResponseEntity<List<UserMealEntity>> getUserMeals(ClientEntity client){
        List<UserMealEntity> userMeals = (userMealRepository.findAll(
                where(
                        (root, query, criteriaBuilder) -> {
                            return criteriaBuilder.equal(
                                    criteriaBuilder.lower(root.get(UserMealEntity_.CLIENT).get(ClientEntity_.LOGIN)), client.getLogin().toLowerCase()
                            );
                        }
                )
        )).stream().toList();
        return ResponseEntity.ok(userMeals);
    }




}
