package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
	private static String url = "jdbc:mysql://localhost:3306/dorm?useUnicode=true&characterEncoding=UTF-8";  
    private static String user = "root";  
    private static String password = "";  
      
    static {  
        try {  
            Class.forName("com.mysql.jdbc.Driver");  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();    
        }  
    }    
    /** 
     * 获取数据库的连接 
     * @return conn 
     */  
    public  Connection getConnection() {    
            try {  
                Connection conn = DriverManager.getConnection(url, user, password);
                return conn;
            } catch (SQLException e) {  
                e.printStackTrace();  
                throw new RuntimeException(e);  
            }  
        }      
      
    /** 
     * 释放资源 
     * @param conn 
     * @param pstmt 
     * @param rs 
     */  
    public void close(Connection conn){
    	try {
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    	try {
			if(conn != null){
				conn.close();
        }
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    }
}
