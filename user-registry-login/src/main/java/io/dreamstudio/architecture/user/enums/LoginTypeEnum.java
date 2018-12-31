package io.dreamstudio.architecture.user.enums;

/**
 * @author Ricky Fung
 */
public enum LoginTypeEnum {
    PASSWORD(1, "用户名密码登录"),
    SMS_CODE(2, "短信验证码登录"),
    ;
    private int value;
    private String desc;

    LoginTypeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}
