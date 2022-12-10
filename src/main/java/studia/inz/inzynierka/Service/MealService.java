package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.Entites.MealEntity;
import studia.inz.inzynierka.Repos.MealRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MealService {
    final private MealRepository mealRepository;

    public ResponseEntity<List<MealEntity>> getAll(){
        List<MealEntity> meals = mealRepository.findAll();
        return ResponseEntity.ok(meals);
    }


}
