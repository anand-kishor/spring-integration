package com.integration;

public class AccountHolder {
	private String name;
	private String address;
	public AccountHolder(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	public AccountHolder() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AccountHolder [name=" + name + ", address=" + address + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

}
