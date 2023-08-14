package studia.inz.inzynierka.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import studia.inz.inzynierka.Models.DTO.ProductDto;
import studia.inz.inzynierka.Models.DTO.ProductSpecDto;
import studia.inz.inzynierka.Service.ProductSpecService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/v1/productspec")
public class ProductSpecController {

    private final ProductSpecService productSpecService;


    @PostMapping
    public ResponseEntity<List<ProductSpecDto>> getProductSpec(@RequestBody ProductDto productDto, Authentication authentication) {

        return productSpecService.getByProduct(productDto);
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<ProductSpecDto>> searchProduct(String name, Pageable pageable, Authentication authentication) {
        return productSpecService.searchByName(name, pageable);
    }
}
