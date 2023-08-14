package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.Mapper.ProductSpecMapper;
import studia.inz.inzynierka.Models.ApiRequest.ProductToBuy;
import studia.inz.inzynierka.Models.Entites.MealIngredientsEntity;
import studia.inz.inzynierka.Models.Entites.ProductSpecEntity;
import studia.inz.inzynierka.Models.Entites.UserMealEntity;
import studia.inz.inzynierka.Repos.ProductRepository;
import studia.inz.inzynierka.Repos.UserMealRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShoppingListService {

    private final UserMealRepository userMealRepository;
    private final ProductRepository productRepository;

    public ResponseEntity<List<ProductToBuy>> getShoppingList(Authentication authentication, Date date) {
        Date today = new Date(System.currentTimeMillis());
        List<UserMealEntity> userMealEntities = userMealRepository.findAllByClient_LoginAndDateIsBetween(authentication.getName(), today, date);
        Map<String, ProductToBuy> productsToBuy = new HashMap<>();


        List<MealIngredientsEntity> mealIngredientsEntities = new ArrayList<>();
        for (UserMealEntity um : userMealEntities) {
            mealIngredientsEntities.addAll(um.getMeal().getMealIngredients());
        }
        for (MealIngredientsEntity mi : mealIngredientsEntities) {
            ProductToBuy product;
            if (productsToBuy.containsKey(mi.getProductSpec().getProduct().getName())) {
                product = productsToBuy.get(mi.getProductSpec().getProduct().getName());
                product.setAmount(product.getAmount() + mi.getProductSpec().getAmount());
            } else
                productsToBuy.put(mi.getProductSpec().getProduct().getName(), new ProductToBuy(mi.getProductSpec().getProduct().getName(), mi.getProductSpec().getUnit(), mi.getProductSpec().getAmount()));
        }
        List<ProductToBuy> productsToBuyList = new ArrayList<ProductToBuy>(productsToBuy.values());

        return ResponseEntity.ok(productsToBuyList);


    }
}
