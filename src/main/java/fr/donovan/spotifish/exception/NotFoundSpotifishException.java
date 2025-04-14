package fr.donovan.spotifish.exception;

import lombok.Getter;

@Getter
public class NotFoundSpotifishException extends RuntimeException {

    private final String field;

    private final Object value;

    public NotFoundSpotifishException(String message, String field, Object value) {
        super(message);
        this.field = field;
        this.value = value;
    }
}