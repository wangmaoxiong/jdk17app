package com.wmx.jdk17app.pojo;

/**
 * 页面返回数据状态枚举值
 *
 * @author wangmaoxiong
 * @version 1.0
 * @date 2020/9/30 16:30
 * <p>
 * 1000～1999 区间表示参数错误
 * 2000～2999 区间表示用户错误
 * 3000～3999 区间表示接口异常
 */
public enum ResultCode {

    SUCCESS(200, "操作成功"),
    SUCCESS_LOAD(200, "查询成功"),
    EXP_SUCCESS(200,"导出成功"),
    IMP_SUCCESS(200,"导入成功"),

    FAIL(500, "操作失败"),

    /**
     * 参数错误：1000-1999
     */
    PARAM_IS_FAIL(1000, "参数校验未通过"),

    PARAM_IS_INVALID(1001, "参数无效"),

    PARAM_IS_BLANK(1002, "参数为空"),

    PARAM_TYP_EBIND_ERROR(1003, "参数类型错误"),

    PARAM_NOT_C0MPLETE(1004, "参数缺失"),
    /**
     * 用户错误：2000-2999
     */
    USER_NOT_L0GGED_IN(2001, "用户未登录，访问的路径需要验证，请登录"),

    USER_L0GIN_ERROR(2002, "账号不存在或密码错误"),

    USER_ACCOUNT_F0_RBIDDEN(2003, "账号已被禁用"),

    USER_VERIFI_CODE_ERROR(2004, "验证码错误");

    private Integer code;
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }


}


