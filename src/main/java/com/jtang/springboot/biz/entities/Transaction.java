package com.jtang.springboot.biz.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

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

@Entity
@Table(name="transactions")
public class Transaction { // each row in the rawdata table
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String source;
	private Date date;
	private String description;
	private Double amount;
	
	@Column(name="adjusted_amount")
	private Double adjustedAmount;
	
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="business_id")
	private int businessId;
	
	@Column(name="account_id")
	private int accountId;
	
	private String notes;
	
	@Column(name="tax_season_id")
	private int taxSeasonId;
	
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
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getBusinessId() {
		return businessId;
	}
	public void setBusinessId(int businessId) {
		this.businessId = businessId;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getTaxSeason() {
		return taxSeasonId;
	}
	public void setTaxSeason(int taxSeason) {
		this.taxSeasonId = taxSeason;
	}
	
}
