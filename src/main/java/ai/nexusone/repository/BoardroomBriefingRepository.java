package ai.nexusone.repository;
import ai.nexusone.entity.BoardroomBriefing;
import ai.nexusone.enums.BoardroomBriefingStatus;
import ai.nexusone.enums.BoardroomPriority;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BoardroomBriefingRepository extends JpaRepository<BoardroomBriefing,Long>{
 long countByStatus(BoardroomBriefingStatus status);
 Page<BoardroomBriefing> findByStatus(BoardroomBriefingStatus status, Pageable pageable);
 Page<BoardroomBriefing> findByPriority(BoardroomPriority priority, Pageable pageable);
 Page<BoardroomBriefing> findByTitleContainingIgnoreCaseOrExecutiveSummaryContainingIgnoreCaseOrBoardRecommendationContainingIgnoreCase(String a,String b,String c,Pageable pageable);
}
