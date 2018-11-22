package com.joofont.annotation;

import com.joofont.enums.PlatformEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * desction: 如果程序中重置了限定时间，记得重置错误描述
 *
 * @author cui jun on 2018/10/9.
 * @version 1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OperationRecord {

    /**
     * 错误描述
     *
     * @return
     */
    String desc() default "5分钟内只能导出一次";

    /**
     * 限制时间
     *
     * @return
     */
    int minute() default 5;

    /**
     * 业务类型
     *
     * @return
     */
    PlatformEnum platform();

}
