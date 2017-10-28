package model.md;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import jdbc.manager.ConnectionFactory;
import jdbc.manager.ControlDB;
import jdbc.manager.DbClose;

public class Card {
	private String account;
	private String password;
	private String createDate;
	private float balance;
	private int status;
	private String addressCard;
	private String blank;
	private int Card;
//	private String password;
//	private float balance;
//	private int  status;
//	private String addressCard;
//	private String blank;
//	private int Card;
//	private String createDate;
//
//	private String account;
	public String getBlank() {
		return blank;
	}

	public void setBlank(String blank) {
		this.blank = blank;
	}

	public int getCard() {
		return Card;
	}

	public void setCard(int card) {
		Card = card;
	}

	public Card() {

	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String acct) {
		this.account = acct;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passwd) {
		this.password = passwd;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String rdate) {
		this.createDate = rdate;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	//通过银行卡号获取状态
	public int getStatus(){
		return status;
		
	}
	public int getStatus(String account){
		int status = -1;
		try{
			String sql="select * from a_account where acct='"+ account + "'";
			ResultSet rs=ControlDB.executeQuery(sql);
			if(rs.next()){
				status=rs.getInt("status");
			}
			DbClose.close(rs);
		}catch(Exception e){
			System.out.println("getStatus方法异常");
		}
		//返回-1表示无此卡号
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAddressCard() {
		return addressCard;
	}

	public void setAddressCard(String addressCard) {
		this.addressCard = addressCard;
	}
	//通过银行卡，查询用户信息
	String name;
	String pid;
	long phone;
	String address;
	public String toString(String userAccount){
		String sql="select * from a_account where acct='"+ userAccount + "'";
		String sql2="select * from a_custnfo where acct='"+ userAccount+ "'";
		try {
			//客户信息
			ResultSet rs1=ControlDB.executeQuery(sql2);
			rs1.next();
			name = rs1.getString("name");
			pid=rs1.getString("pid");
			phone=rs1.getLong("phone");
			address=rs1.getString("adress");
			DbClose.close(rs1);	
			//银行卡信息
			ResultSet rs=ControlDB.executeQuery(sql);
			rs.next();
			password = rs.getString("passwd");
			balance=rs.getFloat("balance");
			status=rs.getInt("status");
			DbClose.close(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//0.冻结 1.正常 2.挂失 3.销户
		if(status==0)
		    {return userAccount+":"+password+":"+balance+":"+"冻结"+":"+name+":"+pid+":"+phone+":"+address; }
		else if(status==1)
			return userAccount+":"+password+":"+balance+":"+"正常"+":"+name+":"+pid+":"+phone+":"+address;
		else if(status==2)
			return userAccount+":"+password+":"+balance+":"+"挂失"+":"+name+":"+pid+":"+phone+":"+address;
		else
			return userAccount+":"+password+":"+balance+":"+"销户"+":"+name+":"+pid+":"+phone+":"+address;
		
		
	}

	// 检测该用户账号密码是否正确
	public static boolean checkAccount(String userAccount, int userPassword)
			throws Exception {
		String sql0 = "select * from a_account where acct='"+ userAccount + "'";// 查询该卡的密码
		ResultSet rs14 = ControlDB.executeQuery(sql0);
		while (rs14.next())// 检查账号是否存在。
		{
			int pass = rs14.getInt("passwd");
			if (pass == userPassword) {
				DbClose.close(rs14);
				return true;
			}

			DbClose.close(rs14);
			return false;
		}
		return false;

	}

	// 开户时输入的卡号信息
	public boolean create(String acct, int passwd, String rdate, double balance,
			int status, String addressCard, String blank1, int card)
			throws Exception {

		String sql8 = "insert into a_account(acct,passwd,rdate,balance,status,addressCard,Blank,Card)";
		sql8 = sql8 + " values('" + acct + "','" + passwd + "','" + rdate
				+ "','" + balance + "','" + status + "','" + addressCard
				+ "','" + blank1 + "','" + card + "')";
		ControlDB a2 = new ControlDB();
		if (a2.executeUpate(sql8))// 检验是否创建成功
			return true; // 创建成功，返回true;
		else
			return false; // 创建失败，返回false;

	}

	// 更改状态，即改变status，0.冻结 1.正常 2.挂失 3.销户
	public boolean changeStatus(String acct1, int status) throws Exception {
		String sql7 = "update a_account set status='" + status + "'";
		sql7 = sql7 + "where acct='" + acct1 + "'";
		ControlDB a1 = new ControlDB();
		if (a1.executeUpate(sql7))// 判断修改是否成功!
			return true;
		else {
			return false;
		}
	}
	// 修改密码
		public boolean changePassword(String acct1, int password) throws Exception {
			String sql7 = "update a_account set passwd='" + password + "'";
			sql7 = sql7 + "where acct='" + acct1 + "'";
			ControlDB a1 = new ControlDB();
			if (a1.executeUpate(sql7))// 判断修改是否成功!
				return true;
			else {
				return false;
			}
		}

	// 存款，deposit(long acct2,double savemoney),通过输入开号，存款进进行存款。
	public boolean deposit(String acct2, double savemoney) throws Exception {
		String sql21 = "select Blank from a_account where acct='" + acct2 + "'";// 查询该卡所属银行
		ResultSet rs14 = ControlDB.executeQuery(sql21);
		rs14.next();
		if (rs14.getString("Blank").equals("中国银行"))// 如果本卡是中国银行的卡，不用收手续费
		{
			String sql17 = "update a_account set balance+='" + savemoney + "'";
			sql17 = sql17 + "where acct='" + acct2 + "'";
			ControlDB a1 = new ControlDB();
			if (a1.executeUpate(sql17))// 判断存款是否成功!
				return true;
			else {
				return false;
			}
		} else {
			
			int res=JOptionPane.showConfirmDialog(null, "非本行账户,收取2.0%手续费，是否继续？", "跨行存款提示", JOptionPane.YES_NO_OPTION); 
			if(res==JOptionPane.YES_OPTION){ 
			String sql17 = "update a_account set balance+='"
					+ (savemoney * 0.98) + "'";
			sql17 = sql17 + "where acct='" + acct2 + "'";
			ControlDB a1 = new ControlDB();
			if (a1.executeUpate(sql17))// 判断存款是否成功!
				return true;
			else {
				return false;
			}
			}else{
				return false;
			}
		}

	}
	//查询是否为中国银行账号
	public boolean queryBlank(String acct) throws Exception{
		String sql31="select Blank from a_account where acct='"+acct+"'";
		ResultSet rs14 = ControlDB.executeQuery(sql31);
		rs14.next();
		if (rs14.getString("Blank").equals("中国银行"))// 如果本卡是中国银行的卡，不用收手续费
		{
			return true;
		}else
			return false;
	}

	// 取款，withDrawals(long acct2,double reduceMoney),通过输入开号，取款金额进行取款
	public boolean withDrawals(String acct21, double reduceMoney)
			throws Exception {
		String sql21 = "select Blank from a_account where acct='" + acct21
				+ "'";// 查询该卡所属银行
		ResultSet rs14 = ControlDB.executeQuery(sql21);
		rs14.next();
		if (rs14.getString("Blank").equals("中国银行"))// 如果本卡是中国银行的卡，不用收手续费
		{
			String sql17 = "update a_account set balance-='" + reduceMoney
					+ "'";
			sql17 = sql17 + "where acct='" + acct21 + "'";
			ControlDB a1 = new ControlDB();
			if (a1.executeUpate(sql17))// 判断取款是否成功!
				return true;
			else {
				return false;
			}
		} else {

			String sql17 = "update a_account set balance-='"
					+ (reduceMoney * 0.02 + reduceMoney) + "'";
			sql17 = sql17 + "where acct='" + acct21 + "'";
			ControlDB a1 = new ControlDB();
			if (a1.executeUpate(sql17))// 判断存款是否成功!
				return true;
			else {
				return false;
			}
		}

	}

	
	// 查询余额，输入要查询的账号，进行余额查询
	public  double queryBalance(String accts) throws Exception {

		String sql20 = "select balance,status from a_account where acct='"
				+ accts + "'";
		ResultSet rs13 = ControlDB.executeQuery(sql20);
		rs13.next();
		if (rs13.getInt("status") == 1 || rs13.getInt("status") == 0) {
			double balanced = rs13.getDouble("balance");
			return balanced;

		} else {
			System.out.println("你的账号不能进行查询操作!");
			return 0;
		}

	}

	// 转账，transferMoney(long firstAccount,long secondAccount,double toMoney)
	// firstAccount是本银行卡的账号，secondAccount对方银行卡的账号，toMoney所转金额
	public boolean transferMoney(String firstAccount, String secondAccount,
			double toMoney) throws Exception {
		String sql21 = "select Blank from a_account where acct='"
				+ secondAccount + "'";// 查询对方的卡所属银行
		ResultSet rs14 = ControlDB.executeQuery(sql21);
		rs14.next();
		if (rs14.getString("Blank").equals("中国银行"))// 如果对方的卡是中国银行的卡，不用收手续费
		{
			String sql17 = "update a_account set balance-='" + toMoney + "'";
			sql17 = sql17 + "where acct='" + firstAccount + "'";
			String sql18 = "update a_account set balance+='" + toMoney + "'";
			sql18 = sql18 + "where acct='" + secondAccount + "'";
			ControlDB a1 = new ControlDB();
			ControlDB a2 = new ControlDB();
			if (a1.executeUpate(sql17) && a2.executeUpate(sql18))// 判断取款是否成功!
				return true;
			else {
				return false;
			}
		} else {

			String sql17 = "update a_account set balance-='"
					+ (toMoney * 0.005 + toMoney) + "'";
			sql17 = sql17 + "where acct='" + firstAccount + "'";
			String sql18 = "update a_account set balance+='" + toMoney + "'";
			sql18 = sql18 + "where acct='" + secondAccount + "'";
			ControlDB a1 = new ControlDB();
			ControlDB a2 = new ControlDB();
			if (a1.executeUpate(sql17) && a2.executeUpate(sql18))// 判断存款是否成功!
				return true;
			else {
				return false;
			}
		}
	}
}
