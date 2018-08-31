package com.ajax.shop.exception;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 31.08.18
 */
public class ValidationException extends RuntimeException {
    private String clientMessage;

    public ValidationException(String message, Throwable cause, String clientMessage) {
        super(message, cause);
        this.clientMessage = clientMessage;
    }

    public ValidationException(String clientMessage) {
        this.clientMessage = clientMessage;
    }

    public String getClientMessage() {
        return clientMessage;
    }
}
