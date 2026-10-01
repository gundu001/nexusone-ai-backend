package ai.nexusone.exception;

public class DigitalBoardMemberIntelligenceNotFoundException extends RuntimeException {
    public DigitalBoardMemberIntelligenceNotFoundException(Long id) {
        super("Digital Board Member intelligence report not found: " + id);
    }
}
