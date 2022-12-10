package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.Entites.ProductEntity;
import studia.inz.inzynierka.Entites.ProductEntity_;
import studia.inz.inzynierka.Entites.ProductSpecEntity;
import studia.inz.inzynierka.Entites.ProductSpecEntity_;
import studia.inz.inzynierka.Repos.ProductSpecRepository;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSpecService {

    final private ProductSpecRepository productSpecRepository;


    public ResponseEntity<List<ProductSpecEntity>> getByProduct(ProductEntity product){
        List<ProductSpecEntity> productSpec = productSpecRepository.findAll(
                where(
                        (root, query, criteriaBuilder) -> {
                            return criteriaBuilder.equal(root.get(ProductSpecEntity_.PRODUCT).get(ProductEntity_.PRODUCT_ID), product.getProductId());
                        }
                )).stream().toList();
        return ResponseEntity.ok(productSpec);
    }

}
