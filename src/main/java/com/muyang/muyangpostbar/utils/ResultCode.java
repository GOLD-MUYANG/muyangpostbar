package com.muyang.muyangpostbar.utils;

import lombok.Data;

/**
 * @author 李传旭
 * @version 1.0
 * @since 2023-03-12 15:50:44
 */
public enum ResultCode {
    SUCCESS(0, "请求成功"),
    FAILED(1, "操作失败"),
    TOKEN_FAILED(2, "token失效"),
    NONE(9, "无");

    private int code;
    private String msg;

    private ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
