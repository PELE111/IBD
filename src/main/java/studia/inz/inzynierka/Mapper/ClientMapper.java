package studia.inz.inzynierka.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import studia.inz.inzynierka.DTO.ClientDTO;
import studia.inz.inzynierka.Entites.ClientEntity;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO clientToDTO(ClientEntity client);
    ClientEntity clientDtoToClient(ClientDTO clientDTO);
}
