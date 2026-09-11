package ai.nexusone.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// TODO:
// Jenkins queue item may expire before polling.
// Fall back to querying build information directly
// using the job name and build number.

@JsonIgnoreProperties(ignoreUnknown = true)
public record JenkinsQueueResponse(
        boolean cancelled,
        String why,
        Executable executable) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public record Executable(
            Integer number,
            String url) {
    }
}