package studia.inz.inzynierka.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studia.inz.inzynierka.Entites.ProductEntity;
import studia.inz.inzynierka.Service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductEntity>> getAll(){
        return productService.getAll();
    }

}
