package cn.com.winning.ssgj;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectTest {

    @Test
    public void testConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection
                     = DriverManager.getConnection("jdbc:sqlserver://172.16.0.200:1433;DatabaseName=ssgj","sa","zyc@8468");
            System.out.println(connection);
            PreparedStatement ps = connection.prepareStatement("select * from sys_dict_info");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
