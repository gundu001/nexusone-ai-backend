package ai.nexusone.repository;
import ai.nexusone.entity.ApplicationEntity; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface ApplicationRepository extends JpaRepository<ApplicationEntity,String>{Optional<ApplicationEntity> findByRepositoryUrl(String repositoryUrl);}
