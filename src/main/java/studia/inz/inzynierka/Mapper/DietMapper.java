package studia.inz.inzynierka.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import studia.inz.inzynierka.DTO.DietDTO;
import studia.inz.inzynierka.Entites.DietEntity;

import java.util.List;

@Mapper
public interface DietMapper {

    DietMapper INSTANCE = Mappers.getMapper(DietMapper.class);
    DietDTO dietToDTO(DietEntity diet);
    List<DietDTO> dietsToDtos(List<DietEntity> diets);
    DietEntity dietDtoToDiet(DietDTO dietDTO);
    List<DietEntity> dietDtosToDiets(List<DietDTO> dietDTOS);

}
