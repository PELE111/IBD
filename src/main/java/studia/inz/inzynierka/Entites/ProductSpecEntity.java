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
@Table(name="product_spec")
public class ProductSpecEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_spec_id", nullable = false, unique = true)
    private int productSpecId;

    @ManyToOne
    @JoinColumn(name = "product_id")
    ProductEntity product;

    @Column(name = "unit")
    String unit;

    @Column(name = "amount")
    float amount;

    @Column(name = "calories")
    float calories;

    @Column(name = "sugar")
    float sugar;

    @Column(name = "fats")
    float fats;

    @Column(name = "protein")
    float protein;

}
