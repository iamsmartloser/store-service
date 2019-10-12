package com.liyan.store.exception;

import com.liyan.store.enums.ResultEnum;

public class ReqException extends RuntimeException {

    private Integer code;

    public ReqException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
