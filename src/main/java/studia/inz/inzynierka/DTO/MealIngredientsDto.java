package studia.inz.inzynierka.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import studia.inz.inzynierka.Entites.MealEntity;
import studia.inz.inzynierka.Entites.ProductSpecEntity;

import javax.persistence.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealIngredientsDto {

    MealEntity mealId;

    ProductSpecEntity productSpec;

    float amount;
}
