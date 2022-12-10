package studia.inz.inzynierka.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import studia.inz.inzynierka.Entites.ClientEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DietDTO {
    private Integer dietId;
    private ClientDTO clientId;
    int dailyCalories;
    boolean diabetes;
    boolean active;
}
