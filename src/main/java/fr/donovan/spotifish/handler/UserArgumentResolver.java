//package fr.donovan.spotifish.handler;
//
//import fr.donovan.spotifish.entity.User;
//import fr.donovan.spotifish.service.UserService;
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.core.MethodParameter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//import org.springframework.web.servlet.HandlerMapping;
//
//import java.util.Map;
//
//@Component
//public class UserArgumentResolver implements HandlerMethodArgumentResolver {
//
//    private final UserService userService;
//
//    public UserArgumentResolver(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public boolean supportsParameter(MethodParameter parameter) {
//        return parameter.getParameterType().equals(User.class);
//    }
//
//    @Override
//    public Object resolveArgument(MethodParameter parameter,
//                                  ModelAndViewContainer mavContainer,
//                                  NativeWebRequest webRequest,
//                                  WebDataBinderFactory binderFactory) throws Exception {
//        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
//
//        // Récupérer les variables de chemin depuis l'attribut
//        Map<String, String> pathVariables =
//                (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//
//        if (pathVariables != null && pathVariables.containsKey("slug")) {
//            String slug = pathVariables.get("slug");
//            return userService.getObjectById(slug);
//        }
//
//        throw new IllegalArgumentException("Slug not found in the path");
//    }
//}
//
