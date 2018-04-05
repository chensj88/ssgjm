package cn.com.winning.ssgj.base.id;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chensj@winning.com.cn
 */
public abstract class AbstractSequenceFactory implements IdCreator<Object> {

    protected final static Logger logger = LoggerFactory.getLogger(AbstractSequenceFactory.class);

    /**
     * 主键类型
     */
    protected String type;

    /**
     * 主键格式化器
     */
    private IdFormatFactory formatter;

    public void setType(String type) {
        this.type = type;
    }

    public void setFormat(String format) {
        this.formatter = IdFormatFactory.getInstance(format);
    }

    public String getType() {
        return this.type;
    }

    public Object create() {
        Object[] arrayOfObject = create(1);
        return arrayOfObject[0];
    }

    public Object[] create(int count) {
        if (this.type == null) {
            throw new IllegalArgumentException("you must setType(String) in SequenceFactory.");
        }

        long[] ids = internalGenerate(count);

        Object[] result;

        if (this.formatter == null) {
            result = new Long[count];
            for (int i = 0; i < count; i++) {
                result[i] = new Long(ids[i]);
            }
        } else {
            result = new String[count];
            for (int i = 0; i < count; i++) {
                result[i] = generateId(ids[i], ids[i + 1]);
            }
        }

        return result;
    }

    /**
     * generateId 格式化主键
     *
     * @param id
     * @param datetime
     * @return
     */
    private String generateId(long id, long datetime) {
        return this.formatter.generate(this, id, datetime < 0L ? new Date() : new Date(datetime));
    }

    /**
     * internalGenerate
     *
     * @param step 递增间隔
     * @return long[] 偶数位数组 ：其中奇数位为当前id，偶数位为当前datetime
     */
    protected abstract long[] internalGenerate(int step);
}
