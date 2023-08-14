package studia.inz.inzynierka.Models.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import studia.inz.inzynierka.Models.Entites.MealEntity;

import java.sql.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMealsDTO {

    private int userMealId;
    Date date;
    MealEntity meal;
}
