package fr.donovan.spotifish.exception;

import lombok.Getter;

@Getter
public class AccessDeniedSpotifishException extends RuntimeException {

    private final String method;

    public AccessDeniedSpotifishException(String type, String method) {
        super(type);
        this.method = method;
    }
}