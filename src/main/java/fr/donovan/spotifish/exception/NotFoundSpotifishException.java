package fr.donovan.spotifish.exception;

import lombok.Getter;

@Getter
public class NotFoundSpotifishException extends RuntimeException {

    private final String type;

    private final String field;

    private final Object value;

    public NotFoundSpotifishException(String type, String field, Object value) {
        super("Entity not found");
        this.type = type;
        this.field = field;
        this.value = value;
    }
}