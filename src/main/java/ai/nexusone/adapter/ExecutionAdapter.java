package ai.nexusone.adapter;

import ai.nexusone.domain.ExecutionRecord;

public interface ExecutionAdapter {
    AdapterResult execute(ExecutionRecord record);
    AdapterResult rollback(ExecutionRecord record);
    record AdapterResult(boolean successful, String message) {}
}
