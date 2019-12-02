package com.dapeng.demo.util.utils;

import java.math.BigDecimal;

/**
 * @Author: qinqc
 * @Date: 2019/1/8 15:00
 * @Desc: BigDecimal工具类
 */
public class BigDecimalUtils {

    /**
     * 加法
     * @param v1 被加数
     * @param v2 加数
     * @return
     */
    public static BigDecimal add(Object v1, Object v2){
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.add(b2);
    }

    /**
     * 加法
     * @param v1 被加数
     * @param v2 加数
     * @return 返回字符串
     */
    public static String addToStr(Object v1, Object v2){
        return add(v1, v2).toString();
    }

    /**
     * 减法
     * @param v1 被减数
     * @param v2 减数
     * @return
     */
    public static BigDecimal sub(Object v1, Object v2){
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.subtract(b2);
    }

    /**
     * 减法
     * @param v1 被减数
     * @param v2 减数
     * @return 返回字符串
     */
    public static String subToStr(Object v1, Object v2){
        return sub(v1, v2).toString();
    }

    /**
     * 乘法
     * @param v1 被乘数
     * @param v2 乘数
     * @return
     */
    public static BigDecimal mul(Object v1, Object v2){
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.multiply(b2);
    }

    /**
     * 乘法
     * @param v1 被乘数
     * @param v2 乘数
     * @return 返回字符串
     */
    public static String mulToStr(Object v1, Object v2){
        return mul(v1, v2).toString();
    }

    /**
     * 除法，四舍五入
     * @param v1 被除数
     * @param v2 除数
     * @param scale 保留小数点后几位
     * @return
     */
    public static BigDecimal div(Object v1, Object v2, int scale){
        BigDecimal b1 = new BigDecimal(String.valueOf(v1));
        BigDecimal b2 = new BigDecimal(String.valueOf(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 除法，四舍五入
     * @param v1 被除数
     * @param v2 除数
     * @param scale 保留小数点后几位
     * @return 返回字符串
     */
    public static String divToStr(Object v1, Object v2, int scale){
        return div(v1, v2, scale).toString();
    }

    /**
     * 求百分比
     * @param v1 分子
     * @param v2 分母
     * @return 百分比，不带百分号
     */
    public static String percent(Object v1, Object v2){
        return mul(div(v1, v2, 4), 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 求百分比
     * @param v1 分子
     * @param v2 分母
     * @return 百分比，带百分号"%"
     */
    public static String percentWithSymbol(Object v1, Object v2){
        return mul(div(v1, v2, 4), 100).setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "%";
    }

}
