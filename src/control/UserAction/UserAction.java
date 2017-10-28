package control.UserAction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.db.AccountDAO;
import model.db.CustnfoDAO;
import model.db.DBClose;
import model.db.DBUtil;
import model.db.DealDAO;
import model.md.Card;
import model.md.Customerinfo;
import model.md.Deal;
import model.md.GETDate;
import model.md.SeqCreate;
import model.md.User;

public class UserAction {

	private CustnfoDAO custnfodao;
	private AccountDAO accountdao;
	private DealDAO dealdao;

	public void addUser(User user) throws Exception {// 增加用户信息
		custnfodao = new CustnfoDAO();
		accountdao = new AccountDAO();
		dealdao = new DealDAO();
		custnfodao.add(user);
		accountdao.add(user);
		dealdao.add(user);
	}

	public boolean findUser(String account, String password) {
		boolean access = false;
		// 查询数据库，比对用户账户密码，正确则返回true
		accountdao = new AccountDAO();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("name", "acct");
		map.put("rela", "=");
		map.put("value", "'"+account+"'");
		params.add( map);
		 map = new HashMap<String, Object>();
		map.put("name", "passwd");
		map.put("rela", "=");
		map.put("value","'"+ password+"'");
		params.add(map);

		try {

			List<Card> result;
			result = accountdao.query(params);
			if (result.size() > 0) {
				access = true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return access;
	}

	public float getBalance(String account) {
		// TODO Auto-generated method stub
		accountdao = new AccountDAO();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("name", "acct");
		map.put("rela", "=");
		map.put("value", "'"+account+"'");

		params.add(map);
		float balance = 0;

		try {

			List<Card> result;
			result = accountdao.query(params);

			balance = result.get(0).getBalance();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return balance;
	}

	public float changeMoney(float dealmoney, String account) {
	
		boolean check=false;
		float balance = 0;
		Card card=new Card();
		accountdao = new AccountDAO();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		

		map.put("name", "acct");
		map.put("rela", "=");
		map.put("value", "'"+account+"'");

		params.add(map);
		

		try {

			List<Card> result;
			result = accountdao.query(params);

			card = result.get(0);
			balance=card.getBalance()+dealmoney;
			card.setBalance(balance);
			check=accountdao.update(card);
			if (check) {
				if (dealmoney > 0) {
					System.out.println("存款成功，现在余额为：" + balance);
				
					
					
					
				} else {

					System.out.println("取款成功，现在余额为：" + balance);
					
					
					
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		return 0;

	}

	public  void log(float dealmoney, String account) {
		//转入6，转出5，存款4，取款3,
		//转入5，转出6,存款7，取款8
		// TODO Auto-generated method stub
		dealdao=new DealDAO();
		Deal deal=new Deal();
		GETDate date=new GETDate();
		if(dealmoney>0){
			System.out.println("存款");
SeqCreate seqcreate=new SeqCreate();
			//String seq=account+date.getNowYear()+date.getNowMonth()+date.getNowDay();
			String seq=seqcreate.create(account);
			User user=new User();
			user.setAcctothers(null);
			user.setAccount(account);
			user.setSeq(seq);
			user.setDealMoney(dealmoney);
			user.setDealDate(new Date());
			
			user.setDealType("4");
			try {
				dealdao.add(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("取款");
			SeqCreate seqcreate=new SeqCreate();
						//String seq=account+date.getNowYear()+date.getNowMonth()+date.getNowDay();
						String seq=seqcreate.create(account);
						User user=new User();
						user.setAcctothers(null);
						user.setAccount(account);
						user.setSeq(seq);
						user.setDealMoney(-dealmoney);
						user.setDealDate(new Date());
						
						user.setDealType("3");
						try {
							dealdao.add(user);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		}
		
		
		
	}

	public boolean queryBlank(String account) {
		// TODO Auto-generated method stub
		String blank="";
		boolean check=false;
		Card card=new Card();
		accountdao = new AccountDAO();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		

		map.put("name", "acct");
		map.put("rela", "=");
		map.put("value", "'"+account+"'");

		params.add(map);
		try {

			List<Card> result;
			result = accountdao.query(params);

			blank = result.get(0).getBlank();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(blank.equals("中国银行")){
			check=true;
		}
		return check;
	}

	public List<Deal> getDealDetail(User user) {

		// TODO Auto-generated method stub
		dealdao=new DealDAO();
	List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

	Map<String, Object> map = new HashMap<String, Object>();
	List<Deal> result = null;
	map.put("name", "acct");
	map.put("rela", "=");
	map.put("value", "'"+user.getAccount()+"'");
	params.add( map);

	try {

		
		result = dealdao.query(params);
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

		
			return result;
	}

	public boolean checkAccount(String account) {
		// TODO Auto-generated method stub
		boolean check=false;
		accountdao = new AccountDAO();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("name", "acct");
		map.put("rela", "=");
		map.put("value", "'"+account+"'");

		params.add(map);
	

		try {

			List<Card> result;
			result = accountdao.query(params);

		if(!result.isEmpty()){
			check=true;
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return check;
	}

	public boolean changePassword(String account, String password3) {
		// TODO Auto-generated method stub
		boolean check=false;
		float balance = 0;
		Card card=new Card();
		accountdao = new AccountDAO();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		

		map.put("name", "acct");
		map.put("rela", "=");
		map.put("value", "'"+account+"'");

		params.add(map);
		

		try {

			List<Card> result;
			result = accountdao.query(params);

			card = result.get(0);
		
			card.setPassword(password3);;
			check=accountdao.update(card);
			if (check) {
		System.out.println("修改密码成功!现在密码为:"+password3);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;

	}

	public boolean transferMoney(float changer,String account, String acctothers, float dealmoney) {
		// TODO Auto-generated method stub
				boolean check=false;
				float balance = 0;
	
				try {
					
					this.changeMoney(-(dealmoney+changer), account);
					this.changeMoney(dealmoney, acctothers);
				
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return check;
	}

	public  void log(float changer,float dealmoney, String acctothers, String account) {
		// TODO Auto-generated method stub
	//转入5，转出6,存款7，取款8
		dealdao=new DealDAO();
		SeqCreate seqcreate=new SeqCreate();
		String seq=seqcreate.create(acctothers);
		User user=new User();
		user.setAcctothers(null);
		user.setAccount(acctothers);
		user.setSeq(seq);
		user.setDealMoney(dealmoney-changer);
		user.setDealDate(new Date());
		
		user.setDealType("6");
		try {
			dealdao.add(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//String seq=account+date.getNowYear()+date.getNowMonth()+date.getNowDay();
		 seq=seqcreate.create(account);
		user=new User();
		user.setAcctothers(acctothers);
		user.setAccount(account);
		user.setSeq(seq);
		user.setDealMoney(dealmoney);
		user.setDealDate(new Date());
		
		user.setDealType("5");
		try {
			dealdao.add(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getUserName(String account) {
		// TODO Auto-generated method stub
		custnfodao = new CustnfoDAO();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("name", "acct");
		map.put("rela", "=");
		map.put("value", "'"+account+"'");

		params.add(map);
	String username="";

		try {

			List<Customerinfo> result;
			result = custnfodao.query(params);

			username = result.get(0).getUsername();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
System.out.println("username="+username);
		return username;
	}

	public boolean checkTakeMoney(String account,float money) {
		// TODO Auto-generated method stub
		boolean check=false;
	GETDate date = new GETDate();
		dealdao = new DealDAO();// 查询最后的一个单子

		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		String nowdate = date.getNowYear() + date.getNowMonth()
				+ date.getNowDay();
		map.put("name", "seq");
		map.put("rela", "like");
		map.put("value", "'%" + nowdate + "%'");

		params.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "acct");
		map.put("rela", "=");
		map.put("value", "'" + account + "'");
		
		params.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "dtype");
		map.put("rela", "=");
		map.put("value", "'" + 3+ "'");

		params.add(map);
		String seq = "";
		String seqlast = "";
float dealmoney=0;
		try {

			List<Deal> result;
			
			result = dealdao.query(params);
			System.out.println("result.size()="+result.size());
			for (int i = 0; i < result.size(); i++) {
				dealmoney=dealmoney+result.get(i).getDealMoney();
			}
			if (dealmoney+money<=20000) {
					check=true;
				
			} 

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return check;
	}

		public boolean checkSaveMoney(String account,float money) {
		// TODO Auto-generated method stub
		boolean check=false;
		GETDate date = new GETDate();
		dealdao = new DealDAO();// 查询最后的一个单子

		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		String nowdate = date.getNowYear() + date.getNowMonth()
				+ date.getNowDay();
		map.put("name", "seq");
		map.put("rela", "like");
		map.put("value", "'%" + nowdate + "%'");

		params.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "acct");
		map.put("rela", "=");
		map.put("value", "'" + account + "'");
		
		params.add(map);
		map = new HashMap<String, Object>();
		map.put("name", "dtype");
		map.put("rela", "=");
		map.put("value", "'" + 4+ "'");

		params.add(map);
		String seq = "";
		String seqlast = "";
float dealmoney=0;
		try {

			List<Deal> result;
			
			result = dealdao.query(params);
			System.out.println("result.size()="+result.size());
			for (int i = 0; i < result.size(); i++) {
				dealmoney=dealmoney+result.get(i).getDealMoney();
			}
			if (dealmoney+money<=50000) {
					check=true;
				
			} 

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return check;
	}

		public int checkStatus(String account) {

			// TODO Auto-generated method stub
			int check=0;
			accountdao = new AccountDAO();
			List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("name", "acct");
			map.put("rela", "=");
			map.put("value", "'"+account+"'");

			params.add(map);
		

			try {

				List<Card> result;
				result = accountdao.query(params);

			if(!result.isEmpty()){
				check=result.get(0).getStatus();
			}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return check;
		}




}
