package com.jtang.springboot.biz.service.impl;

import com.jtang.springboot.biz.entities.*;
import com.jtang.springboot.biz.service.BizExpenseReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DefaultBizExpenseReportService implements BizExpenseReportService {

    //    @Autowired
    private DefaultReferenceDataProvider rdp;

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultBizExpenseReportService.class);

    public DefaultBizExpenseReportService(DefaultReferenceDataProvider rdp) {
        this.rdp = rdp;
        LOGGER.info("****** rdp is null? {}", rdp == null);
    }

    @Override
    public List<Transaction> getAllTransactions(int taxSeasonId) {
        return rdp.getTransactions(taxSeasonId);
    } // run test

    @Override
    public List<Transaction> saveTransactions(List<Transaction> rawData, int taxSeasonId) {
        for (Transaction trans : rawData) {
            trans.setTaxSeason(taxSeasonId);
        }
        List<Transaction> transactions = rdp.saveTransactions(rawData);
        return transactions;
    } // test

    @Override
    public ExpenseSummary getSummaryTable(int taxSeasonId) {
        // get given tax season; validate tax season
        List<TaxSeason> taxes = rdp.getTaxSeasons(taxSeasonId);
        if (taxSeasonId < 0 || taxes.isEmpty()) {
            throw new RuntimeException("Invalid tax id");
        } else {
            // pulling from repos and initializing expensesummary map of maps
            List<Account> accounts = rdp.getAccounts(taxSeasonId);
            List<Business> businesses = rdp.getBusinesses(taxSeasonId);
            List<Transaction> transactions = rdp.getTransactions(taxSeasonId);
            ExpenseSummary summary = new ExpenseSummary(accounts, businesses);
            // creating maps to lookup later (saves time instead of stream filtering each transaction)
            Map<Integer, Account> accountMap = accounts.stream().
                    collect(Collectors.toMap(account -> account.getId(), account -> account));
            Map<Integer, Business> businessMap = businesses.stream().
                    collect(Collectors.toMap(business -> business.getId(), business -> business));
            Map<Account, Map<Business, Double>> summaryMap = summary.getSummary();
            // add each transaction's applied amount to correct account/business
            for (Transaction trans : transactions) {
                Account account = accountMap.get(trans.getAccountId());
                Business business = businessMap.get(trans.getBusinessId());
                Double newAmount = summaryMap.get(account).get(business) + trans.getAppliedAmount();
                summaryMap.get(account).put(business, newAmount);
            }
            summary.setSummary(summaryMap);
            return summary;
        }
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        if (rdp.findById(transaction.getId(), transaction.getTaxSeason()) == null) {
            throw new RuntimeException("Invalid transaction id: " + transaction.getId());
        }
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);
        return rdp.saveTransactions(transactions).get(0);
    }

    @Override
    public void deleteRawData(int taxSeason) {
        rdp.deleteTransactions(taxSeason);
    }
}
