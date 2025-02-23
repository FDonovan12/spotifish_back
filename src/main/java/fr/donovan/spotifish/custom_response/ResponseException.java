package fr.donovan.spotifish.custom_response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseException<T> extends CustomResponse<T> {

    private String field;

    public ResponseException(int code, String message, String entity, T value, String field) {
        super(code, message, entity, value);
        this.field = field;
    }
}