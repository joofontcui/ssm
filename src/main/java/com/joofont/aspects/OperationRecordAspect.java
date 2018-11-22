package com.joofont.aspects;

import com.joofont.annotation.OperationRecord;
import com.joofont.enums.PlatformEnum;
import com.joofont.exception.enums.ExceptionEnum;
import com.joofont.service.OperationRecordService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author cui jun on 2018/10/9.
 * @version 1.0
 */
@Aspect
@Component
public class OperationRecordAspect {

    private Logger logger = LogManager.getLogger(OperationRecordAspect.class);

    @Autowired
    private OperationRecordService operationRecordService;

    @Pointcut("@annotation(com.joofont.annotation.OperationRecord)")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void operationRecord(JoinPoint joinPoint) {
        logger.error("[操作日志]");

        /**
         * 注解属性
         */
        OperationRecord oper = getSourceMethod(joinPoint).getAnnotation(OperationRecord.class);
        PlatformEnum platformEnum = oper.platform();

        /**
         * 获取请求业务方法的入参
         */
        Object[] params = joinPoint.getArgs();
        Integer userId = (Integer) params[0];

        /**
         * 参数校验
         */
        if (joinPoint.getArgs() == null || joinPoint.getArgs().length == 0) {
            try {
                throw new Exception(ExceptionEnum.INVALID_PARAM.getMsg());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * 操作记录
         */
        com.joofont.entity.OperationRecord record = new com.joofont.entity.OperationRecord();
        record.setUserId(userId);
        record.setPlatform(platformEnum.getName());
        record.setCreateTime(new Date());
        operationRecordService.addOperationRecord(record);
    }

    private Method getSourceMethod(JoinPoint jp) {
        Method proxyMethod = ((MethodSignature) jp.getSignature()).getMethod();
        try {
            return jp.getTarget().getClass().getMethod(proxyMethod.getName(), proxyMethod.getParameterTypes());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

}