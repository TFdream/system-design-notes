package io.dreamstudio.architecture.user.enums;

/**
 * @author Ricky Fung
 */
public enum IdVerifiedEnum {
    NEW(1, "未实名"),
    FAILURE(2, "实名认证失败"),
    SUCCESS(3, "已实名"),
    ;
    private int value;
    private String desc;
    IdVerifiedEnum(int value, String desc) {
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
