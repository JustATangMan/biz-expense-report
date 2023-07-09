package com.jtang.springboot.biz.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CategoryToAccount {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cat_to_acc_id")
	private int id;
	@Column(name="category_id")
	private int categoryId;
	@Column(name="account_id")
	private int accountId;
	@Column(name="tax_season")
	private int taxSeason;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCategory_id() {
		return categoryId;
	}
	public void setCategory_id(int category_id) {
		this.categoryId = category_id;
	}
	public int getAccount_id() {
		return accountId;
	}
	public void setAccount_id(int account_id) {
		this.accountId = account_id;
	}
	public int getTaxSeason() {
		return taxSeason;
	}
	public void setTaxSeason(int taxSeason) {
		this.taxSeason = taxSeason;
	}
}
