package com.jtang.springboot.biz.entities;

import java.util.Map;

public class ExpenseSummary {

	private Map<Account, Map<Business, Double>> summary; //account needs hash serializable etc.

	public Map<Account, Map<Business, Double>> getSummary() {
		return summary;
	}

	public void setSummary(Map<Account, Map<Business, Double>> summary) {
		this.summary = summary;
	}
}
