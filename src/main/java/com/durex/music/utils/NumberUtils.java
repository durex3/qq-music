package com.durex.music.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author liugelong
 * @date 2022/8/18 14:21
 */
public class NumberUtils {

    private static final String MILLION_UNIT = "万";
    private static final String BILLION_UNIT = "亿";
    private static final BigDecimal ONE_HUNDRED_HUNDRED = new BigDecimal(10000);
    private static final BigDecimal ONE_HUNDRED_MILLION = new BigDecimal(100000000);
    private static final BigDecimal TEN_THOUSAND = new BigDecimal(10000);

    private NumberUtils() {
    }


    /**
     * <h2>将数字转换成以万为单位或者以亿为单位</h2>
     *
     * @param number 数字
     * @return String
     */
    public static String conversion(BigDecimal number) {
        if (number == null) {
            return BigDecimal.ZERO.toPlainString();
        }
        if (number.abs().compareTo(ONE_HUNDRED_HUNDRED) < 0) {
            // 如果小于1万
            return number.stripTrailingZeros().toPlainString();
        }
        if (number.abs().compareTo(ONE_HUNDRED_MILLION) < 0) {
            // 如果大于1万小于1亿
            return number.divide(TEN_THOUSAND, 1, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString() + MILLION_UNIT;
        }
        return number.divide(ONE_HUNDRED_MILLION, 1, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString() + BILLION_UNIT;
    }
}
