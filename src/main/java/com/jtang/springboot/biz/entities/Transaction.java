package com.jtang.springboot.biz.entities;

import java.sql.Date;

//unique ID (incremental)
//Source (BOFA, costco)
//Date
//Description (transaction description)
//Amount ($)
//Adjusted Amount
//Category (FK)
//Business Unit (FK)
//Account (FK)
//Notes (personal notes)
//Workbook id/tax season

public class Transaction {
	
	private int id;
	private String source;
	private Date date;
	private String description;
	private Double amount;
	private Double adjustedAmount;
	private int category_ID;
	private int business_ID;
	private int account_ID;
	private String notes;
	private int taxSeason;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getAdjustedAmount() {
		return adjustedAmount;
	}
	public void setAdjustedAmount(Double adjustedAmount) {
		this.adjustedAmount = adjustedAmount;
	}
	public int getCategory_ID() {
		return category_ID;
	}
	public void setCategory_ID(int category_ID) {
		this.category_ID = category_ID;
	}
	public int getBusiness_ID() {
		return business_ID;
	}
	public void setBusiness_ID(int business_ID) {
		this.business_ID = business_ID;
	}
	public int getAccount_ID() {
		return account_ID;
	}
	public void setAccount_ID(int account_ID) {
		this.account_ID = account_ID;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getTaxSeason() {
		return taxSeason;
	}
	public void setTaxSeason(int taxSeason) {
		this.taxSeason = taxSeason;
	}

}
