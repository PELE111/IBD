package studia.inz.inzynierka.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import studia.inz.inzynierka.Models.ApiRequest.CreateDiet;
import studia.inz.inzynierka.Models.DTO.DietDTO;
import studia.inz.inzynierka.Models.Entites.DietEntity;
import studia.inz.inzynierka.Service.DietService;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/v1/diet")
public class DietController {
    private final DietService dietService;


    @GetMapping
    public ResponseEntity<DietDTO> userDiet(Authentication authentication) {
        return dietService.getDiet(authentication.getName());
    }

    @PutMapping(value = "create")
    public ResponseEntity<DietEntity> createDiet(@RequestBody CreateDiet diet, Authentication authentication) {
        diet.setLogin(authentication.getName());
        return dietService.createDiet(diet);
    }

    @PostMapping(value = "edit")
    public ResponseEntity<DietDTO> editDiet(@RequestBody DietDTO diet, Authentication authentication) {
        diet.setLogin(authentication.getName());
        return dietService.editDiet(diet);
    }


}
