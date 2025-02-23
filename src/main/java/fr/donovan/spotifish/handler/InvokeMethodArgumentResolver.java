package fr.donovan.spotifish.handler;

import fr.donovan.spotifish.annotation.InvokeMethod;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.lang.reflect.Method;

@AllArgsConstructor
@Component
public class InvokeMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final ApplicationContext applicationContext;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(InvokeMethod.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        InvokeMethod annotation = parameter.getParameterAnnotation(InvokeMethod.class);
        System.out.println("before");
        if (annotation != null) {
            Class<?> targetClass = annotation.targetClass();
            String methodName = annotation.methodName();
            System.out.println(targetClass);
            System.out.println(methodName);

            // Créez une instance de la classe cible
            Object targetInstance;
            try {
                // Si le targetClass est un Spring Bean, obtenez-le via le contexte
                targetInstance = applicationContext.getBean(targetClass);
//                targetInstance = targetClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                // Sinon, utilisez un constructeur par défaut
                targetInstance = targetClass.getDeclaredConstructor().newInstance();
            }

            // Récupérez la méthode et son paramètre
            Method method = targetClass.getDeclaredMethod(methodName, String.class); // Exemple avec String comme argument
            method.setAccessible(true);

            // Récupérez l'argument à passer, par exemple depuis une requête web
            String argument = webRequest.getParameter("argument"); // Nom du paramètre HTTP attendu

            // Appelez la méthode non statique avec l'argument
            return method.invoke(targetInstance, "0577dd48-4216-4cdb-8f6f-fee413150150");
        }
        return null;
    }
}


