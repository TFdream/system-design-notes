package io.dreamstudio.architecture.user.enums;

/**
 * @author Ricky Fung
 */
public enum IdCardTypeEnum {
    NULL(0, "未实名(空)"),
    ID_CARD(1, "身份证"),
    PASSPORT(2, "护照"),
    ;
    private int value;
    private String desc;
    IdCardTypeEnum(int value, String desc) {
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
