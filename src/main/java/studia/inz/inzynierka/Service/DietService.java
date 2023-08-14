package studia.inz.inzynierka.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.Mapper.ClientMapper;
import studia.inz.inzynierka.Mapper.DietMapper;
import studia.inz.inzynierka.Models.ApiRequest.CreateDiet;
import studia.inz.inzynierka.Models.DTO.DietDTO;
import studia.inz.inzynierka.Models.Entites.DietEntity;
import studia.inz.inzynierka.Repos.ClientRepository;
import studia.inz.inzynierka.Repos.DietRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class DietService {

    final private DietRepository dietRepository;
    final private ClientRepository clientRepository;

    final private DietMapper dietMapper = DietMapper.INSTANCE;
    final private ClientMapper clientMapper = ClientMapper.INSTANCE;

    public ResponseEntity createDiet(CreateDiet diet) {
        if (diet == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nie podano danych");
        }
        if (dietRepository.existsByClientID_Login(diet.getLogin()))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        DietEntity newDiet = new DietEntity();
        newDiet.setClientID(clientRepository.findByLogin(diet.getLogin()));
        newDiet.setDailyCalories(diet.getDailyCalories());
        newDiet.setDiabetes(diet.isDiabetes());
        dietRepository.save(newDiet);
        return ResponseEntity.status(HttpStatus.CREATED).body(diet);
    }

    public ResponseEntity<DietDTO> getDiet(String login) {
        DietEntity diet = dietRepository.findByClientID_Login(login);
        if (diet == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dietMapper.dietToDTO(diet));
    }

    public ResponseEntity<DietDTO> editDiet(DietDTO diet1) {

        DietEntity diet = dietRepository.findByClientID_Login(diet1.getLogin());
        if (diet == null) {
            diet = new DietEntity();
            diet.setClientID(clientRepository.findByLogin(diet1.getLogin()));
            if (diet.getClientID() == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        diet.setDailyCalories(diet1.getDailyCalories());
        diet.setDiabetes(diet1.isDiabetes());
        dietRepository.save(diet);
        return ResponseEntity.ok(diet1);
    }

}
