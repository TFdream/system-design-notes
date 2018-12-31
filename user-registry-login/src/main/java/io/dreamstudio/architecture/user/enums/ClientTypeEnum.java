package io.dreamstudio.architecture.user.enums;

/**
 * @author Ricky Fung
 */
public enum ClientTypeEnum {
    PC(1, "网站"),
    APP(2, "App端"),
    H5(3, "H5"),
    ;
    private int value;
    private String desc;
    ClientTypeEnum(int value, String desc) {
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
