package com.task.manager.exception;

public class NotAllowedToChangeStatusException extends RuntimeException {

    public NotAllowedToChangeStatusException() {
        super();
    }

    public NotAllowedToChangeStatusException(String message) {
        super(message);
    }

}
