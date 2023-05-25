package studia.inz.inzynierka.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public class ProductSpecMapper {
    ProductSpecMapper INSTANCE = Mappers.getMapper(ProductSpecMapper.class);

}
