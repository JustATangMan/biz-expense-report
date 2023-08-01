package com.jtang.springboot.biz.service;

import com.jtang.springboot.biz.entities.*;

import java.util.List;
import java.util.Map;

public interface ReferenceDataProvider {

    public void init();

    public List<Account> getAccounts(int taxSeasonId);

    public List<Account> getAccounts();

    public void setAccounts(List<Account> accounts);

    public Account getAccountFromName(String name);

    public Account getAccountFromId(int id);

    public List<Business> getBusinesses(int taxSeasonId);

    public List<Business> getBusinesses();

    public void setBusinesses(List<Business> businesses);

    public Business getBusinessFromName(String name);

    public Business getBusinessFromId(int id);

    public List<Category> getCategories(int taxSeasonId);

    public List<Category> getCategories();

    public void setCategories(List<Category> categories);

    public Category getCategoryFromName(String name);

    public Category getCategoryFromId(int id);

    public Map<Category, Account> getCatToAcc();

    public void setCatToAcc(Map<Category, Account> catToAcc);

    public List<TaxSeason> getTaxSeasons(int id);

    public List<TaxSeason> getTaxSeasons();

    public void setTaxSeasons(List<TaxSeason> taxSeasons);

    public TaxSeason getTaxSeasonFromName(String name);

    public TaxSeason getTaxSeasonsFromId(int id);
    public List<Transaction> getTransactions(int taxSeasonId);

    public List<Transaction> getTransactions();

    public void setTransactions(List<Transaction> transactions);

}
