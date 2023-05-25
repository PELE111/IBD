package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import studia.inz.inzynierka.DTO.SummaryDto;
import studia.inz.inzynierka.Entites.*;
import studia.inz.inzynierka.Mapper.SummaryMapper;
import studia.inz.inzynierka.Repos.*;

import java.sql.Date;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Slf4j
@Service
@RequiredArgsConstructor
public class SummaryService {

    private final UserMealRepository userMealRepository;
    private final ClientRepository clientRepository;
    private final SummaryRepository summaryRepository;
    private final ProductSpecService productSpecService;
    private final MealIngredientsRepository mealIngredientsRepository;
    private final SummaryMapper summaryMapper= SummaryMapper.INSTANCE;

    public ResponseEntity<SummaryDto> getSummary(Authentication authentication, Date date){
        SummaryEntity summaryEntity = summaryRepository.findByClient_LoginAndDate(authentication.getName(),date);
        if(summaryEntity==null) {
            summaryEntity = updateSummary(authentication.getName(), date);
        }
        return ResponseEntity.ok(summaryMapper.summaryToDto(summaryEntity));
    }


@Transactional
    public SummaryEntity updateSummary(String login, Date date) {
        if(!clientRepository.existsByLogin(login)) return null;
        List<UserMealEntity> userMeals = userMealRepository.findByClient_LoginAndDate(login, date);

        summaryRepository.deleteByClient_LoginAndDate(login,date);

        SummaryEntity summaryEntity = new SummaryEntity();
        summaryEntity.setClient(clientRepository.findByLogin(login));
        summaryEntity.setDate(date);


        for (UserMealEntity um: userMeals) {
            List<MealIngredientsEntity> mealIngredients = mealIngredientsRepository.findByMealId(um.getMeal());
            for (MealIngredientsEntity mealIng: mealIngredients) {
                ProductSpecEntity productSpec = productSpecService.syncAmount(mealIng.getProductSpec(), mealIng.getAmount());
                summaryEntity.setCalories(summaryEntity.getCalories()+productSpec.getCalories());
                summaryEntity.setSugar(summaryEntity.getSugar()+productSpec.getSugar());
                summaryEntity.setFats(summaryEntity.getFats()+productSpec.getFats());
                summaryEntity.setProtein(summaryEntity.getProtein()+productSpec.getProtein());
            }
        }

        summaryRepository.save(summaryEntity);
        return summaryEntity;
    }
}
