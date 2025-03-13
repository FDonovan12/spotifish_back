package fr.donovan.spotifish.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.donovan.spotifish.entity.interfaces.PermissionEntityInterface;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class JacksonConfig {


    private final SecurityBeanSerializerModifier securityBeanSerializerModifier;

    @Bean
    public SimpleModule customModule() {
        SimpleModule module = new SimpleModule("CanEditSerializer");
        module.setSerializerModifier(securityBeanSerializerModifier);
        return module;
    }
}
