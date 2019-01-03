package io.dreamstudio.architecture.user.enums;

/**
 * @author Ricky Fung
 */
public enum AuthCodeType {
    REGISTRY(1, "注册验证码"),
    LOGIN(2, "登录验证码"),
    ;
    private int value;
    private String desc;
    AuthCodeType(int value, String desc) {
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
