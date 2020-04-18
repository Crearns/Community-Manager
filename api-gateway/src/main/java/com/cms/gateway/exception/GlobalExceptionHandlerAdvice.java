package com.cms.gateway.exception;

import com.cms.common.common.ResponseCode;
import com.cms.common.common.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandlerAdvice.class);

    /**
     *  校验错误拦截处理
     *
     * @param exception 错误信息集合
     * @return 错误信息
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ServerResponse validationBodyException(MethodArgumentNotValidException exception){

        BindingResult result = exception.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(p ->{
                FieldError fieldError = (FieldError) p;
                logger.error("Data check failure : object{"+fieldError.getObjectName()+"},field{"+fieldError.getField()+
                             "},errorMessage{"+fieldError.getDefaultMessage()+"}");
            });
        }
        return ServerResponse.createFailureResponse(ResponseCode.FAILURE);
    }

    /**
     * 捕获自定义异常
     * @param exception
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ServerResponse validationBodyException(BusinessException exception){
        System.out.println("exception.getMessage() = " + exception.getMessage());
        return ServerResponse.createFailureResponse(exception.getMessage());
    }
    

}