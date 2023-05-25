package studia.inz.inzynierka.Entites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Meal")
public class MealEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id", nullable = false, unique = true)
    private int mealId;

    @Column(name = "name")
    String name;

    @Column(name = "diabetes")
    boolean diabetes;

   // @OneToMany
   // @JoinColumn(name = "meal_id")
   // List<MealIngredientsEntity> mealIngredients;
}
