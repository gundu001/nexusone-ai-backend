package ai.nexusone.service;
import ai.nexusone.dto.*;
import ai.nexusone.entity.BoardroomBriefing;
import ai.nexusone.enums.*;
import ai.nexusone.exception.BoardroomBriefingNotFoundException;
import ai.nexusone.repository.BoardroomBriefingRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class BoardroomIntelligenceService {
 private final BoardroomBriefingRepository repository;
 public BoardroomIntelligenceService(BoardroomBriefingRepository r){repository=r;}
 @Transactional public BoardroomBriefingResponse generate(BoardroomBriefingRequest r){var e=new BoardroomBriefing();e.setTitle(r.title());e.setExecutiveSummary(r.executiveSummary());e.setStrategicAgenda(r.strategicAgenda());e.setBoardRecommendation(r.boardRecommendation());e.setExpectedRoi(r.expectedRoi());e.setStrategicAlignment(r.strategicAlignment());e.setRiskExposure(r.riskExposure());e.setPriority(r.priority());e.setBoardroomScore(score(r.strategicAlignment(),r.expectedRoi(),r.riskExposure()));e.setStatus(BoardroomBriefingStatus.DRAFTED);e.setCreatedBy(r.createdBy());return map(repository.save(e));}
 @Transactional(readOnly=true) public BoardroomBriefingResponse get(Long id){return map(find(id));}
 @Transactional(readOnly=true) public Page<BoardroomBriefingResponse> history(Pageable p){return repository.findAll(p).map(this::map);}
 @Transactional(readOnly=true) public Page<BoardroomBriefingResponse> search(String k,BoardroomPriority p,BoardroomBriefingStatus s,Pageable pg){if(k!=null&&!k.isBlank())return repository.findByTitleContainingIgnoreCaseOrExecutiveSummaryContainingIgnoreCaseOrBoardRecommendationContainingIgnoreCase(k,k,k,pg).map(this::map);if(p!=null)return repository.findByPriority(p,pg).map(this::map);if(s!=null)return repository.findByStatus(s,pg).map(this::map);return history(pg);}
 @Transactional public BoardroomBriefingResponse review(Long id,BoardroomActionRequest r){var e=find(id);require(e,BoardroomBriefingStatus.DRAFTED,"Only DRAFTED briefings can be reviewed");e.setStatus(BoardroomBriefingStatus.REVIEWED);e.setReviewedBy(r.actionBy());e.setReviewedAt(LocalDateTime.now());return map(repository.save(e));}
 @Transactional public BoardroomBriefingResponse approve(Long id,BoardroomActionRequest r){var e=find(id);require(e,BoardroomBriefingStatus.REVIEWED,"Only REVIEWED briefings can be approved");e.setStatus(BoardroomBriefingStatus.APPROVED);e.setDecidedBy(r.actionBy());e.setDecidedAt(LocalDateTime.now());return map(repository.save(e));}
 @Transactional public BoardroomBriefingResponse reject(Long id,BoardroomActionRequest r){var e=find(id);if(e.getStatus()!=BoardroomBriefingStatus.DRAFTED&&e.getStatus()!=BoardroomBriefingStatus.REVIEWED)throw new IllegalArgumentException("Only DRAFTED or REVIEWED briefings can be rejected");e.setStatus(BoardroomBriefingStatus.REJECTED);e.setDecidedBy(r.actionBy());e.setDecidedAt(LocalDateTime.now());return map(repository.save(e));}
 @Transactional public BoardroomBriefingResponse execute(Long id,BoardroomActionRequest r){var e=find(id);require(e,BoardroomBriefingStatus.APPROVED,"Only APPROVED briefings can be executed");e.setStatus(BoardroomBriefingStatus.EXECUTED);e.setExecutedBy(r.actionBy());e.setExecutedAt(LocalDateTime.now());return map(repository.save(e));}
 @Transactional(readOnly=true) public BoardroomAnalyticsResponse analytics(){List<BoardroomBriefing> a=repository.findAll();return new BoardroomAnalyticsResponse(a.size(),count(BoardroomBriefingStatus.DRAFTED),count(BoardroomBriefingStatus.REVIEWED),count(BoardroomBriefingStatus.APPROVED),count(BoardroomBriefingStatus.REJECTED),count(BoardroomBriefingStatus.EXECUTED),avg(a,"alignment"),avg(a,"roi"),avg(a,"risk"),round(a.stream().mapToDouble(BoardroomBriefing::getBoardroomScore).average().orElse(0)));}
 private void require(BoardroomBriefing e,BoardroomBriefingStatus s,String m){if(e.getStatus()!=s)throw new IllegalArgumentException(m);} private long count(BoardroomBriefingStatus s){return repository.countByStatus(s);}
 private double avg(List<BoardroomBriefing>a,String f){return round(a.stream().mapToDouble(x->switch(f){case"alignment"->x.getStrategicAlignment();case"roi"->x.getExpectedRoi();default->x.getRiskExposure();}).average().orElse(0));}
 private double score(double alignment,double roi,double risk){return round(alignment*.45+roi*.35+(100-risk)*.20);} private double round(double v){return Math.round(v*100.0)/100.0;}
 private BoardroomBriefing find(Long id){return repository.findById(id).orElseThrow(()->new BoardroomBriefingNotFoundException(id));}
 private BoardroomBriefingResponse map(BoardroomBriefing e){return new BoardroomBriefingResponse(e.getId(),e.getTitle(),e.getExecutiveSummary(),e.getStrategicAgenda(),e.getBoardRecommendation(),e.getExpectedRoi(),e.getStrategicAlignment(),e.getRiskExposure(),e.getBoardroomScore(),e.getPriority(),e.getStatus(),e.getCreatedBy(),e.getReviewedBy(),e.getReviewedAt(),e.getDecidedBy(),e.getDecidedAt(),e.getExecutedBy(),e.getExecutedAt(),e.getCreatedAt(),e.getUpdatedAt());}
}
