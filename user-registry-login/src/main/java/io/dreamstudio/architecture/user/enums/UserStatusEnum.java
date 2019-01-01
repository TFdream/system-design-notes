package io.dreamstudio.architecture.user.enums;

/**
 * @author Ricky Fung
 */
public enum UserStatusEnum {
    NORMAL(1, "正常"),
    FROZEN(2, "已冻结"),
    ;
    private int value;
    private String desc;
    UserStatusEnum(int value, String desc) {
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
