package com.task.manager.exception;

import com.task.manager.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExcepetionHandler {

    @ExceptionHandler(DeleteNotAllowedException.class)
    public ResponseEntity<ErrorResponse> deleteNotAllowedExceptionHandler(DeleteNotAllowedException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("code", ErrorsEnum.NOT_ALLOWED_TO_DELETE.toString());
        response.put("message", "Deleting a task with a status other than CREATED is not allowed");

        ErrorResponse errorResponse = buildErrorResponse(response);

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NotAllowedToChangeStatusException.class)
    public ResponseEntity<ErrorResponse> notAllowedToChangeStatusExceptionHandler(NotAllowedToChangeStatusException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("code", ErrorsEnum.NOT_ALLOWED_TO_CHANGE_STATUS.toString());
        response.put("message", ex.getMessage());

        ErrorResponse errorResponse = buildErrorResponse(response);

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(TaskAlreadyExistsExcepetion.class)
    public ResponseEntity<ErrorResponse> taskAlreadyExistsExceptionHandler(TaskAlreadyExistsExcepetion ex) {
        Map<String, String> response = new HashMap<>();
        response.put("code", ErrorsEnum.TASK_ALREADY_EXISTS.toString());
        response.put("message", ex.getMessage());

        ErrorResponse errorResponse = buildErrorResponse(response);

        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private static ErrorResponse buildErrorResponse(Map<String, String> response) {
        return ErrorResponse.builder()
                .status(HttpStatus.UNPROCESSABLE_ENTITY.toString())
                .errors(Collections.singletonList(response))
                .build();
    }

}
