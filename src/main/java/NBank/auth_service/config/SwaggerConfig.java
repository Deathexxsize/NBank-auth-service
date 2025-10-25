package NBank.auth_service.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("NBank Auth Service API")
                        .description("""
                            Authentication and authorization microservice for NBank system.
                            Provides endpoints for user registration, login, token management, 
                            and password recovery.
                            """)
                        .version("0.0.1")
                        .contact(new Contact()
                                .name("NBank Team <3")
                                .email("support@nbank.dev")
                                .url("https://nbank.dev"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("NBank Platform Documentation")
                        .url("https://docs.nbank.dev"));
    }
}
