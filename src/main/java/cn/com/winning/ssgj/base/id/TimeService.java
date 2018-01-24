package cn.com.winning.ssgj.base.id;

import java.util.Calendar;

/**
 * @author chensj@winning.com.cn
 */
public abstract interface TimeService {

    public static final int FIRST_DAY_OF_WEEK = Calendar.MONDAY;

    public static final int MINUTE = Calendar.MINUTE;

    public static final int HOUR = Calendar.HOUR;

    public static final int DAY = Calendar.DAY_OF_MONTH;

    public static final int WEEK = Calendar.WEEK_OF_MONTH;

    public static final int MONTH = Calendar.MONTH;

    public static final int YEAR = Calendar.YEAR;

    public abstract boolean isCutoff(int calField, long oldTimeInMillis);

    public abstract boolean isCutoff(int calField, long currentTimeMillis,
                                     long oldTimeInMillis);

    public abstract long currentTimeMillis();
}