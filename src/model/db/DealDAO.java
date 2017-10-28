package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.md.Customerinfo;
import model.md.Deal;
import model.md.User;

public class DealDAO {

	public DealDAO() {
		// TODO Auto-generated constructor stub
	}

	private DBUtil dbc = new DBUtil();
	private String account = new String();
	private Connection conn = null;


	// 增加
	public void add(User user) throws Exception {
		conn = DBUtil.getConnection();
		// 添加信息至交易处理表
		String sqlCustnfo = "" + "insert into dbo.a_deal"
				+ "(acctothers,acct,seq,dealmoney,rdate,dtype)" + "values(" + "?,?,?,?,GETDATE(),?)";
		PreparedStatement ptmt = conn.prepareStatement(sqlCustnfo);

		ptmt.setString(1, user.getAcctothers());
		ptmt.setString(2, user.getAccount());
		ptmt.setString(3, user.getSeq());
		ptmt.setFloat(4, user.getDealMoney());
		ptmt.setString(5, user.getDealType());
	
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
			String sqlCustnfo = "" + "delete dbo.a_deal" + " where acct=?";
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
	public boolean update(Deal deal) {

		conn = DBUtil.getConnection();
		String sql = "" + " update dbo.a_deal "
				+ " set acctothers=?,acc=?,seq=?,dealmoney=?,dtype=? "
				+ " where acct=? ";
		PreparedStatement ptmt;
		boolean check = false;
		try {
			ptmt = conn.prepareStatement(sql);
			
			ptmt.setString(1, deal.getAcctothers());
			ptmt.setString(2, deal.getAccount());
			ptmt.setString(3, deal.getSeq());
			ptmt.setFloat(4, deal.getDealMoney());
			ptmt.setString(5, deal.getDealType());
			
	

			check = ptmt.execute();		
			DBClose.close(ptmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;

	}

	// 查
	public List<Deal> query(List<Map<String, Object>> params)
			throws Exception {
		List<Deal> result = new ArrayList<Deal>();

		conn = DBUtil.getConnection();// 获取链接

		StringBuilder sb = new StringBuilder();

		sb.append("select * from a_deal where 1=1 ");// 构造sql语句

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

		Deal deal = null;
		while (rs.next()) {
			deal = new Deal();
			deal.setAccount(rs.getString("acct"));
			deal.setAcctothers(rs.getString("acctothers"));
			deal.setSeq(rs.getString("seq"));
			deal.setDealMoney(rs.getFloat("dealmoney"));
			deal.setDealDate(rs.getDate("rdate"));
			deal.setDealType(rs.getString("dtype"));

			result.add(deal);
		}
		DBClose.close(rs, ptmt, conn);
		return result;
	}

}
