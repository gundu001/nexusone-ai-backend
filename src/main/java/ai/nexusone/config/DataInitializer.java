package ai.nexusone.config;

import ai.nexusone.entity.ApplicationEntity;
import ai.nexusone.repository.ApplicationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;

@Configuration public class DataInitializer {
    @Bean CommandLineRunner seed(ApplicationRepository r){
        return args->{if(r.count()==0){
            r.save(new ApplicationEntity("Payment Service","Java 17 / Spring Boot","github/payment","HEALTHY"));
            r.save(new ApplicationEntity("Customer Portal","React / Node.js","github/portal","HEALTHY"));
            r.save(new ApplicationEntity("Risk Engine","Python","github/risk","WARNING"));
        }
        };
    }
}
