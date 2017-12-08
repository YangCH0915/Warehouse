package com.yunji.morn.exception;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理，并写到日志文件
 */
public class GlobalExceptionHandler implements HandlerExceptionResolver{

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //handler就是处理器适配器要执行的Handler对象(只有method)
        //解析出异常类型。
        //如果该 异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示。
        CustomException customException=null;
        if(e instanceof CustomException){
            customException=(CustomException)e;
        }else{
            //如果该 异常类型不是系统 自定义的异常，构造一个自定义的异常类型（信息为“未知错误”）。
            customException=new CustomException("未知错误");
        }
        //错误信息
        String message=customException.getMessage();
        logger.error(message);
        ModelAndView modelAndView=new ModelAndView();
        //将错误信息传到页面
        modelAndView.addObject("message",message);
        //指向到错误界面
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
