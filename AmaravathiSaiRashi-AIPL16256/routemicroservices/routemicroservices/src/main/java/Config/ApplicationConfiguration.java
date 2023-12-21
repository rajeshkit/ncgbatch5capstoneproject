package Config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.oas.annotations.EnableOpenApi;

@Configuration
@EnableOpenApi
public class ApplicationConfiguration {
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}