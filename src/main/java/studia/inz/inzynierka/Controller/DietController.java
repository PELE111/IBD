package studia.inz.inzynierka.Controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import studia.inz.inzynierka.ApiRequest.CreateDiet;
import studia.inz.inzynierka.DTO.ClientDTO;
import studia.inz.inzynierka.DTO.DietDTO;
import studia.inz.inzynierka.Entites.ClientEntity;
import studia.inz.inzynierka.Entites.DietEntity;
import studia.inz.inzynierka.Service.DietService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(value = "/v1/diet")
public class DietController {
    private final DietService dietService;




    @PostMapping(value = "create")
    public ResponseEntity<DietEntity> createDiet(@RequestBody CreateDiet diet){
        return dietService.createDiet(diet);
    }

    @PostMapping(value = "user")
    public ResponseEntity<List<DietEntity>> userDiet(@RequestBody ClientEntity client){
        return dietService.userDiet(client);
    }

    @PostMapping(value = "active")
    public ResponseEntity<DietDTO> activeDiet(@RequestBody ClientDTO clientDTO){
        return dietService.activeDiet(clientDTO);
    }

    @PostMapping(value = "edit")
    public ResponseEntity<DietDTO> editDiet(@RequestBody DietDTO diet){
        return dietService.editDiet(diet);
    }

    @PostMapping(value = "set")
    public ResponseEntity<DietDTO> setActive(@RequestBody DietDTO diet){
        return dietService.setActive(diet);
    }
}
