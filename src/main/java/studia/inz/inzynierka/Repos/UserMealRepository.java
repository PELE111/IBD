package studia.inz.inzynierka.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import studia.inz.inzynierka.Entites.UserMealEntity;

import java.sql.Date;
import java.util.List;

public interface UserMealRepository extends JpaRepository<UserMealEntity, Integer>, JpaSpecificationExecutor {
    List<UserMealEntity> findByClient_LoginAndDate(String login, Date date);
}
