package ai.nexusone.repository;
import ai.nexusone.entity.ObservabilityAnalysis; import org.springframework.data.domain.*; import org.springframework.data.jpa.repository.JpaRepository;
public interface ObservabilityAnalysisRepository extends JpaRepository<ObservabilityAnalysis,Long>{Page<ObservabilityAnalysis> findAllByOrderByCreatedAtDesc(Pageable p);Page<ObservabilityAnalysis> findByServiceNameOrderByCreatedAtDesc(String s,Pageable p);}
