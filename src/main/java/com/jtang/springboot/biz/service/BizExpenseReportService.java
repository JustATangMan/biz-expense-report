package com.jtang.springboot.biz.service;

import java.util.List;

import com.jtang.springboot.biz.controllers.TransactionResponsePayload;
import com.jtang.springboot.biz.entities.ExpenseSummaryResponse;
import com.jtang.springboot.biz.entities.Transaction;

public interface BizExpenseReportService {
	
	List<Transaction> getAllTransactions(int taxSeasonId); //repo.find, filter by tax season?
	
	List<Transaction> saveTransactions(List<Transaction> rawData, int taxSeasonId); //repo save

	TransactionResponsePayload getTransactionResponsePayload(List<Transaction> transactions);
	ExpenseSummaryResponse getSummaryTable(int taxSeasonId); //do aggregation logic return summary table

	Transaction getSingleTransactionById(int id);

	Transaction updateTransaction(Transaction transaction);

	void deleteRawData(int taxSeason);
	
}
