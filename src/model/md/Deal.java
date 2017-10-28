package model.md;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import jdbc.manager.ControlDB;
import jdbc.manager.DbClose;

public class Deal {
	
	private String  acctothers;
	private Date dealDate;
	private String account;
	private String seq;
	private float dealMoney;
	private String dealType;

	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public String getAcctothers() {
		return acctothers;
	}
	public void setAcctothers(String acctothers) {
		this.acctothers = acctothers;
	}
	public Date getDealDate() {
		return dealDate;
	}
	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public float getDealMoney() {
		return dealMoney;
	}
	public void setDealMoney(float dealMoney) {
		this.dealMoney = dealMoney;
	}
	public Deal() {
		// TODO Auto-generated constructor stub
	}
	public void dealLog(String acct) throws Exception {
		
		String sql12 = "select * from a_deal";
		ResultSet rs5 = ControlDB.executeQuery(sql12);
		while(rs5.next()){
		if (rs5.getString("acct").equals(acct) ){
			    String accountOthers = rs5.getString("acctothers");
				String seqEx = rs5.getString("seq");
				Timestamp datetime = rs5.getTimestamp("rdate");
				int type = rs5.getInt("dtype");
				float dmoney = rs5.getFloat("dealmoney");
				
				
				 
				switch (type) {
				case 4:
					System.out.println(datetime + "    " + "取款" + "        "
							+ accountOthers + "     " + seqEx + "       " + dmoney);
					break;
				case 5:
					System.out.println(datetime + "    " + "存款" + "        "
							+ accountOthers + "     " + seqEx + "       " + dmoney);
					break;
				case 6:
					System.out.println(datetime + "    " + "转出" + "        "
							+ accountOthers + "     " + seqEx + "       " + dmoney);
					break;
				default:
					System.out.println(datetime + "    " + "转进" + "        "
							+ accountOthers + "     " + seqEx + "       " + dmoney);
				}
				System.out.println();
				System.out.println("日期&时间：          "+datetime);
				System.out.println("类型：                       "+type);
				System.out.println("对方账号：             "+accountOthers);
				System.out.println("交易金额：             "+ dmoney);
				System.out.println("流水号：                  "+ seqEx);

			
		}
		DbClose.close(rs5);
		return;
	}
	}

}
