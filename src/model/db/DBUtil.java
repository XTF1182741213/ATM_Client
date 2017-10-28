package model.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private static final String  url="jdbc:sqlserver://localhost:1433;DatabaseName=ATM";
	private static final  String user="xiaofei";
	private static final String password="123456";
	private static Connection conn=null;

	
public static Connection getConnection(){
	try
		{
			//加载驱动
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

					conn=	DriverManager.getConnection(url, user, password);
		//获取执行sql对象
	 if(conn!=null){
		 System.out.println("链接数据库成功！");
	 }
		
		//
		}catch (Exception e) {
			// TODO: handle exception
		}
	return conn;
}
	
}
