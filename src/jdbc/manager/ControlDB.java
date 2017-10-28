package jdbc.manager;

//对数据库进行增，删，改，查操作
//查找就调用          ResultSet rs=ControlDB.executeQuery(sql);
//增，删，更新 调用  ResultSet rs=ControlDB.executeUpate(String sql);
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ControlDB {
	// 执行Select语句
	public static ResultSet executeQuery(String sql) throws Exception {
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			throw e;
		}
		return rs;
	}

	// 执行Insert,Update,Delete语句
	public boolean executeUpate(String sql) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.err.println("执行SQL语句出错:" + ex.getMessage());
		} finally {
			DbClose.close(stmt, conn);
		}
		return false;
	}

}
