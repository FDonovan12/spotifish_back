package fr.donovan.spotifish.exception;

import lombok.Getter;

@Getter
public class AlreadyActiveException extends RuntimeException {

    private final String type;

    private final String field;

    private final Object value;

    public AlreadyActiveException(String type, String field, Object value) {
        super("Already Active");
        this.type = type;
        this.field = field;
        this.value = value;
    }
}