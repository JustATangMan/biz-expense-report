package com.jtang.springboot.biz.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="category_to_account")
public class CategoryToAccount {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cat_to_acc_id")
	private int id;
	
	@Column(name="category_id")
	private int categoryId;
	
	@Column(name="account_id")
	private int accountId;
	
	@Column(name="tax_season_id")
	private int taxSeasonId;
	
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
		return taxSeasonId;
	}
	public void setTaxSeason(int taxSeasonId) {
		this.taxSeasonId = taxSeasonId;
	}
}
