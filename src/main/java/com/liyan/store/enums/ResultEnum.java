package com.liyan.store.enums;


public enum ResultEnum {
    SUCCESS(0, "成功"),
    UNKOWN_ERROR(-1, "未知错误"),
    PARAMETER_ERROR(-2,"请求参数错误"),
    PASSWORD_ERROR(-100,"密码错误"),
    PASSWORD_EMPTY(-101,"密码为空"),
    USER_NAME_EXISTENT(-102,"此用户名已存在"),
    USER_NAME_NON_EXISTENT(-103,"此用户名不存在"),
    ACCESS_TOKEN_INVALID(-104,"accessToken失效"),
    NO_PERMITION(-105,"没有权限"),
    DATA_BASE_ERROR(-106,"数据库字段重复或有外键关联等"),
    PRODUCT_NOT_FIND(-107,"商品不存在");
    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
