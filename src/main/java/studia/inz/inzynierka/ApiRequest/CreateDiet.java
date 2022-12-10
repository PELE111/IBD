package studia.inz.inzynierka.ApiRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import studia.inz.inzynierka.Entites.ClientEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDiet {
    String login;
    int dailyCalories;
    boolean diabetes;
}
