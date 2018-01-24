package cn.com.winning.ssgj.base.id;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * ID格式化工厂
 *
 * @author chensj@winning.com.cn
 */
public class IdFormatFactory {

    private String format;

    private IdFormatter[] formatters;

    public static IdFormatFactory getInstance(String format) {
        return new IdFormatFactory(format);
    }

    private IdFormatFactory(String format) {
        this.format = format;
        init();
    }

    public String generate(IdCreator<?> idCreator, long id, Date date) {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < this.formatters.length; i++) {
            IdFormatter formatter = this.formatters[i];
            if ((formatter instanceof IdCreatorFormatter))
                formatter.generate(idCreator, sb);
            else if ((formatter instanceof NumberFormmter))
                formatter.generate(new Long(id), sb);
            else if ((formatter instanceof DateFormatter))
                formatter.generate(date, sb);
            else {
                formatter.generate(null, sb);
            }
        }

        return sb.toString();
    }

    protected void init() {
        char[] arrayOfChar = this.format.toCharArray();
        int i = 0;
        StringBuffer sb = new StringBuffer();
        ArrayList<IdFormatter> list = new ArrayList<IdFormatter>();

        for (int j = 0; j < arrayOfChar.length; j++) {
            if ('{' == arrayOfChar[j]) {
                if (i != 0) {
                    throw new IllegalArgumentException("invalid pattern: " + this.format);
                }

                i = 1;

                if (sb.length() > 0) {
                    list.add(new SimpleStringFormatter(sb.toString()));
                    sb.setLength(0);
                }
            } else if ('}' == arrayOfChar[j]) {
                if (i == 0) {
                    throw new IllegalArgumentException("invalid pattern: " + this.format);
                }

                i = 0;

                if (sb.length() > 0) {
                    list.add(getFormatter(sb.toString()));
                    sb.setLength(0);
                }
            } else {
                sb.append(arrayOfChar[j]);
            }
        }
        if (i != 0)
            throw new IllegalArgumentException("invalid pattern: " + this.format);

        if (sb.length() > 0) {
            list.add(new SimpleStringFormatter(sb.toString()));
            sb.setLength(0);
        }

        this.formatters = list.toArray(new IdFormatter[list.size()]);
    }

    protected IdFormatter getFormatter(String format) {
        int i = format.charAt(0);
        switch (i) {
            case '#':
                return new NumberFormmter(format.length());
            case '$':
                return new IdCreatorFormatter(format.substring(1));
            default:
                return new DateFormatter(format);
        }
    }

    abstract interface IdFormatter {
        public abstract void generate(Object object, StringBuffer result);
    }


    /**
     * 日期格式化器
     *
     * @author zhouw
     */
    class DateFormatter implements IdFormatter {

        private ThreadLocal<DateFormat> dateFormat = null;

        public void generate(Object date, StringBuffer result) {
            if ((date != null) && (!(date instanceof Date)))
                throw new IllegalArgumentException("input is not a date: "
                        + date);
            Date localDate = (Date) date;
            if (localDate == null) {
                localDate = new Date();
            }

            result.append(this.dateFormat.get().format(localDate));
        }

        public DateFormatter(String format) {
            final String str = format;
            this.dateFormat = new ThreadLocal<DateFormat>() {
                @Override
                public DateFormat get() {
                    return new SimpleDateFormat(str);
                }
            };
        }
    }


    /**
     * 类型格式化器
     *
     * @author zhouw
     */
    class IdCreatorFormatter implements IdFormatter {
        private String field;

        public IdCreatorFormatter(String field) {
            this.field = (Character.toUpperCase(field.charAt(0)) + field.substring(1));
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        public void generate(Object idCreator, StringBuffer result) {
            if ((idCreator == null) || (!(idCreator instanceof IdCreator))) {
                throw new IllegalArgumentException("input is not a id factory: " + idCreator);
            }
            Class c = idCreator.getClass();
            try {
                Method m = c.getMethod("get" + this.field, c);
                Object invokeValue = m.invoke(idCreator, m);
                if (invokeValue == null) {
                    throw new IllegalArgumentException("field '" + this.field + "'s value is null: " + idCreator);
                }
                result.append(invokeValue);
            } catch (Exception e) {
                throw new IllegalArgumentException("cannot get field '" + this.field + "'s value: " + idCreator);
            }
        }
    }

    /**
     * 递增数字格式化器
     */
    class NumberFormmter implements IdFormatter {
        /**
         * 序号长度
         */
        private int length;

        private long pow;

        public void generate(Object number, StringBuffer result) {
            if ((number == null) || (!(number instanceof Number))) {
                throw new IllegalArgumentException("input is not a number: " + number);
            }

            String str = new Long(this.pow + ((Number) number).longValue()).toString();
            result.append(str.substring(str.length() - this.length));
        }

        public NumberFormmter(int length) {
            this.length = length;
            this.pow = (long) Math.pow(10.0D, this.length);
        }
    }


    /**
     * 简单字符串格式化器
     *
     * @author zhouw
     */
    class SimpleStringFormatter implements IdFormatter {

        private String str;

        public void generate(Object object, StringBuffer result) {
            if (this.str == null) {
                throw new IllegalArgumentException("input is null.");
            }

            result.append(this.str);
        }

        SimpleStringFormatter(String str) {
            this.str = str;
        }
    }
}