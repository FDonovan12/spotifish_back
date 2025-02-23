//package fr.donovan.spotifish.configuration;
//
//import io.swagger.v3.oas.models.Operation;
//import io.swagger.v3.oas.models.parameters.Parameter;
//import org.springdoc.core.customizers.OperationCustomizer;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//
//import java.util.List;
//
//@Component
//public class OpenApiParameterCustomizer implements OperationCustomizer {
//
//    @Override
//    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
//        List<Parameter> parameters = operation.getParameters();
//        if (parameters == null) return operation;
//        List<Parameter> filteredParameters = operation.getParameters().stream()
//                .filter(parameter -> !parameter.getName().equals("user"))
//                .toList();
//
//        operation.setParameters(filteredParameters);
//        return operation;
//    }
//}
