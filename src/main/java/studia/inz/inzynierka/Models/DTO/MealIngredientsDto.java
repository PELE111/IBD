package studia.inz.inzynierka.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import studia.inz.inzynierka.Models.Entites.MealEntity;
import studia.inz.inzynierka.Models.Entites.ProductSpecEntity;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealIngredientsDto {

    MealEntity mealId;

    ProductSpecEntity productSpec;

    float amount;
}
