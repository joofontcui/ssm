package com.joofont.enums;

/**
 * @author cui jun on 2018/10/11.
 * @version 1.0
 */
public enum PlatformEnum {

    BOOK(1, "书籍相关"),
    ;

    private int code;

    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    PlatformEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean isMaster(Integer code) {
        return code != null && code.intValue() == BOOK.getCode();
    }

    public static PlatformEnum get(Integer code) {
        if (code == null) {
            return null;
        }
        PlatformEnum[] values = PlatformEnum.values();
        for (PlatformEnum value : values) {
            if (value.code == code.intValue()) {
                return value;
            }
        }
        return null;
    }
}
