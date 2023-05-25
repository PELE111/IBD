package studia.inz.inzynierka.Entites;

import lombok.*;
import javax.persistence.*;
import java.sql.Date;


@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="summary")
public class SummaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "summary_id", nullable = false)
    private int summaryId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    ClientEntity client;

    @Column(name = "calories")
    float calories;

    @Column(name = "sugar")
    float sugar;

    @Column(name = "fats")
    float fats;

    @Column(name = "protein")
    float protein;

    @Column(name = "date")
    Date date;


}
