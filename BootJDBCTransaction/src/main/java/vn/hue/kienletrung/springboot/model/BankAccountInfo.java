package vn.hue.kienletrung.springboot.model;

public class BankAccountInfo {
	private long id;
	private String fullName;
	private double balance;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	

	public BankAccountInfo(long id, String fullName, double balance) {
		super();
		this.id = id;
		this.fullName = fullName;
		this.balance = balance;
	}
	

}
