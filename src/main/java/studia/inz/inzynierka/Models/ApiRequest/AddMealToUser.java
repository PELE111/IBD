package studia.inz.inzynierka.Models.ApiRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddMealToUser {
    int userId;
    int mealId;
    Date date;
}
