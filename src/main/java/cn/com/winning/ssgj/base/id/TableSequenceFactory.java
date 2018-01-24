package cn.com.winning.ssgj.base.id;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author chensj@winning.com.cn
 */
public class TableSequenceFactory extends AbstractSequenceFactory {

    private static final Log logger = LogFactory.getLog(TableSequenceFactory.class);

    private int retryCount = 20;

    SequenceTable sequenceTable;

    boolean dateCutOff;

    boolean autoCommit = true;

    //使用Calendar.DAY_OF_MONTH 为月切
//    int cutOffType = Calendar.DAY_OF_MONTH;
    //使用Calendar.DAY_OF_YEAR 为日切
    int cutOffType = Calendar.DAY_OF_YEAR;

    public void setTable(SequenceTable sequenceTable) {
        this.sequenceTable = sequenceTable;
    }

    public void setRetryCount(int retryCount) {
        this.retryCount = retryCount;
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    public void setDateCutOff(boolean dateCutOff) {
        this.dateCutOff = dateCutOff;
    }

    public int getCutOffType() {
        return cutOffType;
    }

    public void setCutOffType(int cutOffType) {
        this.cutOffType = cutOffType;
    }

    protected long[] internalGenerate(int step) {
        try {
            Connection conn = this.sequenceTable.getConnection();
            boolean commitFlag = conn.getAutoCommit();
            try {
                if (!this.autoCommit) {
                    conn.setAutoCommit(false);
                }
                int i = 0;
                long[] result = null;
                for (int j = 0; (j < this.retryCount) && (i != 1); j++) {
                    try {
                        result = this.sequenceTable.doSelect(conn, this.type, this.dateCutOff, this.cutOffType);
                        if (result[0] == -1L) {
                            this.sequenceTable.doInsert(conn, this.type, 0L, result[1]);
                            result[0] = 0L;
                        }
                        i = this.sequenceTable.doUpdate(conn, this.type, step, result[1], result[0]);
                    } catch (SQLException f) {
                        logger.warn("get id retry: " + j + ", cause:" + f);
                        if (j == this.retryCount - 1) {
                            throw f;
                        }
                    }
                }
                if (i != 1) {
                    throw new IllegalStateException("cannot get id");
                }

                if (!this.autoCommit) {
                    conn.commit();
                }

                return result;
            } catch (SQLException e) {
                if (!this.dateCutOff) {
                    conn.rollback();
                }
                throw e;
            } finally {
                if (!this.autoCommit) {
                    conn.setAutoCommit(commitFlag);
                }
                try {
                    conn.close();
                } catch (SQLException f) {
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
