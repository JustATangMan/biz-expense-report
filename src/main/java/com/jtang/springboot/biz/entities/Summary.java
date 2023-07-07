package com.jtang.springboot.biz.entities;

import java.util.Map;

public class Summary { // each row in the summary table
	private Account account;
	private Map<Business, Double> businesses;
	
	public Summary (Account account, Map<Business, Double> businesses) {
		this.account = account;
		this.businesses = businesses;
	}
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Map<Business, Double> getBusinesses() {
		return businesses;
	}
	public void setBusinesses(Map<Business, Double> businesses) {
		this.businesses = businesses;
	}
}
