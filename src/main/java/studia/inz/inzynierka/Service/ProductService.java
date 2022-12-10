package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.Entites.MealEntity;
import studia.inz.inzynierka.Entites.ProductEntity;
import studia.inz.inzynierka.Entites.ProductEntity_;
import studia.inz.inzynierka.Repos.ProductRepository;
import studia.inz.inzynierka.Repos.ProductSpecRepository;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    final private ProductRepository productRepository;
    final private ProductSpecRepository productSpecRepository;

    public ResponseEntity<List<ProductEntity>> getAll(){
        List<ProductEntity> products = productRepository.findAll();

        return ResponseEntity.ok(products);
    }


    public ResponseEntity<List<ProductEntity>> getAllSpec(){
        List<ProductEntity> products = productRepository.findAll();

        return ResponseEntity.ok(products);
    }


}
