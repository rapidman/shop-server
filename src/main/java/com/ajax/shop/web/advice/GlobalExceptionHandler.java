package com.ajax.shop.web.advice;

import com.ajax.shop.data.ValidationErrorResponse;
import com.ajax.shop.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:t.saidov@fasten.com">Timur Saidov</a>.
 * @since 31.08.18
 */
@ControllerAdvice("com.ajax.shop.web.controller")
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ValidationErrorResponse handleValidationException(ValidationException e, HttpServletRequest request) {
        return new ValidationErrorResponse(e.getClientMessage());
    }
}
