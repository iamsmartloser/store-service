package com.liyan.store.handle;

import com.liyan.store.domain.Result;
import com.liyan.store.enums.ResultEnum;
import com.liyan.store.exception.ReqException;
import com.liyan.store.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ReqExceptionHandle {
    private final static Logger logger = LoggerFactory.getLogger(ReqExceptionHandle.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {

        if (e instanceof ReqException) {
            ReqException reqException = (ReqException) e;
            e.printStackTrace();
            return ResultUtil.error(reqException.getCode(), reqException.getMessage());
        }if (e instanceof MethodArgumentNotValidException){
            e.printStackTrace();
            MethodArgumentNotValidException methodArgumentNotValidException=(MethodArgumentNotValidException)e;
            return ResultUtil.error(-2,methodArgumentNotValidException.getBindingResult()
                    .getFieldError().getDefaultMessage());
        } else {
            logger.error("【系统异常】{未知错误}", e);
            e.printStackTrace();
            return ResultUtil.error(-1, "未知错误");
        }
    }

    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public Result requestTypeMismatch(TypeMismatchException ex){
        ex.printStackTrace();
        return ResultUtil.error(-400, "参数类型不匹配,参数" + ex.getPropertyName() + "类型应该为" + ex.getRequiredType());
    }
    //缺少参数异常
//getParameterName() 缺少的参数名称
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public Result requestMissingServletRequest(MissingServletRequestParameterException ex){
        ex.printStackTrace();
        return ResultUtil.error(-400, "缺少必要参数,参数名称为" + ex.getParameterName());
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseBody
    public Result dataIntegrityViolationException(DataIntegrityViolationException ex){
        ex.printStackTrace();
        return ResultUtil.error(ResultEnum.DATA_BASE_ERROR);
    }


}
