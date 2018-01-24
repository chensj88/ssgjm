package cn.com.winning.ssgj.base.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;

public class ConnectionUtil {
	private static HashMap<String, String> properties = new HashMap<String, String>();

	static {
		InputStream inputStream = ConnectionUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (Object key : p.keySet()) {
			properties.put((String) key, (String) p.get(key));
		}
	}

	/**
	 * @return Connection
	 */
	public static synchronized Connection getConnection() {
		// 读出配置信息
		String driverClassName = properties.get("jdbc.driverClassName");
		String url = properties.get("jdbc.url");
		String username = properties.get("jdbc.username");
		String password = properties.get("jdbc.password");
		
		Connection conn = null;
		try {
			// 加载数据库驱动程序
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}

	public static void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
				pstmt = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private ConnectionUtil() {
	}
}
