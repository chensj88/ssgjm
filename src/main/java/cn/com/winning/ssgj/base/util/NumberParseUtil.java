package cn.com.winning.ssgj.base.util;

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


}
