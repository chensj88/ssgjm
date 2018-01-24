package cn.com.winning.ssgj.base.id;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import javax.sql.DataSource;

/**
 * @author chensj@winning.com.cn
 */
public class SqlTimeService extends AbstractTimeService {

    //	private String timestampSql = "select now() from dual";
    //sqlserver
    private String timestampSql = "select GETDATE()";

    private DataSource dataSource;

    public void setTimestampSql(String timestampSql) {
        this.timestampSql = timestampSql;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected long remoteTimestamp() throws SQLException {
        Connection conn = null;
        try {
            conn = this.dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(this.timestampSql);
            long localDateTime = System.currentTimeMillis();
            if (rs.next()) {
                Timestamp localTimestamp = rs.getTimestamp(1);
                localDateTime = localTimestamp.getTime();
            }
            rs.close();
            stmt.close();
            return localDateTime;
        } catch (SQLException e) {
            throw new IllegalStateException("cannot get timestamp from database.");
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
            }
        }
    }
}