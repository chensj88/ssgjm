package cn.com.winning.ssgj.base.aspect;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.com.winning.ssgj.base.helper.SSGJHelper;
import cn.com.winning.ssgj.base.util.StringUtil;
import cn.com.winning.ssgj.domain.SysLog;
import cn.com.winning.ssgj.domain.SysUserInfo;
import cn.com.winning.ssgj.domain.expand.LoginUserInfo;
import cn.com.winning.ssgj.service.SysLogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.winning.ssgj.base.annoation.ILog;
import cn.com.winning.ssgj.base.Constants;

@Aspect
@Component
public class LogAspect {

    @Autowired
    private SSGJHelper ssgjHelper;
    @Autowired
    private SysLogService sysLogServiceImpl;

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);


    @Pointcut("@annotation(cn.com.winning.ssgj.base.annoation.ILog)")
    public  void  serviceAspect(){}
    // Service层切点，这里如果需要配置多个切入点用“||”
    @Pointcut("execution(* cn.com.winning.ssgj.web.controller..*.*(..))")
    public void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    //@Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("==========执行controller前置通知===============");
        if (logger.isInfoEnabled()) {
            logger.info("before " + joinPoint);
        }
    }

    // 配置controller环绕通知,使用在方法aspect()上注册的切入点
    //@Around("controllerAspect()")
    public void around(JoinPoint joinPoint) {
        System.out.println("==========开始执行controller环绕通知===============");
        long start = System.currentTimeMillis();
        try {
            ((ProceedingJoinPoint) joinPoint).proceed();
            long end = System.currentTimeMillis();
            if (logger.isInfoEnabled()) {
                logger.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms!");
            }
            System.out.println("==========结束执行controller环绕通知===============");
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            if (logger.isInfoEnabled()) {
                logger.info("around " + joinPoint + "\tUse time : " + (end - start) + " ms with exception : "
                        + e.getMessage());
            }
        }
    }

    /**
     * 后置通知 用于拦截Controller层记录用户的操作
     * @param joinPoint 切点
     */
    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        String requestName = uri.substring(uri.lastIndexOf("/"),uri.lastIndexOf(".do"));
        if(requestName.equals("/login") || requestName.equals("/logout") || requestName.equals("/check")){
            return;
        }
        // 请求的IP
        String ip = request.getRemoteAddr();
        //访问主机名称
        String remoteHost = request.getRemoteHost();
        //登录人员
        SysUserInfo operator = (SysUserInfo) SecurityUtils.getSubject().getPrincipal();
        SysUserInfo sessionUser = (SysUserInfo) session.getAttribute(Constants.USER_INFO);
        operator = operator != null ? operator :sessionUser;
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            Object[] arguments = joinPoint.getArgs();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            /*String operationType = "";
            String operationName = "";
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    Class[] clazzs = method.getParameterTypes();
                    if (clazzs.length == arguments.length) {
                        operationType = method.getAnnotation(ILog.class).operationType();
                        operationName = method.getAnnotation(ILog.class).operationName();
                        break;
                    }
                }
            }*/

            // *========控制台输出=========*//
         logger.info("=====controller后置通知开始=====");
            logger.info("请求方法:"
                    + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
            /*logger.info("方法描述:" + operationName);*/
            logger.info("请求人:"  +operator.getYhmc());
            logger.info("请求IP:" + ip);
            String content = "[classs]{ "+targetName +"} [method]{"+ methodName  + "} [user] {" + operator.getYhmc() +"}";
            logger.info("访问内容："+content);
            // *========数据库日志=========*//
            SysLog log = new SysLog();
            log.setId(ssgjHelper.createLogId());
            if(!StringUtil.isEmptyOrNull(operator.getMap().get("C_ID").toString())){
                log.setCId(Long.valueOf(operator.getMap().get("C_ID").toString()));
            }
            if(!StringUtil.isEmptyOrNull(operator.getMap().get("PM_ID").toString())){
                log.setPmId(Long.valueOf(operator.getMap().get("PM_ID").toString()));
            }
            if(!StringUtil.isEmptyOrNull(operator.getMap().get("CU_ID").toString())){
                log.setSerialNo(operator.getMap().get("CU_ID").toString());
            }
            //暂时放空
            log.setOperator(operator.getId());
            log.setOperatorTime(new Timestamp(new Date().getTime()));
            log.setContent(content);
            log.setClientIp(ip);
            log.setClientName(remoteHost);
            //暂时放空
            log.setClientMac(null);
            sysLogServiceImpl.createSysLog(log);
          /*  logger.info("=====controller后置通知结束=====");*/
        } catch (Exception e) {
            // 记录本地异常日志
            /*logger.error("==后置通知异常==");*/
            logger.error("异常信息:{}", e.getMessage());
        }
    }

    // 配置后置返回通知,使用在方法aspect()上注册的切入点
    //@AfterReturning("controllerAspect()")
    public void afterReturn(JoinPoint joinPoint) {

    }

    /**
     * 异常通知 用于拦截记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    //@AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {

    }
}
