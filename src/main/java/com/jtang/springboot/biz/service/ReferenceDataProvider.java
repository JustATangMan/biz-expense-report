package com.jtang.springboot.biz.service;

import com.jtang.springboot.biz.entities.*;

import java.util.List;
import java.util.Map;

public interface ReferenceDataProvider {

    public List<Account> getAccounts(int taxSeasonId);

    public List<Account> getAccounts();

    public Account getAccountFromName(String name, int taxSeasonId);

    public Account getAccountFromId(int id, int taxSeasonId);

    public List<Business> getBusinesses(int taxSeasonId);

    public List<Business> getBusinesses();

    public Business getBusinessFromName(String name, int taxSeasonId);

    public Business getBusinessFromId(int id, int taxSeasonId);

    public List<Category> getCategories(int taxSeasonId);

    public Category getCategoryFromName(String name, int taxSeasonId);

    public Category getCategoryFromId(int id, int taxSeasonId);

    public List<CategoryToAccount> getCatToAcc();

    public List<CategoryToAccount> getCatToAcc(int taxSeasonId);

    public TaxSeason getTaxSeasonFromName(String name);

    public TaxSeason getTaxSeasonsFromId(int id);

    public List<Transaction> getTransactions(int taxSeasonId);

    public List<Transaction> getTransactions();

    public List<TaxSeason> getTaxSeasons();

    public List<TaxSeason> getTaxSeasons(int taxSeasonId);

    public Transaction findById(int id);

    public List<Transaction> saveTransactions(List<Transaction> rawData);

    public Transaction saveTransaction(Transaction transaction);

    public void deleteTransactions(int taxSeasonId);
}
