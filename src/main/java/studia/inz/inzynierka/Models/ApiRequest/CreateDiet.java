package studia.inz.inzynierka.Models.ApiRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDiet {
    String login;
    int dailyCalories;
    boolean diabetes;
}
