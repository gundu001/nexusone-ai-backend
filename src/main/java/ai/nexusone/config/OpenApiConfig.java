package ai.nexusone.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.*;

@Configuration public class OpenApiConfig {
    @Bean OpenAPI api(){
        return new OpenAPI().info(new Info().title("NexusOne AI API").version("1.0.0").description("Universal delivery control-plane MVP APIs"));
    }
}
