package studia.inz.inzynierka.Models.ApiRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import studia.inz.inzynierka.Models.DTO.ProductSpecDto;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductValues {
    String name;

    int productId;


    ProductSpecDto productSpecs;
}
