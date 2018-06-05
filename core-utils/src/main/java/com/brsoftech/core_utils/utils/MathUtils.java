package com.brsoftech.core_utils.utils;

import java.math.BigDecimal;

/**
 * <p><b>Author: Kartik Sharma @ B.R. Softech<b></p>
 * <p>Date: 27/6/16</p>
 * <p>Time: 12:59 PM</p>
 * <p>Project: Planbeep</p>
 */
public class MathUtils {
    public static double round(double x, int scale) {
        return round(x, scale, BigDecimal.ROUND_HALF_UP);
    }

    public static double round(double x, int scale, int roundingMethod) {
        try {
            return (new BigDecimal
                    (Double.toString(x))
                    .setScale(scale, roundingMethod))
                    .doubleValue();
        } catch (NumberFormatException ex) {
            if (Double.isInfinite(x)) {
                return x;
            } else {
                return Double.NaN;
            }
        }
    }
}
