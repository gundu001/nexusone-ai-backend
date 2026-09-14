package ai.nexusone.service;

import ai.nexusone.dto.devopscommand.*;
import ai.nexusone.enums.CommandStatus;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class DevOpsCommandCenterService {
    private final AtomicLong commandSequence = new AtomicLong(5800);
    private final AtomicLong timelineSequence = new AtomicLong(1);
    private final List<OperationTimelineResponse> timeline = new CopyOnWriteArrayList<>();

    public DevOpsCommandCenterService() {
        timeline.add(event("PLATFORM_INITIALIZED", "Autonomous DevOps Command Center initialized.", "COMPLETED", null));
    }

    public CommandCenterOverviewResponse getOverview() {
        long successfulCommands = timeline.stream().filter(item -> "COMPLETED".equals(item.getStatus())).count();
        return new CommandCenterOverviewResponse(
                4, 3, 1, 3, successfulCommands,
                96, "HEALTHY", OffsetDateTime.now());
    }

    public List<MissionResponse> getMissions() {
        return List.of(
                new MissionResponse(1L, "Release Monitoring", "Monitor active release workflows", "RUNNING", 92, "Phase 5.7"),
                new MissionResponse(2L, "Governance Monitoring", "Validate governance and compliance gates", "RUNNING", 94, "Phase 5.3"),
                new MissionResponse(3L, "Deployment Monitoring", "Track execution and automation health", "RUNNING", 88, "Phase 5.6")
        );
    }

    public List<DevOpsRecommendationResponse> getRecommendations() {
        return List.of(
                new DevOpsRecommendationResponse(1L, "MEDIUM", "Monitor release execution 8 during promotion.", "DEPLOY_WITH_MONITORING", 91),
                new DevOpsRecommendationResponse(2L, "LOW", "Continue automated governance validation.", "RUN_GOVERNANCE_CHECK", 94),
                new DevOpsRecommendationResponse(3L, "LOW", "Keep rollback automation ready for active releases.", "MONITOR_DEPLOYMENT", 89)
        );
    }

    public List<OperationTimelineResponse> getTimeline() {
        List<OperationTimelineResponse> result = new ArrayList<>(timeline);
        result.sort(Comparator.comparing(OperationTimelineResponse::getTimestamp).reversed());
        return result;
    }

    public List<String> getSupportedCommands() {
        return List.of("START_RELEASE", "ANALYZE_DEPLOYMENT", "RUN_GOVERNANCE_CHECK", "EXECUTE_ORCHESTRATION", "MONITOR_DEPLOYMENT");
    }

    public CommandExecutionResponse execute(CommandExecutionRequest request) {
        validate(request);
        String command = request.getCommand().trim().toUpperCase(Locale.ROOT);
        if (!getSupportedCommands().contains(command)) {
            throw new IllegalArgumentException("Unsupported command: " + command);
        }
        long commandId = commandSequence.incrementAndGet();
        String message = messageFor(command, request.getExecutionId());
        timeline.add(event(command, message, CommandStatus.SUCCESS.name(), request.getExecutionId()));
        return new CommandExecutionResponse(commandId, request.getExecutionId(), command,
                CommandStatus.SUCCESS.name(), message, OffsetDateTime.now());
    }

    private void validate(CommandExecutionRequest request) {
        if (request == null || request.getCommand() == null || request.getCommand().isBlank()) {
            throw new IllegalArgumentException("command is required");
        }
        if (request.getExecutionId() == null || request.getExecutionId() <= 0) {
            throw new IllegalArgumentException("executionId must be greater than zero");
        }
    }

    private String messageFor(String command, Long executionId) {
        return switch (command) {
            case "START_RELEASE" -> "Release workflow started for execution " + executionId + ".";
            case "ANALYZE_DEPLOYMENT" -> "Deployment analysis completed for execution " + executionId + ".";
            case "RUN_GOVERNANCE_CHECK" -> "Governance validation completed for execution " + executionId + ".";
            case "EXECUTE_ORCHESTRATION" -> "Release orchestration executed for execution " + executionId + ".";
            case "MONITOR_DEPLOYMENT" -> "Deployment monitoring activated for execution " + executionId + ".";
            default -> "Command completed for execution " + executionId + ".";
        };
    }

    private OperationTimelineResponse event(String action, String details, String status, Long executionId) {
        return new OperationTimelineResponse(timelineSequence.getAndIncrement(), executionId, action, status, details, OffsetDateTime.now());
    }
}
