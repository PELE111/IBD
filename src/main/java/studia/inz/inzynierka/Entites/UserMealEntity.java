package studia.inz.inzynierka.Entites;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_meal")
public class UserMealEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_meal_id", nullable = false, unique = true)
    private int userMealId;


    @ManyToOne
    @JoinColumn(name = "client_id")
    ClientEntity client;

    @Column(name = "date")
    Date date;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    MealEntity meal;
}
