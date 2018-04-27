package cn.com.winning.ssgj.base.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
        String driverClassName = properties.get("driverClassName");
        String url = properties.get("url");
        String username = properties.get("username");
        String password = properties.get("password");

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

    /**
     * @param obj
     * @return
     * @throws Exception
     * @description javabean 转化成Map
     */
    public static HashMap<String, Object> objectToMap(Object obj) {
        if (obj == null)
            return null;

        HashMap<String, Object> map = new HashMap<String, Object>();

        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(obj.getClass());

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") == 0) {
                    continue;
                }
                Method getter = property.getReadMethod();
                Object value = getter != null ? getter.invoke(obj) : null;
                Set<String> set = null;
                HashMap<String, Object> curMap = null;
                if (value != null && "map".equalsIgnoreCase(key)) {
                    //封装map内属性
                    curMap = (HashMap) value;
                    //获取keySet
                    set = curMap.keySet();
                    for (String curSet : set) {
                        map.put(key.toLowerCase() + "." + curSet, curMap.get(curSet).toString());
                    }
                } else {
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
