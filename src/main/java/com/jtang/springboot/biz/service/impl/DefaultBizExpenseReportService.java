package com.jtang.springboot.biz.service.impl;

import com.jtang.springboot.biz.entities.ExpenseSummary;
import com.jtang.springboot.biz.entities.Transaction;
import com.jtang.springboot.biz.repo.BizExpenseTransactionRepository;
import com.jtang.springboot.biz.service.BizExpenseReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DefaultBizExpenseReportService implements BizExpenseReportService {

    @Autowired
    private BizExpenseTransactionRepository transRepo;

    @Override
    public List<Transaction> getAllTransactions(int taxSeasonId) {
        return transRepo.findByTaxSeasonId(taxSeasonId);
    } // run test

    @Override
    public List<Transaction> saveTransactions(List<Transaction> rawData, int taxSeasonId) {
        for (Transaction trans : rawData) {
            trans.setTaxSeason(taxSeasonId);
        }
        return transRepo.saveAll(rawData);
    } // test

    @Override
    public ExpenseSummary getSummaryTable(int taxSeasonId) {
        return null;
    }

    @Override
    public void deleteRawData(int taxSeason) {

    }
}
