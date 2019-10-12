package com.liyan.store.aspect;

import com.liyan.store.domain.User;
import com.liyan.store.enums.ResultEnum;
import com.liyan.store.exception.ReqException;
import com.liyan.store.repository.UserRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAspect {
    @Autowired
    private UserRepository userRepository;
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
//"execution(public * com.liyan.store.controller.UserController.*(..))"
//    @Pointcut("execution(public * com.liyan.store.controller..*.*(..))")
//    public void log() {
//    }
//
//    @Before("log()")
//    public void doBefore(JoinPoint joinPoint) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//        //url
//        logger.info("url={}", request.getRequestURL());
//
//        //method
//        logger.info("method={}", request.getMethod());
//
//        //ip
//        logger.info("ip={}", request.getRemoteAddr());
//
//        //类方法
////        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
////
////        Object o[]=joinPoint.getArgs();
////        //参数
////        logger.info("args={}", joinPoint.getArgs());
////        logger.info("args length={}", o.length);
////        logger.info("args[0]={}",o[0].toString() );
//
//
//    }
//
//    @After("log()")
//    public void doAfter() {
//
//    }
//
//    @AfterReturning(returning = "object", pointcut = "log()")
//    public void doAfterReturning(Object object) {
//        logger.info("response={}",object.toString() );
//    }
//
//
    @Pointcut("@annotation(com.liyan.store.aspect.UserAccess)")
    public void access() {
    }

    /**
     * accessToken验证
     */
    @Before("access()")
    public void beforeAccess(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //url
        logger.info("url={}", request.getRequestURL());

        //method
        logger.info("method={}", request.getMethod());

        //ip
        logger.info("ip={}", request.getRemoteAddr());

        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        Object o[]=joinPoint.getArgs();
        JSONObject jsonObject=null;
        try {
            jsonObject=new JSONObject(o[0].toString());
            String accessToken=jsonObject.getString("accessToken");
            logger.info("accessToken:"+accessToken);
            if (null==userRepository.findByAccessToken(accessToken)){
                throw new ReqException(ResultEnum.ACCESS_TOKEN_INVALID);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Pointcut("@annotation(com.liyan.store.aspect.ManagerAccess)")
    public void managerAccess() {
    }

    /**
     * accessToken验证
     */
    @Before("managerAccess()")
    public void beforeManagerAccess(){
        logger.info("Manageraccess:");
    }

}
