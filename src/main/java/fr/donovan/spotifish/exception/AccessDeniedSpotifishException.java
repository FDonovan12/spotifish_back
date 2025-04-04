package fr.donovan.spotifish.exception;

import lombok.Getter;

@Getter
public class AccessDeniedSpotifishException extends RuntimeException {

    private final String type;

    private final String method;

    public AccessDeniedSpotifishException(String type, String method) {
        super("Entity not found");
        this.type = type;
        this.method = method;
    }
}