package studia.inz.inzynierka.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import studia.inz.inzynierka.Models.Entites.UserMealEntity;

import java.sql.Date;
import java.util.List;

@Repository
public interface UserMealRepository extends JpaRepository<UserMealEntity, Integer>, JpaSpecificationExecutor {
    List<UserMealEntity> findAllByClient_LoginAndDate(String login, Date date);

    List<UserMealEntity> findAllByClient_LoginAndDateIsBetween(String login, Date today, Date date);

    boolean existsByClient_LoginAndUserMealId(String login, int id);

}
