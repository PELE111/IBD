package studia.inz.inzynierka.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import studia.inz.inzynierka.Models.ApiRequest.ProductToBuy;
import studia.inz.inzynierka.Service.ShoppingListService;

import java.sql.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/v1/shopping")
public class ShoppingListController {

    private final ShoppingListService shoppingListService;

    @GetMapping
    ResponseEntity<List<ProductToBuy>> getShoppingList(Authentication authentication, @RequestParam Date date) {
        return shoppingListService.getShoppingList(authentication, date);

    }
}
