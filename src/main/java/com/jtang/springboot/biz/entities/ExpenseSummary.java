package com.jtang.springboot.biz.entities;

import jakarta.persistence.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpenseSummary {

	private Map<Account, Map<Business, Double>> summary; //account needs hash serializable etc.

	public ExpenseSummary(List<Account> accounts, List<Business> businesses) {
		summary = new HashMap<Account, Map<Business, Double>>();
		for (Account a : accounts) {
			Map<Business, Double> businessMap = new HashMap<>();
			for (Business b : businesses) {
				businessMap.put(b, 0.0);
			}
			summary.put(a, businessMap);
		}
	}

	public Map<Account, Map<Business, Double>> getSummary() {
		return summary;
	}

	public void setSummary(Map<Account, Map<Business, Double>> summary) {
		this.summary = summary;
	}

	public String toString() {
		StringBuffer buff = new StringBuffer();
		for (Account a : summary.keySet()) {
			buff.append("\n" + a.getName() + "\n");
			Map<Business, Double> m = summary.get(a);
			for (Business b : m.keySet()) {
				buff.append("\t" + b.getName() + ": " + m.get(b) + "\n");
			}
		}
		return buff.toString();
	}
}
