package fr.donovan.spotifish.handler;

import fr.donovan.spotifish.custom_response.ResponseException;
import fr.donovan.spotifish.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundSpotifishException.class)
    ResponseException notFoundResponseHandler(NotFoundSpotifishException e) {
        return new ResponseException(
            HttpStatus.NOT_FOUND,
            e.getMessage(),
            e.getValue()
        );
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedSpotifishException.class)
    ResponseException accessDeniedResponseHandler(AccessDeniedSpotifishException e) {
        return new ResponseException(
            HttpStatus.FORBIDDEN,
            e.getMessage(),
            e.getMethod()
        );
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ResponseException MethodArgumentNotValidHandleException(MethodArgumentNotValidException e) {
        List<ErrorModel> errorModels = processErrors(e);
        return new ResponseException(
                HttpStatus.UNPROCESSABLE_ENTITY,
                e.getMessage(),
                errorModels
        );
    }

    private List<ErrorModel> processErrors(MethodArgumentNotValidException e) {
        List<ErrorModel> validationErrorModels = new ArrayList<ErrorModel>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            ErrorModel validationErrorModel = new ErrorModel(
                    fieldError.getCode(),
                    fieldError.getObjectName() + "/" + fieldError.getField(),
                    fieldError.getDefaultMessage());
            validationErrorModels.add(validationErrorModel);
        }
        return validationErrorModels;
    }
}