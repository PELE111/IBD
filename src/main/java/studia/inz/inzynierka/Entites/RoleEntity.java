package studia.inz.inzynierka.Entites;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="role")
public class RoleEntity {
    @Id
    @Column(name = "role_id", nullable = false)
    private int roleId;

    @Column(name = "name")
    String name;
}
