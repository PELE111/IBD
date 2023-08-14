package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studia.inz.inzynierka.Mapper.SummaryMapper;
import studia.inz.inzynierka.Models.DTO.SummaryDto;
import studia.inz.inzynierka.Models.Entites.MealIngredientsEntity;
import studia.inz.inzynierka.Models.Entites.ProductSpecEntity;
import studia.inz.inzynierka.Models.Entites.SummaryEntity;
import studia.inz.inzynierka.Models.Entites.UserMealEntity;
import studia.inz.inzynierka.Repos.ClientRepository;
import studia.inz.inzynierka.Repos.MealIngredientsRepository;
import studia.inz.inzynierka.Repos.SummaryRepository;
import studia.inz.inzynierka.Repos.UserMealRepository;

import java.sql.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SummaryService {

    private final UserMealRepository userMealRepository;
    private final ClientRepository clientRepository;
    private final SummaryRepository summaryRepository;
    private final ProductSpecService productSpecService;
    private final MealIngredientsRepository mealIngredientsRepository;
    private final SummaryMapper summaryMapper = SummaryMapper.INSTANCE;

    public ResponseEntity<SummaryDto> getSummary(String name, Date date) {
        if (date == null) return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        SummaryEntity summaryEntity = summaryRepository.findByClient_LoginAndDate(name, date);
        if (summaryEntity == null) {
            if (!clientRepository.existsByLogin(name)) return ResponseEntity.notFound().build();
            summaryEntity = updateSummary(name, date);
        }
        return ResponseEntity.ok(summaryMapper.summaryToDto(summaryEntity));
    }


    @Transactional
    public SummaryEntity updateSummary(String login, Date date) {
        if (!clientRepository.existsByLogin(login)) return null;
        List<UserMealEntity> userMeals = userMealRepository.findAllByClient_LoginAndDate(login, date);

        summaryRepository.deleteByClient_LoginAndDate(login, date);

        SummaryEntity summaryEntity = new SummaryEntity();
        summaryEntity.setClient(clientRepository.findByLogin(login));
        summaryEntity.setDate(date);


        for (UserMealEntity um : userMeals) {
            List<MealIngredientsEntity> mealIngredients = mealIngredientsRepository.findByMeal(um.getMeal());
            for (MealIngredientsEntity mealIng : mealIngredients) {
                ProductSpecEntity productSpec = productSpecService.syncAmount(mealIng.getProductSpec(), mealIng.getAmount());
                summaryEntity.setCalories(summaryEntity.getCalories() + productSpec.getCalories());
                summaryEntity.setSugar(summaryEntity.getSugar() + productSpec.getSugar());
                summaryEntity.setFats(summaryEntity.getFats() + productSpec.getFats());
                summaryEntity.setProtein(summaryEntity.getProtein() + productSpec.getProtein());
            }
        }

        summaryRepository.save(summaryEntity);
        return summaryEntity;
    }
}
