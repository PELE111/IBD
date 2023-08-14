package studia.inz.inzynierka.Models.ApiRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MealValues {
    String name;
    int mealId;
    float amount;
    float calories;
    float sugar;
    float fats;
    float protein;
}
