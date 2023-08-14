package studia.inz.inzynierka.Models.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSpecDto {

    private int productSpecId;

    String unit = "";

    float amount;

    float calories;

    float sugar;

    float fats;

    float protein;
}
