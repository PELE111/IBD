package studia.inz.inzynierka.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.Mapper.ProductSpecMapper;
import studia.inz.inzynierka.Models.ApiRequest.ProductValues;
import studia.inz.inzynierka.Models.DTO.ProductDto;
import studia.inz.inzynierka.Models.DTO.ProductSpecDto;
import studia.inz.inzynierka.Models.Entites.ProductEntity;
import studia.inz.inzynierka.Models.Entites.ProductSpecEntity;
import studia.inz.inzynierka.Repos.ProductRepository;
import studia.inz.inzynierka.Repos.ProductSpecRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSpecService {

    final private ProductSpecRepository productSpecRepository;
    final private ProductRepository productRepository;
    ProductSpecMapper productSpecMapper = ProductSpecMapper.INSTANCE;


    public ResponseEntity<List<ProductSpecDto>> getByProduct(ProductDto productDto) {
        ProductEntity product = productRepository.findById(productDto.getProductId()).get();
        if (product == null) return ResponseEntity.notFound().build();
        List<ProductSpecEntity> productSpec = productSpecRepository.findAllByProduct(product);
        List<ProductSpecDto> productSpecDtos = productSpecMapper.productSpecsToDtos(productSpec);
        return ResponseEntity.ok(productSpecDtos);
    }


    public ProductSpecEntity syncAmount(ProductSpecEntity productSpec, float amount) {

        float mul = amount / productSpec.getAmount();
        ProductSpecEntity productSpec1 = new ProductSpecEntity();
        productSpec1.setProduct(productSpec.getProduct());
        productSpec1.setCalories(productSpec.getCalories() * mul);
        productSpec1.setProtein(productSpec.getProtein() * mul);
        productSpec1.setFats(productSpec.getFats() * mul);
        productSpec1.setSugar(productSpec.getSugar() * mul);
        productSpec1.setAmount(amount);
        return productSpec1;
    }

    public ResponseEntity<List<ProductSpecDto>> searchByName(String name, Pageable pageable) {
        List<ProductSpecEntity> products;
        if (name.isEmpty())
            products = productSpecRepository.findAll(pageable == null ? Pageable.unpaged() : pageable).toList();
        else
            products = productSpecRepository.findAllByProduct_NameContaining(name, (pageable == null ? Pageable.unpaged() : pageable));
        List<ProductValues> productValues = new ArrayList<>();

        List<ProductSpecDto> productDtos = productSpecMapper.productSpecsToDtos(products);
        return ResponseEntity.ok(productDtos);
    }

}
