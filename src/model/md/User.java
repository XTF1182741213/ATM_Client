package model.md;

import java.util.Date;


public class User {
	private String  acctothers;
	private Card card = new Card();
	private Customerinfo customerinfo = new Customerinfo();

	private Deal deal = new Deal();
	
	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Customerinfo getCustomerinfo() {
		
		return customerinfo;
	}

	public void setCustomerinfo(Customerinfo customerinfo) {
		this.customerinfo = customerinfo;
	}

	public Deal getDeal() {
		return deal;
	}

	public void setDeal(Deal deal) {
		this.deal = deal;
	}

	public String getAcctothers() {
		return acctothers;
	}

	public void setAcctothers(String acctothers) {
		this.acctothers = acctothers;
	}



	public String getAccount() {
		return card.getAccount();
	}

	public void setAccount(String account) {
		card.setAccount(account);
		customerinfo.setAccount(account);
		;
		deal.setAccount(account);
	}

	public String getUsername() {
		return customerinfo.getUsername();
	}

	public void setUsername(String username) {
		customerinfo.setUsername(username);
	}

	public String getSex() {
		return customerinfo.getSex();
	}

	public void setSex(String sex) {
		customerinfo.setSex(sex);
	}

	public String getPid() {
		return customerinfo.getPid();
	}

	public void setPid(String pid) {
		customerinfo.setPid(pid);
	}

	public String getPhone() {
		return customerinfo.getPhone();
	}

	public void setPhone(String phone) {
		customerinfo.setPhone(phone);
	}

	public String getAddress() {
		return customerinfo.getAddress();
	}

	public void setAddress(String address) {
		customerinfo.setAddress(address);
	}

	public String getPassword() {
		return card.getPassword();
	}

	public void setPassword(String password) {
		card.setPassword(password);
	}

	public String getCreateDate() {
		return card.getCreateDate();
	}

	public void setCreateDate(String createDate) {
		card.setCreateDate(createDate);

	}

	public int getStatus() {
		return card.getStatus();
	}

	public void setStatus(int status) {
		card.setStatus(status);
	}

	public String getBlank() {
		return card.getBlank();
	}

	public void setBlank(String blank) {
		card.setBlank(blank);

	}

	public String getAddressCard() {
		return card.getAddressCard();
	}

	public void setAddressCard(String addressCard) {
		card.setAddressCard(addressCard);
	}

	public float getBalance() {
		return card.getBalance();
	}

	public void setBalance(float balance) {
		card.setBalance(balance);
	}

	public int getCardbelongID() {
		return card.getCard();
	}

	public void setCardbelongID(int cardbelongid) {
		card.setCard(cardbelongid);
	}

	public String getSeq() {
		return deal.getSeq();
	}

	public void setSeq(String seq) {
		deal.setSeq(seq);
	}

	public float getDealMoney() {
		return deal.getDealMoney();
	}

	public void setDealMoney(float dealMoney) {
		deal.setDealMoney(dealMoney);
	}

	public java.util.Date getDealDate() {
		return deal.getDealDate();
	}

	public void setDealDate(Date dealDate) {
		deal.setDealDate(dealDate);
	}

	public String getDealType() {
		return deal.getDealType();
	}

	public void setDealType(String dealType) {
		deal.setDealType(dealType);
	}
}
