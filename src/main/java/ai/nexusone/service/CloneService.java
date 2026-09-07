package ai.nexusone.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@Service
public class CloneService {

    private static final String REPO_FOLDER = "repos";

    public String cloneRepository(String repoUrl) {

        try {

            // Extract repository name
            String repoName = repoUrl
                    .substring(repoUrl.lastIndexOf("/") + 1)
                    .replace(".git", "");

            // Create repos directory if not exists
            File reposDir = new File(REPO_FOLDER);

            if (!reposDir.exists()) {
                reposDir.mkdirs();
            }

            // Check if repo already exists
            File targetRepo = new File(reposDir, repoName);

            if (targetRepo.exists()) {
                return "Repository already exists: "
                        + targetRepo.getAbsolutePath();
            }

            // Execute git clone command
            ProcessBuilder processBuilder =
                    new ProcessBuilder(
                            "git",
                            "clone",
                            repoUrl
                    );

            processBuilder.directory(reposDir);

            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    process.getInputStream()
                            )
                    );

            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line)
                        .append(System.lineSeparator());
            }

            BufferedReader errorReader =
                    new BufferedReader(
                            new InputStreamReader(
                                    process.getErrorStream()
                            )
                    );

            while ((line = errorReader.readLine()) != null) {
                output.append(line)
                        .append(System.lineSeparator());
            }

            int exitCode = process.waitFor();

            if (exitCode == 0) {

                return """
                        Repository cloned successfully!

                        Repository Name: %s

                        Location: %s
                        """
                        .formatted(
                                repoName,
                                targetRepo.getAbsolutePath()
                        );
            }

            return """
                    Clone failed.

                    Exit Code: %d

                    Details:
                    %s
                    """
                    .formatted(exitCode, output.toString());

        } catch (Exception e) {

            e.printStackTrace();

            return """
                    Clone failed.

                    Error:
                    %s
                    """
                    .formatted(e.getMessage());
        }
    }
}