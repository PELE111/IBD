package studia.inz.inzynierka.Entites;

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
@Table(name="meal_ingredients")
public class MealIngredientsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_ingredients_id", nullable = false, unique = true)
    private int mealIngredientsId;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    MealEntity mealId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;

    @Column(name = "amount")
    float amount;
}
