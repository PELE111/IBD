package studia.inz.inzynierka.Models.Entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diet")
public class DietEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diet_id", unique = true)
    private Integer dietId;

    @OneToOne
    @JoinColumn(name = "client_id")
    ClientEntity clientID;

    @Column(name = "daily_calories")
    int dailyCalories;

    @Column(name = "diabetes")
    boolean diabetes;
}
