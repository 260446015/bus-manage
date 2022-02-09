package com.example.busmanage.config;

import com.example.busmanage.common.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class RestErrControllerAdvice {

    @ExceptionHandler(value = Exception.class)//指定拦截的异常
    public void errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        ApiResult error = ApiResult.error(e.getMessage());
        if(e instanceof MethodArgumentNotValidException){
            MethodArgumentNotValidException  methodArgumentNotValidException = (MethodArgumentNotValidException ) e;
            BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
            error = handleRequest(bindingResult);
        }
        log.error("异常出现，异常原因为:{}", e.getMessage());
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.getOutputStream().write(error.toString().getBytes(StandardCharsets.UTF_8));
    }

    protected ApiResult handleRequest(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> {
                //日志打印不符合校验的字段名和错误提示
                log.error("error field is : {} ,message is : {}", fieldError.getField(), fieldError.getDefaultMessage());
            });
            for (int i = 0; i < fieldErrors.size(); i++) {
                //控制台打印不符合校验的字段名和错误提示
                log.error("error field is :" + fieldErrors.get(i).getField() + ",message is :" + fieldErrors.get(i).getDefaultMessage());
                return new ApiResult().setCode(-1).setMessage(fieldErrors.get(i).getDefaultMessage());
            }
        }
        return null;
    }
}
