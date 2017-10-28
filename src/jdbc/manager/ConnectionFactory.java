package jdbc.manager;

//灏嗚繛鎺ユ暟鎹簱鎵撳寘锛�
//璋冪敤 杩涜杩炴帴    Connection conn=ConnectionFactory.getConnection();
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	private static String url = "jdbc:sqlserver://localhost:1433;DatabaseName=ATM";

	private static String username = "xiaofei";

	private static String password = "123456";

	private ConnectionFactory() {
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) {
		Connection conn = getConnection();
		if (conn == null)
			System.out.println("鏈繛鎺�");
		else
			System.out.println("宸茶繛鎺�");

	}

}
