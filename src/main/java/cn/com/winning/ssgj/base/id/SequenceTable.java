package cn.com.winning.ssgj.base.id;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author chensj@winning.com.cn
 */
public class SequenceTable {

    public static final String BEAN_ID = "sequenceTable";

    private static final Log logger = LogFactory.getLog(SequenceTable.class);

    private DataSource dataSource;

    private String tableName = "seq_no";

    private String idColumn = "seqno";

    private String typeColumn = "tabletype";

    private String insertSql;

    private String updateSql;

    private String selectSql;

    private String resetSql;

    private TimeService timeService = new SqlTimeService();

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setIdColume(String idColumn) {
        this.idColumn = idColumn;
    }

    public void setTypeColume(String typeColumn) {
        this.typeColumn = typeColumn;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setTimeService(TimeService timeService) {
        this.timeService = timeService;
    }

    protected TimeService getTimeSerivce() {
        return this.timeService;
    }

    public void init() {
        Connection conn = null;
        Statement statement = null;
        try {
            conn = this.dataSource.getConnection();
            statement = conn.createStatement();
            //修改SQLserver时间戳的问题
            String sql = "create table " + this.tableName + "("
                    + this.typeColumn + " varchar(20) not null, " + this.idColumn
                    + " int, updatetime datetime, primary key("
                    + this.typeColumn + "))";
            statement.execute(sql);
            statement.close();
        } catch (SQLException e) {
            logger.warn("cannot create sequence table, " + e.getMessage());
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
            }
        }
    }

    public void reset() {
        try {
            Connection conn = this.dataSource.getConnection();
            Statement statement = null;
            boolean autoCommit = conn.getAutoCommit();
            try {
                conn.setAutoCommit(false);
                statement = conn.createStatement();
                String str = "update table " + this.tableName + " set "
                        + this.idColumn + "=0, set updatetime='"
                        + new Timestamp(this.timeService.currentTimeMillis())
                        + "'";
                statement.execute(str);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                logger.warn("cannot reset sequence table, " + e);
            } finally {
                conn.setAutoCommit(autoCommit);
                try {
                    conn.close();
                } catch (Exception e) {
                }
            }
        } catch (SQLException e) {
            logger.warn("cannot reset sequence table, " + e);
        }
    }

    public Connection getConnection() throws SQLException {
        return this.dataSource.getConnection();
    }

    public long[] doSelect(Connection conn, String type, boolean dateCutOff, int dateCutType)
            throws SQLException {
        long currentTimeMillis = this.timeService.currentTimeMillis();
        long[] result = {-1L, -1L};
        PreparedStatement pstmt = conn.prepareStatement(getSelectSql());
        try {
            pstmt.setString(1, type);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                result[0] = rs.getLong(1);
                Timestamp localTimestamp = rs.getTimestamp(2);

                if ((dateCutOff)
                        && (this.timeService.isCutoff(dateCutType,
                        currentTimeMillis, localTimestamp.getTime()))) {
                    int k = doReset(conn, type, currentTimeMillis, result[0]);
                    if (k == 1) {
                        result[0] = 0L;
                    } else {
                        return doSelect(conn, type, dateCutOff);
                    }
                }

                if ((localTimestamp != null)
                        && (localTimestamp.getTime() > currentTimeMillis)) {
                    result[1] = localTimestamp.getTime();
                } else {
                    result[1] = currentTimeMillis;
                }
            } else {
                result[1] = currentTimeMillis;
                logger.warn("cannot get a id due to no initialized.");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException f) {
            }
        }

        return result;
    }

    public long[] doSelect(Connection conn, String type, boolean cutOffType)
            throws SQLException {
        return doSelect(conn, type, cutOffType, TimeService.DAY);
    }

    public int doInsert(Connection conn, String type, long id,
                        long datetime) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(getInsertSql());
        try {
            pstmt.setString(1, type);
            pstmt.setLong(2, id);
            pstmt.setTimestamp(3, new Timestamp(datetime));
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.warn("could not insert type " + type + ", " + e);
            throw e;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException f) {
            }
        }
    }

    public int doReset(Connection conn, String type, long datetime, long id)
            throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(getResetSql());
        try {
            pstmt.setTimestamp(1, new Timestamp(datetime));
            pstmt.setString(2, type);
            pstmt.setLong(3, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            logger.warn("could not reset type " + type + ", " + e);
            throw e;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException f) {
            }
        }
    }

    public int doUpdate(Connection conn, String type, int step,
                        long datetime, long id) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(getUpdateSql());
        try {
            pstmt.setLong(1, step);
            pstmt.setTimestamp(2, new Timestamp(datetime));
            pstmt.setString(3, type);
            pstmt.setLong(4, id);
            int k = pstmt.executeUpdate();
            return k;
        } catch (SQLException e) {
            throw e;
        } finally {
            try {
                pstmt.close();
            } catch (SQLException f) {
            }
        }
    }

    private String getSelectSql() {
        if (this.selectSql == null)
            this.selectSql = ("select " + this.idColumn + ", updatetime from "
                    + this.tableName + " where " + this.typeColumn + "=?");
        return this.selectSql;
    }

    private String getResetSql() {
        if (this.resetSql == null)
            this.resetSql = ("update " + this.tableName + " set "
                    + this.idColumn + "=0, updatetime=? where " + this.typeColumn
                    + "=? and " + this.idColumn + "=?");
        return this.resetSql;
    }

    private String getUpdateSql() {
        if (this.updateSql == null)
            this.updateSql = ("update " + this.tableName + " set "
                    + this.idColumn + "=" + this.idColumn
                    + "+?, updatetime=? where " + this.typeColumn + "=? and "
                    + this.idColumn + "=?");
        return this.updateSql;
    }

    private String getInsertSql() {
        if (this.insertSql == null)
            this.insertSql = ("insert into " + this.tableName + " ("
                    + this.typeColumn + "," + this.idColumn + ", updatetime) values (?,?,?)");
        return this.insertSql;
    }
}
