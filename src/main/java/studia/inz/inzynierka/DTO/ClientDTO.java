package studia.inz.inzynierka.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
    int clientId;
    String login;
}
