package io.dreamstudio.common.util;

import java.math.BigDecimal;

/**
 * @author Ricky Fung
 */
public abstract class DecimalUtils {
    private static final int DEFAULT_SCALE = 5;
    //------------
    public static BigDecimal valueOf(double num) {
        return new BigDecimal(Double.toString(num));
    }

    public static BigDecimal valueOf(float num) {
        return new BigDecimal(Float.toString(num));
    }

    public static BigDecimal valueOf(int num) {
        return new BigDecimal(Integer.toString(num));
    }

    public static BigDecimal valueOf(long num) {
        return new BigDecimal(Long.toString(num));
    }

    //-------------
    public static String format(BigDecimal bd, int scale) {
        return bd.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static BigDecimal setScale(BigDecimal bd, int scale) {
        return bd.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal setScale(BigDecimal bd, int scale, int roundingMode) {
        return bd.setScale(scale, roundingMode);
    }

    //-----int
    public static BigDecimal div(int v1, int v2) {
        BigDecimal b1 = new BigDecimal(Integer.toString(v1));
        BigDecimal b2 = new BigDecimal(Integer.toString(v2));
        return b1.divide(b2, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP);//四舍五入,保留两位小数
    }

    public static BigDecimal div(int v1, int v2, int scale) {
        BigDecimal b1 = new BigDecimal(Integer.toString(v1));
        BigDecimal b2 = new BigDecimal(Integer.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);//四舍五入,保留两位小数
    }
    //-----long
    public static BigDecimal div(long v1, long v2) {
        BigDecimal b1 = new BigDecimal(Long.toString(v1));
        BigDecimal b2 = new BigDecimal(Long.toString(v2));
        return b1.divide(b2, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP);//四舍五入,保留两位小数
    }

    public static BigDecimal div(long v1, long v2, int scale) {
        BigDecimal b1 = new BigDecimal(Long.toString(v1));
        BigDecimal b2 = new BigDecimal(Long.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);//四舍五入,保留两位小数
    }

    //-----double
    public static BigDecimal add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    public static BigDecimal sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }

    public static BigDecimal mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

    public static BigDecimal div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP);//四舍五入,保留两位小数
    }

    //--------
    public static BigDecimal add(BigDecimal b1, BigDecimal b2) {
        return b1.add(b2);
    }

    public static BigDecimal sub(BigDecimal b1, BigDecimal b2) {
        return b1.subtract(b2);
    }

    public static BigDecimal mul(BigDecimal b1, BigDecimal b2) {
        return b1.multiply(b2);
    }

    public static BigDecimal div(BigDecimal b1, BigDecimal b2) {
        return b1.divide(b2, DEFAULT_SCALE, BigDecimal.ROUND_HALF_UP);//四舍五入,保留两位小数
    }

    public static BigDecimal div(BigDecimal b1, BigDecimal b2, int scale) {
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);//四舍五入,保留两位小数
    }
}
