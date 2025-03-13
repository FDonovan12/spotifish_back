package fr.donovan.spotifish.custom_response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResponseException<String> extends CustomResponse<String> {

    private Object value;

    public ResponseException(HttpStatus httpStatus, String message, Object value) {
        super(httpStatus, message);
        this.value = value;
    }
}