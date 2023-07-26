package com.jtang.springboot.biz.service;

import java.util.List;

import com.jtang.springboot.biz.entities.ExpenseSummary;
import com.jtang.springboot.biz.entities.Transaction;

public interface BizExpenseReportService {
	
	List<Transaction> getAllTransactions(int taxSeasonId); //repo.find, filter by tax season?
	
	List<Transaction> saveTransactions(List<Transaction> rawData, int taxSeasonId); //repo save
	
	ExpenseSummary getSummaryTable(int taxSeasonId); //do aggregation logic return summary table
	
	void deleteRawData(int taxSeason);
	
}
