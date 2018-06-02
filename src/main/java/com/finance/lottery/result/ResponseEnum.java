package com.finance.lottery.result;

/**
 * @Auther: xuzhiqing
 * @Date: 2018/5/13 07:40
 * @Description:
 */

public enum ResponseEnum {
    SUCCESS(200, "OK"),
    ERROR(3000,"错误"),
    PARAM_ERROR(3001, "参数错误"),
    PARAM_NULL(3002,"参数为空"),
    DATA_ERROR(3003,"数据错误"),
    SERVER_ERROR(3004,"服务器错误"),
    USER_EXIST(3005,"用户名已存在"),
    LOGIN_FAILURE(3006,"用户名或密码错误"),
    USER_AVAILABLE(3007,"用户名可以使用"),
    EMAIL_NOT_BIND(3008,"该邮箱未绑定账户"),
    RESET_PASS_INVALID(3009,"密码重置链接已失效"),
    ;

    private int code;
    private String msg;

    private ResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ResponseEnum() {

    }

    private void setResponse(ResponseEnum responseEnum) {
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
