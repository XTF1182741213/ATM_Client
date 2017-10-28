package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.md.Admin;
import model.md.Card;
import model.md.Customerinfo;
import model.md.User;

public class AdminDAO {
	private DBUtil dbc = new DBUtil();
	private Connection conn = null;

	public AdminDAO() {
		// TODO Auto-generated constructor stub
	}

	// 增加
	public void add(Admin admin) throws Exception {
		conn = DBUtil.getConnection();
		// 添加管理员信息表
		String sql = "" + "insert into dbo.a_admin"
				+ "(adminID,adminName,adminPassword,adminJuris)" + "values("
				+ "?,?,?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sql);

		ptmt.setString(1, admin.getAdminID());
		ptmt.setString(2, admin.getAdminName());
		ptmt.setString(3, admin.getAdminPassword());
		ptmt.setInt(4, admin.getAdminJuris());

		// 执行
		ptmt.execute();
		DBClose.close(ptmt, conn);

	}

	// 删
	public boolean delete(String adminID) {
		boolean check = false;
		try {
			conn = DBUtil.getConnection();

			String sqlCustnfo = "" + "delete dbo.a_admin" + " where adminID=?";
			PreparedStatement ptmt = conn.prepareStatement(sqlCustnfo);

			ptmt.setString(1, adminID);
			// 执行
			check = ptmt.execute();
			DBClose.close(ptmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;

	}

	// 改
	public boolean update(Admin admin) {

		conn = DBUtil.getConnection();
		String sql = "" + " update dbo.a_admin "
				+ " set adminID=?,adminName=?,adminPassword=?,adminJuris=? "
				+ " where adminID=? ";
		PreparedStatement ptmt;
		boolean check = false;
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, admin.getAdminID());
			ptmt.setString(2, admin.getAdminName());
			ptmt.setString(3, admin.getAdminPassword());
			ptmt.setInt(4, admin.getAdminJuris());
			ptmt.setString(5, admin.getAdminID());

			check = ptmt.execute();		DBClose.close(ptmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;

	}

	// 查
	public List<Admin> query(List<Map<String, Object>> params) throws Exception {
		List<Admin> result = new ArrayList<Admin>();

		conn = DBUtil.getConnection();// 获取链接

		StringBuilder sb = new StringBuilder();

		sb.append("select * from a_admin where 1=1 ");// 构造sql语句

		if (params != null && params.size() > 0) {
			for (int i = 0; i < params.size(); i++) {
				Map<String, Object> map = params.get(i);
				sb.append(" and  " + map.get("name") + " " + map.get("rela")
						+ " " + map.get("value") + " ");
			}
		}

		PreparedStatement ptmt = conn.prepareStatement(sb.toString());// 预处理

		System.out.println(sb.toString());// 把sql语句输出
		ResultSet rs = ptmt.executeQuery();

		Admin admin = null;
		while (rs.next()) {
			admin = new Admin();
			admin.setAdminID(rs.getString("adminID"));
			;
			admin.setAdminJuris(rs.getInt("adminJuris"));
			admin.setAdminName(rs.getString("adminName"));
			admin.setAdminPassword(rs.getString("adminPassword"));

			result.add(admin);
		}
		DBClose.close(rs, ptmt, conn);
		return result;
	}

	public boolean findUser(String account, String password) {
		boolean access = false;
		// 查询数据库，比对用户账户密码，正确则返回true
		
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", "adminID");
		map.put("rela", "=");
		map.put("value", "'"+account+"'");
		params.add( map);
		 map = new HashMap<String, Object>();
		map.put("name", "adminPassword");
		map.put("rela", "=");
		map.put("value","'"+ password+"'");
		params.add(map);

		try {

			List<Admin> result;
			result = this.query(params);
			if (result.size() > 0) {
				access = true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return access;
	}
}
