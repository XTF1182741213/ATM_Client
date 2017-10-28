package jdbc.manager;

//在执行增加，删除，修改的时候，可用]
//DbClose.close(Statement stmt,Connection conn);
//在执行查询，可用
//Dbclose.close(ResultSet rs,Statement stmt,Connection conn);
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbClose {
	// 关闭Connection
	public static void close(Connection conn) {
		if (null != conn) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 关闭Statement
	public static void close(Statement stmt) {
		if (null != stmt) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 关闭ResultSet
	public static void close(ResultSet rs) {
		if (null != rs) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 关闭执行Select的JDBC资源
	public static void close(ResultSet rs, Statement stmt, Connection conn) {
		close(rs);
		close(stmt, conn);
	}

	// 关闭用来执行Insert,Update,Delete的JDBC资源
	public static void close(Statement stmt, Connection conn) {
		close(stmt);
		close(conn);
	}

}
