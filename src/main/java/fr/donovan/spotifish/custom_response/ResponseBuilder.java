package fr.donovan.spotifish.custom_response;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class ResponseBuilder<T> {

    private final HttpStatusCode statusCode;

    private ResponseBuilder(HttpStatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public static <T> ResponseBuilder<T> success(T body) {
        return new ResponseBuilder<>(HttpStatus.OK);
    }

    public static <T> ResponseBuilder<T> created(T body) {
        return new ResponseBuilder<>(HttpStatus.CREATED);
    }

    public static <T> ResponseBuilder<T> error(HttpStatus status, String message) {
        return new ResponseBuilder<>(status);
    }

}
