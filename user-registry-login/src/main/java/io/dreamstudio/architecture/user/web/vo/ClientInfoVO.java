package io.dreamstudio.architecture.user.web.vo;

/**
 * Android/iOS 手机设备信息
 * @author Ricky Fung
 */
public class ClientInfoVO {
    /**
     * IMEI
     */
    private String imei;

    /**
     * 厂商名, android.os.Build.MANUFACTURER
     */
    private String manufacturer;

    /**
     * 产品名, android.os.Build.PRODUCT
     */
    private String product;

    /**
     * 手机品牌, android.os.Build.BRAND;
     */
    private String brand;

    /**
     * 手机型号, android.os.Build.MODEL;
     */
    private String model;

    /**
     * 手机主板, android.os.Build.BOARD;
     */
    private String board;

    /**
     * 设备名, android.os.Build.DEVICE;
     */
    private String device;

    /**
     * android.os.Build.FINGERPRINT;
     */
    private String fingerprint;

    /**
     * android.os.Build.VERSION.SDK_INT
     */
    private String versionCode;

    /**
     * android.os.Build.VERSION.RELEASE
     */
    private String versionName;
}
