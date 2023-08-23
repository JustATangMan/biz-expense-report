package com.jtang.springboot.biz.service.impl;

import com.jtang.springboot.biz.controllers.TransactionResponsePayload;
import com.jtang.springboot.biz.entities.*;
import com.jtang.springboot.biz.service.BizExpenseReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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
//        LOGGER.info("****** rdp is null? {}", rdp == null);
    }

    @Override
    public List<Transaction> getAllTransactions(int taxSeasonId) {
        return rdp.getTransactions(taxSeasonId);
    } // run test

    @Override
    public List<Transaction> saveTransactions(List<Transaction> rawData, int taxSeasonId) {
        for (Transaction trans : rawData) {
            trans.setTaxSeasonId(taxSeasonId);
        }
        List<Transaction> transactions = rdp.saveTransactions(rawData);
        return transactions;
    } // test

    @Override
    public TransactionResponsePayload getTransactionResponsePayload(List<Transaction> transactions) {
        Map<Integer, String> accIdToName = new HashMap<>();
        Map<Integer, String> bizIdToName = new HashMap<>();
        Map<Integer, String> catIdToName = new HashMap<>();
        List<TransactionResponse> responses = transactions.stream().map(t ->
                {
                    if (accIdToName.get(t.getAccountId()) == null) {
                        accIdToName.put(t.getAccountId(), rdp.getAccountFromId(t.getAccountId(), t.getTaxSeasonId()).getName());
                    }
                    if (bizIdToName.get(t.getBusinessId()) == null) {
                        bizIdToName.put(t.getBusinessId(), rdp.getBusinessFromId(t.getBusinessId(), t.getTaxSeasonId()).getName());
                    }
                    if (catIdToName.get(t.getCategoryId()) == null) {
                        catIdToName.put(t.getCategoryId(), rdp.getCategoryFromId(t.getCategoryId(), t.getTaxSeasonId()).getName());
                    }
                    return new TransactionResponse(t,
                            accIdToName.get(t.getAccountId()),
                            bizIdToName.get(t.getBusinessId()),
                            catIdToName.get(t.getCategoryId()));
                }).toList();
        // cache fetched names in map
        // transactions -> transactionDisplays
        return new TransactionResponsePayload(responses, accIdToName, bizIdToName, catIdToName);
    }

    @Override
    public ExpenseSummaryResponse getSummaryTable(int taxSeasonId) {
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
                if (trans.getAppliedAmount() == null || trans.getAppliedAmount() == 0.0) {
                    continue;
                }
                Account account = accountMap.get(trans.getAccountId());
                Business business = businessMap.get(trans.getBusinessId());
                Double newAmount = summaryMap.get(account).get(business) + trans.getAppliedAmount();
                summaryMap.get(account).put(business, newAmount);
            }
            summary.setSummary(summaryMap);
            ExpenseSummaryResponse esr = new ExpenseSummaryResponse();
            return esr.from(summary);
        }
    }

    @Override
    public Transaction getSingleTransactionById(int id) {
        return rdp.findById(id);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        if (rdp.findById(transaction.getId()) == null) {
            throw new RuntimeException("Invalid transaction id: " + transaction.getId());
        }
        return rdp.saveTransaction(transaction);
    }

    @Override
    public void deleteRawData(int taxSeason) {
        rdp.deleteTransactions(taxSeason);
    }
}
