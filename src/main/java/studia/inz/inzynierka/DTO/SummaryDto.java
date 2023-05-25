package studia.inz.inzynierka.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import studia.inz.inzynierka.Entites.ClientEntity;

import javax.persistence.*;
import java.sql.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryDto {
    private int summaryId;

    float calories;

    float sugar;

    float fats;

    float protein;

    Date date;
}
