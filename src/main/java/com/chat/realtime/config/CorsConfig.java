package com.chat.realtime.config;

// import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // private final Dotenv dotenv = Dotenv.load();
	@Value("${ALLOWED_ORIGIN_1}")
    private String allowedOrigin1; // = dotenv.get("ALLOWED_ORIGIN_1");

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(allowedOrigin1)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}
