package cn.com.winning.ssgj.base.id;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author chensj@winning.com.cn
 */
public abstract class AbstractTimeService implements TimeService {

    private long remoteTimeMillis;

    private long localTimeMillis;

    private int sychInterval = 60000;

    private TimeZone timeZone;

    private Locale locale;

    private int firstDayOfWeek = Calendar.MONDAY;

    public void setSychronizeInterval(int sychInterval) {
        this.sychInterval = sychInterval;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void setFirstDayOfWeek(int firstDayOfWeek) {
        this.firstDayOfWeek = firstDayOfWeek;
    }

    public final void reset() {
        this.remoteTimeMillis = remoteTimeMillis();
        this.localTimeMillis = localTimeMillis();
    }

    protected long localTimeMillis() {
        return System.currentTimeMillis();
    }

    protected long remoteTimeMillis() {
        return System.currentTimeMillis();
    }

    private long interval() {
        return System.currentTimeMillis() - this.localTimeMillis;
    }

    public final synchronized long currentTimeMillis() {
        long l = interval();
        if (l > this.sychInterval) {
            reset();
        }

        return this.remoteTimeMillis + interval();
    }

    public final boolean isCutoff(int calField, long oldTimeInMillis) {
        long l = currentTimeMillis();
        return isCutoff(calField, l, oldTimeInMillis);
    }

    public final boolean isCutoff(int calField, long newTimeInMillis, long oldTimeInMillis) {
        return getCalendarField(calField, newTimeInMillis) - getCalendarField(calField, oldTimeInMillis) > 0;

    }

    private Calendar getCalendar() {
        Calendar cal = null;
        if ((this.timeZone != null) && (this.locale != null)) {
            cal = Calendar.getInstance(this.timeZone, this.locale);
        } else if (this.timeZone != null) {
            cal = Calendar.getInstance(this.timeZone);
        } else if (this.locale != null) {
            cal = Calendar.getInstance(this.locale);
        } else {
            cal = Calendar.getInstance();
        }
        cal.setFirstDayOfWeek(this.firstDayOfWeek);
        return cal;
    }

    private int getCalendarField(int field, long timeInMillis) {
        Calendar cal = getCalendar();
        cal.setTimeInMillis(timeInMillis);
        return cal.get(field);
    }
}