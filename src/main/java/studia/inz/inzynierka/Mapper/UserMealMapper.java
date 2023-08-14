package studia.inz.inzynierka.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import studia.inz.inzynierka.Models.DTO.UserMealsDTO;
import studia.inz.inzynierka.Models.Entites.UserMealEntity;

import java.util.List;

@Mapper
public interface UserMealMapper {

    UserMealMapper INSTANCE = Mappers.getMapper(UserMealMapper.class);

    UserMealsDTO userMealToDto(UserMealEntity userMeal);

    List<UserMealsDTO> userMealsToDtos(List<UserMealEntity> usermeals);

}
