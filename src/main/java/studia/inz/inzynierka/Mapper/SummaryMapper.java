package studia.inz.inzynierka.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import studia.inz.inzynierka.DTO.SummaryDto;
import studia.inz.inzynierka.Entites.SummaryEntity;

@Mapper
public interface SummaryMapper {

    SummaryMapper INSTANCE = Mappers.getMapper(SummaryMapper.class);
    SummaryDto summaryToDto(SummaryEntity summaryEntity);
}
