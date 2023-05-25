package studia.inz.inzynierka.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import studia.inz.inzynierka.DTO.MealIngredientsDto;
import studia.inz.inzynierka.Entites.MealIngredientsEntity;

import java.util.List;

@Mapper
public interface MealIngredientsMapper {
    MealIngredientsMapper INSTANCE = Mappers.getMapper(MealIngredientsMapper.class);

    List<MealIngredientsDto> mealIngredientsToDtos(List<MealIngredientsEntity> mealIngredients);
}