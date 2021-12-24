package org.example.crm.handle;

import org.example.crm.exception.LoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public String doLoginException(Exception e){
        e.printStackTrace();
        return e.getMessage();
    }
}
