package com.joofont.exception.enums;

/**
 * @author cui jun on 2018/10/10.
 * @version 1.0
 */
public enum ExceptionEnum {

    // 通用导出异常
    INVALID_PARAM("20000000", "参数异常"),
    ;

    private String code, msg;



    ExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ExceptionEnum msg(String msg) {
        this.msg = msg;
        return this;
    }

}
