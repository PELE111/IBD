package studia.inz.inzynierka.Repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import studia.inz.inzynierka.Models.Entites.ProductEntity;
import studia.inz.inzynierka.Models.Entites.ProductSpecEntity;

import java.util.List;

@Repository
public interface ProductSpecRepository extends JpaRepository<ProductSpecEntity, Integer>, JpaSpecificationExecutor {
    List<ProductSpecEntity> findAllByProduct(ProductEntity product);

    List<ProductSpecEntity> findAllByProduct_NameContaining(String name, Pageable pageable);

    ProductSpecEntity findFirstByProduct(ProductEntity product);
}
