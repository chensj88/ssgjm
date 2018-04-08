package cn.com.winning.ssgj.base.util;

import java.text.NumberFormat;

public class NumberParseUtil {

    /**
     * 空字符串和null时返回null
     *
     * @param num
     * @return
     */
    public static Integer parseInt(String num) {
        if (StringUtil.isEmptyOrNull(num)) {
            return null;
        } else {
            return Integer.parseInt(num);
        }
    }

    /**
     * 空字符串和null时返回null
     *
     * @param num
     * @return
     */
    public static Long parseLong(String num) {
        if (StringUtil.isEmptyOrNull(num)) {
            return null;
        } else {
            return Long.parseLong(num);
        }
    }


    /**
     * 计算百分率，精确到小数点后两位
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String parsePercent(Integer num1, Integer num2) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float) num1 / (float) num2 * 100);
        return result + "%";
    }

}
