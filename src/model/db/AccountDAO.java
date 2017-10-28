package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.md.Card;
import model.md.User;


public class AccountDAO {
	private DBUtil dbc=new DBUtil();
	private String account=new String();
	private Connection conn=null;
	//增加
	public void add(User user) throws Exception{
	 conn=DBUtil.getConnection();

		//添加用户卡号信息
		String sql=""+
				"insert into dbo.a_account" +
				"(acct,passwd,rdate,balance,status,addressCard,Blank,Card)" +
				"values(" +
				"?,?,GETDATE(),?,?,?,?,?)";
		PreparedStatement ptmt=conn.prepareStatement(sql);
		ptmt.setString(1, user.getAccount());
		ptmt.setString(2, user.getPassword());
		ptmt.setString(3, user.getBalance()+"");
		ptmt.setInt(4, user.getStatus());
		ptmt.setString(5, user.getAddressCard());
		ptmt.setString(6, user.getBlank());
		ptmt.setInt(7, user.getCardbelongID());
		//执行
		ptmt.execute();
		
		DBClose.close(ptmt, conn);
	}
	//删
	public boolean delete(String account){
		boolean check = false;
		try {
			conn=DBUtil.getConnection();
			//添加信息至用户信息表
			String sql="" +
					"delete dbo.a_account" +
					" where acct=?" ;
			PreparedStatement ptmt=conn.prepareStatement(sql);
			
			ptmt.setString(1, account);
			//执行
			check=ptmt.execute();
			DBClose.close(ptmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	return check;
	
	}
	//改
	public boolean update(Card card){
		
		conn=DBUtil.getConnection();
		String sql="" +
				" update dbo.a_account " +
				" set acct=?,passwd=?,rdate=?,balance=?,status=?,addressCard=?,Blank=?,Card=? " +
					" where acct=? ";
		PreparedStatement ptmt;
		boolean check=false;
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, card.getAccount());
			ptmt.setString(2, card.getPassword());
			ptmt.setString(3, card.getCreateDate());
			ptmt.setFloat(4, card.getBalance());
			ptmt.setInt(5, card.getStatus());
			ptmt.setString(6, card.getAddressCard());
			ptmt.setString(7, card.getBlank());
			ptmt.setInt(8, card.getCard());
			ptmt.setString(9, card.getAccount());
		ptmt.execute();
		check=true;
		DBClose.close(ptmt, conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		return check;
		
	}
	//查
	public List<Card> query(List<Map<String, Object>> params) throws Exception{
		List<Card> result=new ArrayList<Card>();
		
		conn=DBUtil.getConnection();//获取链接
		
		StringBuilder sb=new StringBuilder();
		
		sb.append("select * from a_account where 1=1 ");//构造sql语句
		
		if(params!=null&&params.size()>0){
			for (int i = 0; i < params.size(); i++) {
				Map<String, Object> map=params.get(i);
				sb.append(" and  "+map.get("name")+" "+map.get("rela")+" "+map.get("value")+" ");
			}
		}
		
		PreparedStatement ptmt=conn.prepareStatement(sb.toString());//预处理
		
		System.out.println(sb.toString());//把sql语句输出
		ResultSet rs=ptmt.executeQuery();
		
		Card card=null;
		while(rs.next()){
			card=new Card();
			card.setAccount(rs.getString("acct"));
			card.setPassword((rs.getString("passwd")));
			card.setCreateDate((rs.getString("rdate")));
			card.setBalance(Float.parseFloat(rs.getString("balance")));
			card.setStatus(rs.getInt("status"));
			card.setAddressCard(rs.getString("addressCard"));
			card.setBlank((rs.getString("Blank")));

			card.setCard(rs.getInt("Card"));
			result.add(card);
		}
		DBClose.close(rs, ptmt, conn);
		return result;
	}
}
