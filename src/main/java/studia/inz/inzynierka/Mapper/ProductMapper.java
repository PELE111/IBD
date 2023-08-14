package studia.inz.inzynierka.Mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import studia.inz.inzynierka.Models.DTO.ProductDto;
import studia.inz.inzynierka.Models.Entites.ProductEntity;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    List<ProductDto> productsToDtos(List<ProductEntity> products);
}
