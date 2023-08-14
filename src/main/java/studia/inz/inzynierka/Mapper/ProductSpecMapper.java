package studia.inz.inzynierka.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import studia.inz.inzynierka.Models.DTO.ProductSpecDto;
import studia.inz.inzynierka.Models.Entites.ProductSpecEntity;

import java.util.List;

@Mapper
public interface ProductSpecMapper {

    ProductSpecMapper INSTANCE = Mappers.getMapper(ProductSpecMapper.class);

    List<ProductSpecDto> productSpecsToDtos(List<ProductSpecEntity> productSpecs);

    ProductSpecDto productSpecToDto(ProductSpecEntity productSpec);
}
