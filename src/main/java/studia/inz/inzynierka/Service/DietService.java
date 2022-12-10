package studia.inz.inzynierka.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import studia.inz.inzynierka.ApiRequest.CreateDiet;
import studia.inz.inzynierka.DTO.ClientDTO;
import studia.inz.inzynierka.DTO.DietDTO;
import studia.inz.inzynierka.Entites.ClientEntity;
import studia.inz.inzynierka.Entites.ClientEntity_;
import studia.inz.inzynierka.Entites.DietEntity;
import studia.inz.inzynierka.Entites.DietEntity_;
import studia.inz.inzynierka.Mapper.ClientMapper;
import studia.inz.inzynierka.Mapper.DietMapper;
import studia.inz.inzynierka.Repos.ClientRepository;
import studia.inz.inzynierka.Repos.DietRepository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.Specification.where;

@Slf4j
@Service
@RequiredArgsConstructor
public class DietService {

    final private DietRepository dietRepository;
    final private ClientRepository clientRepository;

    final private DietMapper dietMapper = DietMapper.INSTANCE;
    final private ClientMapper clientMapper = ClientMapper.INSTANCE;

    public ResponseEntity createDiet(CreateDiet diet){
        if(diet == null){

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nie podano danych");
        }
        DietEntity newDiet = new DietEntity();
        newDiet.setClientID(clientRepository.findByLogin(diet.getLogin()));
        newDiet.setDailyCalories(diet.getDailyCalories());
        newDiet.setDiabetes(diet.isDiabetes());
        dietRepository.save(newDiet);
        return ResponseEntity.status(HttpStatus.CREATED).body(diet);
    }

    public ResponseEntity<List<DietEntity>> userDiet(ClientEntity client){

       List<DietEntity> diet =  dietRepository.findAll(
               where(
                       (root, query, criteriaBuilder) -> {
                       return criteriaBuilder.equal(criteriaBuilder.lower(root.get(DietEntity_.CLIENT_ID).get(ClientEntity_.LOGIN)), client.getLogin().toLowerCase());
                       }
               )).stream().toList();
        return ResponseEntity.ok(diet);
    }

    public ResponseEntity<DietDTO> activeDiet(ClientDTO clientDTO){

        ClientEntity client = clientMapper.clientDtoToClient(clientDTO);
        Optional <DietEntity> diet =  dietRepository.findOne(

                where(
                        (root, query, criteriaBuilder) -> {
                            return criteriaBuilder.and(criteriaBuilder.equal(criteriaBuilder.lower(root.get(DietEntity_.CLIENT_ID).get(ClientEntity_.LOGIN)), client.getLogin().toLowerCase()),
                             criteriaBuilder.equal(root.get(DietEntity_.ACTIVE), true));
                        }
                ));
        if(diet.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dietMapper.dietToDTO(diet.get()));
    }

    public ResponseEntity<DietDTO> editDiet(DietDTO diet1){

        DietEntity diet = dietMapper.dietDtoToDiet(diet1);
        if(dietRepository.existsById(diet.getDietId())) {

            DietEntity diet2 =  dietRepository.findById(diet.getDietId()).get();
            diet2.setDailyCalories(diet.getDailyCalories());
            diet2.setDiabetes(diet.isDiabetes());
            diet2.setActive(diet.isActive());
            dietRepository.save(diet2);
            return ResponseEntity.ok(diet1);
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dietMapper.dietToDTO(diet));
    }


    public ResponseEntity<DietDTO> setActive(DietDTO diet){


        if(dietRepository.existsById(diet.getDietId())) {

            ClientEntity client =  dietRepository.findById(diet.getDietId()).get().getClientID();

            Optional <DietEntity> diet1 =  dietRepository.findOne(

                    where(
                            (root, query, criteriaBuilder) -> {
                                return criteriaBuilder.and(criteriaBuilder.equal(criteriaBuilder.lower(root.get(DietEntity_.CLIENT_ID).get(ClientEntity_.LOGIN)), client.getLogin().toLowerCase()),
                                        criteriaBuilder.equal(root.get(DietEntity_.ACTIVE), true));
                            }
                    ));
            if (!diet1.isEmpty()) {
                diet1.get().setActive(false);
                dietRepository.save(diet1.get());
            }
            DietEntity diet2 =  dietRepository.findById(diet.getDietId()).get();

            diet2.setActive(true);
            dietRepository.save(diet2);
            return ResponseEntity.ok(diet);
        }

        else return ResponseEntity.status(HttpStatus.CONFLICT).body(diet);

    }


}
