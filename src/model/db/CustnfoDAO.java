package model.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.md.Card;
import model.md.Customerinfo;
import model.md.User;

public class CustnfoDAO {
	private DBUtil dbc = new DBUtil();
	private String account = new String();
	private Connection conn = null;

	public CustnfoDAO() {
		// TODO Auto-generated constructor stub

	}

	// 增加
	public void add(User user) throws Exception {
		conn = DBUtil.getConnection();
		// 添加信息至用户信息表
		String sqlCustnfo = "" + "insert into dbo.a_custnfo"
				+ "(acct,name,pid,phone,adress)" + "values(" + "?,?,?,?,?)";
		PreparedStatement ptmt = conn.prepareStatement(sqlCustnfo);

		ptmt.setString(1, user.getAccount());
		ptmt.setString(2, user.getUsername());
		ptmt.setString(3, user.getPid());
		ptmt.setString(4, user.getPhone());
		ptmt.setString(5, user.getAddress());
		// 执行
		ptmt.execute();

		DBClose.close(ptmt, conn);
	}

	// 删
	public boolean delete(String account) {
		boolean check = false;
		try {
			conn = DBUtil.getConnection();
			// 添加信息至用户信息表
			String sqlCustnfo = "" + "delete dbo.a_custnfo" + " where acct=?";
			PreparedStatement ptmt = conn.prepareStatement(sqlCustnfo);

			ptmt.setString(1, account);
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
	public boolean update(Customerinfo customerinfo) {

		conn = DBUtil.getConnection();
		String sql = "" + " update dbo.a_custnfo "
				+ " set acct=?,name=?,pid=?,phone=?,adress=?,sex=? "
				+ " where acct=? ";
		PreparedStatement ptmt;
		boolean check = false;
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, customerinfo.getAccount());
			ptmt.setString(2, customerinfo.getUsername());
			ptmt.setString(3, customerinfo.getPid());
			ptmt.setString(4, customerinfo.getPhone());
			ptmt.setString(5, customerinfo.getAddress());
			ptmt.setString(6, customerinfo.getAccount());

			check = ptmt.execute();		DBClose.close(ptmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;

	}

	// 查
	public List<Customerinfo> query(List<Map<String, Object>> params)
			throws Exception {
		List<Customerinfo> result = new ArrayList<Customerinfo>();

		conn = DBUtil.getConnection();// 获取链接

		StringBuilder sb = new StringBuilder();

		sb.append("select * from a_custnfo where 1=1 ");// 构造sql语句

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

		Customerinfo customerinfo = null;
		while (rs.next()) {
			customerinfo = new Customerinfo();
			customerinfo.setAccount(rs.getString("acct"));
			customerinfo.setUsername((rs.getString("name")));
			customerinfo.setSex((rs.getString("sex")));
			customerinfo.setPid(rs.getString("pid"));
			customerinfo.setPhone(rs.getString("phone"));
			customerinfo.setAddress(rs.getString("adress"));

			result.add(customerinfo);
		}
		DBClose.close(rs, ptmt, conn);
		return result;
	}
}
