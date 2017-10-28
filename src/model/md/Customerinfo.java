package model.md;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.manager.ConnectionFactory;
import jdbc.manager.ControlDB;
import jdbc.manager.DbClose;

public class Customerinfo {
    
	private String account;
	private String username;
	private String pid;
	private String phone;
	private String address;
//	private String account;
	private String sex;
public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	//	private String username;
//	private String phone;
//	private String address;
//	private String pid;
	public String getAccount() {
		return account;
	}

	public void setAccount(String acct) {
		this.account = acct;
	}

	public Customerinfo() {

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String name) {
		this.username = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    //通过身份证查询信息
	int passwd;
	double balance;
	int status;
	public ArrayList<String> toString(String personID){
		ArrayList<String> list=new ArrayList<String>();
		String sql="select * from a_custnfo where pid='"+ personID + "'";
		String[] results = new String[100];
		int i = 0;
		try {
			//客户信息
			ResultSet rs=ControlDB.executeQuery(sql);
			ResultSet rs2 = null;
			while(rs.next()){
				account=rs.getString("acct");
				username = rs.getString("name");
				pid=rs.getString("pid");
				phone=rs.getString("phone");
				address=rs.getString("adress");

				//根据卡号获取其他信息
				String sql2="select * from a_account where acct='"+ account + "'";
				rs2=ControlDB.executeQuery(sql2);
				while(rs2.next()){
					passwd = rs2.getInt("passwd");
					balance=rs2.getDouble("balance");
					status=rs2.getInt("status");
					
				}
				//0.冻结 1.正常 2.挂失 3.销户
				
				if(status==0)//冻结
				{
					String result = (account+":"+passwd+":"+balance+":"+"冻结"+":"+username+":"+pid+":"+phone+":"+address);
					results[i++] = result;
					list.add(results[i-1]);
					
				}
				else if(status==1)//正常
				{
					String result = (account+":"+passwd+":"+balance+":"+"正常"+":"+username+":"+pid+":"+phone+":"+address);
					results[i++] = result;
					list.add(results[i-1]);
					
				}
				else if(status==2)//挂失
				{
					String result = (account+":"+passwd+":"+balance+":"+"挂失"+":"+username+":"+pid+":"+phone+":"+address);
					results[i++] = result;
					list.add(results[i-1]);
					
				}
				else if(status==3)//销户
				{
					String result = (account+":"+passwd+":"+balance+":"+"销户"+":"+username+":"+pid+":"+phone+":"+address);
					results[i++] = result;
					list.add(results[i-1]);
					
				}
			
				
				
			}
			DbClose.close(rs2);
			DbClose.close(rs);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	// 创建用户信息
	public boolean createInfo(String accts,String names,String sex, String pids, String phones,
			String addresss) throws Exception {

		String sql10 = "insert into a_custnfo(acct,name,sex,pid,phone,adress)";
		sql10 = sql10 + " values('"+accts+"','" + names + "','"+ sex + "','" + pids + "','" + phones + "','"+ addresss + "')";
		ControlDB b1 = new ControlDB();	
		if (b1.executeUpate(sql10))// 检验是否创建成功
			return true; // 创建成功，返回true;
		else
			return false; // 创建失败，返回false;
	}

	// 修改客户信息,这里使用changeName(long acct,String name)修改客户的姓名
	public boolean changeName(String acct, String name) throws Exception {
		
		String sql4="update a_custnfo set name='"+name+"'";
		sql4=sql4+"where acct='"+acct+"'";
		ControlDB a = new ControlDB();
		if (a.executeUpate(sql4))// 判断修改是否成功!
			return true;
		else {
			return false;
		}
	}

	// 修改客户信息,这里使用changePhone(long acct,long phone)修改客户的手机号
	public boolean changePhone(String acct, String phone) throws Exception {
		
		String sql5="update a_custnfo set phone='"+phone+"'";
		sql5=sql5+"where acct='"+acct+"'";
		ControlDB a = new ControlDB();
		if (a.executeUpate(sql5))// 判断修改是否成功!
			return true;
		else {
			return false;
		}
	}

	// 修改客户信息,这里使用changeAddress(long acct,String address)修改客户的地址
	public boolean changeAddress(String acct, String address) throws Exception {
		
		String sql6="update a_custnfo set adress='"+address+"'";
		sql6=sql6+"where acct='"+acct+"'";
		ControlDB a = new ControlDB();
		if (a.executeUpate(sql6))// 判断修改是否成功!
			return true;
		else {
			return false;
		}
	}

}
