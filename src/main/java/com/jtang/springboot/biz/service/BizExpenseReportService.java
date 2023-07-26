package com.jtang.springboot.biz.service;

import java.util.List;

import com.jtang.springboot.biz.entities.ExpenseSummary;
import com.jtang.springboot.biz.entities.Transaction;

public interface BizExpenseReportService {
	
	public List<Transaction> getAllTransactions(int taxSeasonId); //repo.find, filter by tax season?
	
	public List<Transaction> saveTransactions(List<Transaction> rawData, int taxSeasonId); //repo save
	
	public ExpenseSummary getSummaryTable(int taxSeasonId); //do aggregation logic return summary table
	
	public void deleteRawData(int taxSeason);
	
}
