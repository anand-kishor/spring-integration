package com.integration;

public class Account {
	private String accountNo;
	private String accountType;
	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", accountType=" + accountType + "]";
	}
	public Account(String accountNo, String accountType) {
		super();
		this.accountNo = accountNo;
		this.accountType = accountType;
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	

}
