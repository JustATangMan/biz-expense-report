package com.jtang.springboot.biz.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

//Id
//Name
//Description

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="account_id")
	private int id;
	@Column(name="account_name")
	private String name;
	@Column(name="account_description")
	private String description;
	@Column(name="tax_season")
	private int taxSeason;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTaxSeason() {
		return taxSeason;
	}
	public void setTaxSeason(int taxSeason) {
		this.taxSeason = taxSeason;
	}
}
