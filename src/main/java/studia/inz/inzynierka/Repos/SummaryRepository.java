package studia.inz.inzynierka.Repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import studia.inz.inzynierka.Entites.RoleEntity;
import studia.inz.inzynierka.Entites.SummaryEntity;

import java.sql.Date;

@Repository
public interface SummaryRepository extends JpaRepository<SummaryEntity, Integer>, JpaSpecificationExecutor {
    SummaryEntity findByClient_LoginAndDate(String login, Date date);
    void deleteByClient_LoginAndDate(String login, Date date);
    boolean existsByClient_LoginAndDate(String login, Date date);
}
