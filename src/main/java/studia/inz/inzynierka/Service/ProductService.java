package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.Mapper.ProductSpecMapper;
import studia.inz.inzynierka.Models.ApiRequest.ProductValues;
import studia.inz.inzynierka.Models.DTO.ProductSpecDto;
import studia.inz.inzynierka.Models.Entites.ProductEntity;
import studia.inz.inzynierka.Repos.ProductRepository;
import studia.inz.inzynierka.Repos.ProductSpecRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    final private ProductRepository productRepository;
    final private ProductSpecRepository productSpecRepository;
    final private ProductSpecMapper productSpecMapper = ProductSpecMapper.INSTANCE;

    public ResponseEntity<List<ProductEntity>> getAll() {
        List<ProductEntity> products = productRepository.findAll();
        return ResponseEntity.ok(products);
    }

    public ResponseEntity<List<ProductValues>> searchByName(String name, Pageable pageable) {
        List<ProductEntity> products;
        products = productRepository.findAllByNameContaining(name, (pageable == null ? Pageable.unpaged() : pageable));
        List<ProductValues> productValues = new ArrayList<>();
        for (ProductEntity product : products) {
            ProductValues productValue = new ProductValues(
                    product.getName(),
                    product.getProductId(),
                    productSpecMapper.productSpecToDto(productSpecRepository.findFirstByProduct(product)));
            if (productValue.getProductSpecs() == null) productValue.setProductSpecs(new ProductSpecDto());
            productValues.add(productValue);
        }
        return ResponseEntity.ok(productValues);
    }
}
