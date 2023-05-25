package studia.inz.inzynierka.Controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import studia.inz.inzynierka.ApiRequest.CreateDiet;
import studia.inz.inzynierka.DTO.ClientDTO;
import studia.inz.inzynierka.DTO.DietDTO;
import studia.inz.inzynierka.Entites.ClientEntity;
import studia.inz.inzynierka.Entites.DietEntity;
import studia.inz.inzynierka.Service.ClientService;
import studia.inz.inzynierka.Service.DietService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/v1/diet")
public class DietController {
    private final DietService dietService;
    private final ClientService clientService;




    @PutMapping(value = "create")
    public ResponseEntity<DietEntity> createDiet(@RequestBody CreateDiet diet, Authentication authentication){
        diet.setLogin(authentication.getName());
        return dietService.createDiet(diet);
    }

    @GetMapping(value = "user")
    public ResponseEntity<List<DietEntity>> userDiet(Authentication authentication){
        return dietService.userDiet(authentication.getName());
    }

    @GetMapping(value = "active")
    public ResponseEntity<DietDTO> activeDiet(Authentication authentication){
        return dietService.activeDiet(authentication.getName());
    }


    @PostMapping(value = "edit")
    public ResponseEntity<DietDTO> editDiet(@RequestBody DietDTO diet, Authentication authentication){
        diet.setLogin(authentication.getName());
        return dietService.editDiet(diet);
    }

    @PostMapping(value = "set")
    public ResponseEntity<DietDTO> setActive(@RequestBody DietDTO diet, Authentication authentication) throws HttpClientErrorException.Unauthorized {
        diet.setLogin(authentication.getName());
        return dietService.setActive(diet);
    }
}
