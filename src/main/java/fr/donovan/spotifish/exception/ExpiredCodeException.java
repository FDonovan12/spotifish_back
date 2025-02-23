package fr.donovan.spotifish.exception;

import lombok.Getter;

@Getter
public class ExpiredCodeException extends RuntimeException {

    private final String type;

    private final String field;

    private final Object value;

    public ExpiredCodeException(String type, String field, Object value) {
        super("Code expired");
        this.type = type;
        this.field = field;
        this.value = value;
    }
}