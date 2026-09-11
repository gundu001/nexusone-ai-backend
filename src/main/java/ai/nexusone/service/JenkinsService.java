package ai.nexusone.service;

import ai.nexusone.dto.JenkinsBuildResponse;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class JenkinsService {

    // Phase 4.6 starter mock. Replace this method with Jenkins REST integration in Phase 4.6.2.
    private final AtomicInteger mockBuildSequence = new AtomicInteger(1000);

    public JenkinsBuildResponse triggerBuild(String jobName) {
        if (jobName == null || jobName.isBlank()) {
            throw new IllegalArgumentException("Jenkins job name is required");
        }
        int buildNumber = mockBuildSequence.incrementAndGet();
        return new JenkinsBuildResponse(
                buildNumber,
                "Mock Jenkins job triggered successfully: " + jobName
        );
    }
}
