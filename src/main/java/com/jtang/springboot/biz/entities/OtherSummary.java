package com.jtang.springboot.biz.entities;

import java.util.Map;

public class OtherSummary extends Summary { // each row in the other summary table
	
	private Category category;

	public OtherSummary(Account account, Map<Business, Double> businesses, Category category) { // account must be other
		super(account, businesses);
		this.category = category;
	}
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
