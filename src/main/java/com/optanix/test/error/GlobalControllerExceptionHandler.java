package com.optanix.test.error;

import com.optanix.test.model.ApiError;
import com.optanix.test.model.ApplicationException;
import com.optanix.test.web.controller.ApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jignesh
 * The purpose of this class is to selectively replace default Spring Boot error responses with custom error responses
 * in cases where the Spring Boot's default error response if not suitable.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(ApiController.class);

    /**
     * This method handles MethodArgumentNotValidException specifically
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request) {
        logger.error("MethodArgumentNotValidException occurred. Message: {}, Exception: {} ", exception.getMessage(), exception);
        HttpStatus status = HttpStatus.BAD_REQUEST;

        FieldError error = exception.getBindingResult().getFieldError();
        String message;
        if (error != null) {
            message = error.getField() + " - " + error.getDefaultMessage();
        } else {
            message = exception.getMessage();
        }

        return ResponseEntity.status(status).body(createErrorAttributes(status, "INVALID_REQUEST_DATA", null, message, request));
    }


    /**
     * This method handles ApplicationException specifically
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiError> handleApplicationException(ApplicationException exception, WebRequest request) {
        logger.error("ApplicationException occurred. Message: {}, Exception: {} ", exception.getMessage(), exception);
        HttpStatus status = HttpStatus.valueOf(exception.getStatus());

        Throwable rootCause = exception;
        while (rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
        }
        String message = rootCause.getMessage();

        return ResponseEntity.status(status).body(createErrorAttributes(status, exception.getCode(), exception.getParameters(), message, request));
    }

    /**
     * This method handles any generic exception specifically
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception exception, WebRequest request) {
        logger.error("Exception occurred. Message: {}, Exception: {} ", exception.getMessage(), exception);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        Throwable rootCause = exception;
        while (rootCause.getCause() != null) {
            rootCause = rootCause.getCause();
        }
        String message = rootCause.getMessage();

        return ResponseEntity.status(status).body(createErrorAttributes(status, null, null, message, request));
    }

    /**
     * This method wraps up error details in a API wide standard format represented by ApiError class
     *
     * @param status
     * @param code
     * @param parameters
     * @param message
     * @param request
     * @return
     */
    protected ApiError createErrorAttributes(HttpStatus status, String code, Map<String, String> parameters, String message, WebRequest request) {
        if (StringUtils.isEmpty(code))
            code = "GENERIC_EXCEPTION";
        if (parameters == null)
            parameters = new HashMap<>();
        return new ApiError(new Date(), status.value(), HttpStatus.valueOf(status.value()).getReasonPhrase(), message, code, ((ServletWebRequest) request).getRequest().getRequestURI(), parameters);
    }
}
