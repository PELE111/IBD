package studia.inz.inzynierka.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietDTO {
    private Integer dietId;
    private String login;
    int dailyCalories;
    boolean diabetes;
}
