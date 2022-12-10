package studia.inz.inzynierka.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studia.inz.inzynierka.Entites.ProductEntity;
import studia.inz.inzynierka.Entites.ProductSpecEntity;
import studia.inz.inzynierka.Service.ProductSpecService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/v1/productspec")
public class ProductSpecController {

    private final ProductSpecService productSpecService;


    @PostMapping
    public ResponseEntity<List<ProductSpecEntity>> getProductSpec(@RequestBody ProductEntity product){
        return productSpecService.getByProduct(product);
    }
}
