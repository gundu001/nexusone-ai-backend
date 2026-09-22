package ai.nexusone.adapter;

import ai.nexusone.domain.ExecutionRecord;
import org.springframework.stereotype.Component;

@Component
public class SimulationExecutionAdapter implements ExecutionAdapter {
    @Override
    public AdapterResult execute(ExecutionRecord record) {
        if (record.getTargetName().toLowerCase().contains("fail-simulation")) {
            return new AdapterResult(false, "Simulated adapter failure requested by target name.");
        }
        return new AdapterResult(true,
            "Simulation completed for " + record.getActionType() + " on " + record.getTargetName() + ".");
    }

    @Override
    public AdapterResult rollback(ExecutionRecord record) {
        return new AdapterResult(true,
            "Simulation rollback completed for execution " + record.getId() + ".");
    }
}
