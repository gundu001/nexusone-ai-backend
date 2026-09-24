package ai.nexusone.exception;
public class BoardroomBriefingNotFoundException extends RuntimeException {
 public BoardroomBriefingNotFoundException(Long id){super("Boardroom briefing not found: "+id);}
}
