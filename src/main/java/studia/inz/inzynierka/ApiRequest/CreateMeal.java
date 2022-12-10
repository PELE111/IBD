package studia.inz.inzynierka.ApiRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import studia.inz.inzynierka.Entites.ProductEntity;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMeal {
    String name;
    List<Ingredient> ingredients;
}
