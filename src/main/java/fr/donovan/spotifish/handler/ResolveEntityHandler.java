package fr.donovan.spotifish.handler;

import fr.donovan.spotifish.annotation.ResolveEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Method;

@Component
public class ResolveEntityHandler implements HandlerMethodArgumentResolver {

    private final ApplicationContext applicationContext;

    public ResolveEntityHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(ResolveEntity.class);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        ResolveEntity annotation = parameter.getParameterAnnotation(ResolveEntity.class);
        Class<?> entityType = parameter.getParameterType();
        String pathVariable = annotation.pathVariable();
        String field = annotation.field();
        System.out.println(webRequest.getNativeRequest());
        System.out.println(webRequest.getNativeRequest());
        System.out.println(webRequest.getNativeRequest().toString());
        System.out.println(webRequest.getParameter("slug"));
        System.out.println(webRequest.getParameter("user"));
        System.out.println(pathVariable);
        System.out.println(field);
        String identifier = webRequest.getParameter(pathVariable);
        if (identifier == null) {
            throw new IllegalArgumentException("Path variable '" + pathVariable + "' not found");
        }

        // Obtenir le service de l'entité
        String serviceName = entityType.getSimpleName() + "Service";
        Object service = applicationContext.getBean(serviceName);

        // Rechercher l'entité
        Method findMethod = service.getClass().getMethod("findBy" + capitalize(field), String.class);
        return findMethod.invoke(service, identifier);
    }

    private String capitalize(String field) {
        return field.substring(0, 1).toUpperCase() + field.substring(1);
    }
}

