package com.task.manager.validation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.task.manager.response.ErrorResponse;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class validationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> processValidation(MethodArgumentNotValidException ex) {
        List<Map<String, String>> listErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> {
                    Map<String, String> errors = new HashMap<>();
                    errors.put("Field", getPropertyName(error));
                    errors.put("Description", error.getDefaultMessage());

                    return errors;
                }).toList();

        return new ResponseEntity<>(buildErrorResponse(listErrors), HttpStatus.BAD_REQUEST);
    }

    public String getPropertyName(final FieldError error) {
        if(error.contains(ConstraintViolation.class)) {
            try {
                final ConstraintViolation<?> violation = error.unwrap(ConstraintViolation.class);
                final Field field;

                field = violation.getRootBeanClass().getDeclaredField(error.getField());
                final JsonProperty annotation = field.getAnnotation(JsonProperty.class);

                if (annotation != null && annotation.value() != null && !annotation.value().isEmpty()) {
                    return annotation.value();
                }
            } catch (Exception ex) {
            }
        }
        return error.getField();
    }

    private ErrorResponse buildErrorResponse(List<Map<String, String>> listErrors) {
        return ErrorResponse
                .builder()
                .status(HttpStatus.BAD_REQUEST.toString())
                .errors(listErrors)
                .build();
    }

}
