package ai.nexusone.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OpenApiConfig {
 @Bean OpenAPI api(){return new OpenAPI().info(new Info().title("NexusOne AI Phase 2 API").version("2.0.0").description("Application registration, GitHub integration, repository analysis and deployment simulation"));}
}
