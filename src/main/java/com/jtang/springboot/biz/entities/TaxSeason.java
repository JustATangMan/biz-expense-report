package com.jtang.springboot.biz.entities;

import java.util.Map;

public class TaxSeason {
	
	private int taxSeason;
	private Map<Integer, Account> accounts; //accountid -> accountname
	private Map<Integer, Business> businesses; //businessid -> businessname
	private Map<Integer, Category> categories; //categoryid -> category
	private Map<Integer, Transaction> rawData; //transactionid -> transaction
	private Map<Category, Account> catToAcc;
	
	public int getTaxSeason() {
		return taxSeason;
	}
	public void setTaxSeason(int taxSeason) {
		this.taxSeason = taxSeason;
	}
	public Map<Integer, Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(Map<Integer, Account> accounts) {
		this.accounts = accounts;
	}
	public Map<Integer, Business> getBusinesses() {
		return businesses;
	}
	public void setBusinesses(Map<Integer, Business> businesses) {
		this.businesses = businesses;
	}
	public Map<Integer, Category> getCategories() {
		return categories;
	}
	public void setCategories(Map<Integer, Category> categories) {
		this.categories = categories;
	}
	public Map<Integer, Transaction> getRawData() {
		return rawData;
	}
	public void setRawData(Map<Integer, Transaction> rawData) {
		this.rawData = rawData;
	}
	public Map<Category, Account> getCatToAcc() {
		return catToAcc;
	}
	public void setCatToAcc(Map<Category, Account> catToAcc) {
		this.catToAcc = catToAcc;
	}
}
