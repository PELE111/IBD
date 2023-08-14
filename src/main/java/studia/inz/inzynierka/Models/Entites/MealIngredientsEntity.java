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
@Table(name = "meal_ingredients")
public class MealIngredientsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_ingredients_id", nullable = false, unique = true)
    private int mealIngredientsId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "meal_id")
    MealEntity meal;

    @ManyToOne
    @JoinColumn(name = "product_spec_id")
    ProductSpecEntity productSpec;

    @Column(name = "amount")
    float amount;
}
