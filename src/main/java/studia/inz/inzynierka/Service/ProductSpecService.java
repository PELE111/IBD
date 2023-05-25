package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.Entites.*;
import studia.inz.inzynierka.Repos.ProductSpecRepository;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSpecService {

    final private ProductSpecRepository productSpecRepository;


    public ResponseEntity<List<ProductSpecEntity>> getByProduct(ProductEntity product){
        List<ProductSpecEntity> productSpec = productSpecRepository.findAllByProduct(product);
        return ResponseEntity.ok(productSpec);
    }

    public ProductSpecEntity syncAmount(ProductSpecEntity productSpec, float amount){

        float mul = amount / productSpec.getAmount();
        ProductSpecEntity productSpec1 = new ProductSpecEntity();
        productSpec1.setProduct(productSpec.getProduct());
        productSpec1.setCalories(productSpec.getCalories()*mul);
        productSpec1.setProtein(productSpec.getProtein()*mul);
        productSpec1.setFats(productSpec.getFats()*mul);
        productSpec1.setSugar(productSpec.getSugar()*mul);
        productSpec1.setAmount(amount);
        return productSpec1;
    }

}
