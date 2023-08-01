package com.jtang.springboot.biz.service.impl;

import com.jtang.springboot.biz.entities.*;
import com.jtang.springboot.biz.service.BizExpenseReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DefaultBizExpenseReportService implements BizExpenseReportService {

    @Autowired
    private DefaultReferenceDataProvider rdp;

    @Override
    public List<Transaction> getAllTransactions(int taxSeasonId) {
        return rdp.getTransactions(taxSeasonId);
    } // run test

    @Override
    public List<Transaction> saveTransactions(List<Transaction> rawData, int taxSeasonId) {
        for (Transaction trans : rawData) {
            trans.setTaxSeason(taxSeasonId);
        }
        List<Transaction> transactions = rdp.getTransRepo().saveAll(rawData);
        rdp.setTransactions(rdp.getTransRepo().findAll());
        return transactions;
    } // test

    @Override
    public ExpenseSummary getSummaryTable(int taxSeasonId) {
        List<TaxSeason> taxes = rdp.getTaxSeasons(taxSeasonId);
        if (taxSeasonId < 0 || taxes.isEmpty()) {
            throw new RuntimeException("Invalid tax id");
        } else {
            ExpenseSummary summary = new ExpenseSummary(rdp.getAccounts(taxSeasonId),
                    rdp.getBusinesses(taxSeasonId));
            List<Transaction> transactions = rdp.getTransactions(taxSeasonId);
            for (Transaction trans : transactions) {
                Account account = rdp.getAccountFromId(trans.getAccountId());
                Business business = rdp.getBusinessFromId(trans.getBusinessId());
                Double newAmount = summary.getSummary().get(account).get(business) + trans.getAppliedAmount();
                summary.getSummary().get(account).put(business, newAmount);
            }
            return summary;
        }
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        if (rdp.getTransRepo().findById(transaction.getId()).isEmpty()) {
            throw new RuntimeException("Invalid transaction id: " + transaction.getId());
        }
        Transaction trans = rdp.getTransRepo().save(transaction);
        rdp.setTransactions(rdp.getTransRepo().findAll());
        return trans;
    }

    @Override
    public void deleteRawData(int taxSeason) {
        rdp.getTransRepo().deleteByTaxSeasonId(taxSeason);
        rdp.setTransactions(rdp.getTransRepo().findAll());
    }
}
