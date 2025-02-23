package fr.donovan.spotifish.configuration;

import fr.donovan.spotifish.annotation.ResolveEntity;
import fr.donovan.spotifish.handler.InvokeMethodArgumentResolver;
import fr.donovan.spotifish.handler.ResolveEntityHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@AllArgsConstructor
public class AppConfig implements WebMvcConfigurer {

    private final ResolveEntityHandler resolveEntityHandler;
    private final InvokeMethodArgumentResolver invokeMethodArgumentResolver;
//    private final UserArgumentResolver userArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(resolveEntityHandler);
        resolvers.add(invokeMethodArgumentResolver);
//        resolvers.add(userArgumentResolver);
    }
}
