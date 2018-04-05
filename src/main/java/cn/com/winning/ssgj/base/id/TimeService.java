package cn.com.winning.ssgj.base.id;

import java.util.Calendar;

/**
 * @author chensj@winning.com.cn
 */
public interface TimeService {

    int FIRST_DAY_OF_WEEK = Calendar.MONDAY;

    int MINUTE = Calendar.MINUTE;

    int HOUR = Calendar.HOUR;

    int DAY = Calendar.DAY_OF_MONTH;

    int WEEK = Calendar.WEEK_OF_MONTH;

    int MONTH = Calendar.MONTH;

    int YEAR = Calendar.YEAR;

    boolean isCutoff(int calField, long oldTimeInMillis);

    boolean isCutoff(int calField, long currentTimeMillis,
                     long oldTimeInMillis);

    long currentTimeMillis();
}