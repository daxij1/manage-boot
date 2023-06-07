package com.daxij1.manageboot.framework.exeception;

import com.daxij1.manageboot.framework.pojo.ResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：daxij1
 * @date ：Created in 2023/5/24 17:23
 * @description：CommonExceptionHandler
 */
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public ResponseVO<Object> handleServiceException(ServiceException e) {
        return ResponseVO.error(e.getErrorCode(), e.getMsg());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseVO<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return ResponseVO.error(errorMessages.get(errorMessages.size() - 1));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseVO<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errorMessages = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return ResponseVO.error(errorMessages.get(errorMessages.size() - 1));
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseVO<Object> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseVO.error();
    }

}
