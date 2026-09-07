package ai.nexusone.dto;
import jakarta.validation.constraints.NotBlank;
public record RegisterApplicationRequest(@NotBlank String name,@NotBlank String repositoryUrl,@NotBlank String technology,String branch){}
