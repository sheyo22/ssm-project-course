package org.example.crm.handle;

import org.example.crm.exception.LoginException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public Map doLoginException(Exception e){
        e.printStackTrace();
        Map map = new HashMap();
        map.put("msg",e.getMessage());
        return map;
    }
    @ExceptionHandler
    public ModelAndView doException(Exception e){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg",e.getMessage());
        e.printStackTrace();
        mv.setViewName("error.jsp");
        return mv;
    }
}
