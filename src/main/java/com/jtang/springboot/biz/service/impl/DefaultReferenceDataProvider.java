package com.jtang.springboot.biz.service.impl;

import java.util.List;

import com.jtang.springboot.biz.service.ReferenceDataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtang.springboot.biz.entities.Account;
import com.jtang.springboot.biz.entities.Business;
import com.jtang.springboot.biz.entities.Category;
import com.jtang.springboot.biz.entities.CategoryToAccount;
import com.jtang.springboot.biz.entities.TaxSeason;
import com.jtang.springboot.biz.entities.Transaction;
import com.jtang.springboot.biz.repo.BizExpenseAccountRepository;
import com.jtang.springboot.biz.repo.BizExpenseBusinessRepository;
import com.jtang.springboot.biz.repo.BizExpenseCatToAccRepository;
import com.jtang.springboot.biz.repo.BizExpenseCategoryRepository;
import com.jtang.springboot.biz.repo.BizExpenseTaxSeasonRepository;
import com.jtang.springboot.biz.repo.BizExpenseTransactionRepository;

@Service
public class DefaultReferenceDataProvider implements ReferenceDataProvider {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultReferenceDataProvider.class);

//	@Autowired
	private BizExpenseAccountRepository accRepo;
//	@Autowired
	private BizExpenseBusinessRepository bizRepo;
//	@Autowired
	private BizExpenseCategoryRepository catRepo;
//	@Autowired
	private BizExpenseCatToAccRepository catToAccRepo;
//	@Autowired
	private BizExpenseTaxSeasonRepository taxRepo;
//	@Autowired
	private BizExpenseTransactionRepository transRepo;

	@Autowired
	public DefaultReferenceDataProvider(BizExpenseAccountRepository accRepo, BizExpenseBusinessRepository bizRepo, BizExpenseCategoryRepository catRepo, BizExpenseCatToAccRepository catToAccRepo, BizExpenseTaxSeasonRepository taxRepo, BizExpenseTransactionRepository transRepo) {
		this.accRepo = accRepo;
		this.bizRepo = bizRepo;
		this.catRepo = catRepo;
		this.catToAccRepo = catToAccRepo;
		this.taxRepo = taxRepo;
		this.transRepo = transRepo;
		LOGGER.info("************** accRepo is null? {} {} {} {} {} {} ", accRepo == null, bizRepo == null
		, catRepo == null, catToAccRepo == null, taxRepo == null, transRepo == null);
	}

	@Override
	public List<Account> getAccounts(int taxSeasonId) {
		return accRepo.findByTaxSeasonId(taxSeasonId);
	}

	@Override
	public List<Account> getAccounts() {
		return accRepo.findAll();
	}

	@Override
	public Account getAccountFromName(String name, int taxSeasonId) {
		var accountList = getAccounts(taxSeasonId).stream()
				.filter(a -> a.getName().equalsIgnoreCase(name)).toList();
		return accountList.size() > 0 ? accountList.get(0) : null;
	}

	@Override
	public Account getAccountFromId(int id, int taxSeasonId) {
		var accountList = getAccounts(taxSeasonId).stream()
				.filter(a -> a.getId() == id).toList();
		return accountList.size() > 0 ? accountList.get(0) : null;
	}

	@Override
	public List<Business> getBusinesses(int taxSeasonId) {
		return bizRepo.findByTaxSeasonId(taxSeasonId);
	}

	@Override
	public List<Business> getBusinesses() {
		return bizRepo.findAll();
	}

	@Override
	public Business getBusinessFromName(String name, int taxSeasonId) {
		var businessList = getBusinesses(taxSeasonId).stream()
				.filter(b -> b.getName().equalsIgnoreCase(name)).toList();
		return businessList.size() > 0 ? businessList.get(0) : null;
	}

	@Override
	public Business getBusinessFromId(int id, int taxSeasonId) {
		var businessList = getBusinesses(taxSeasonId).stream()
				.filter(b -> b.getId() == id).toList();
		return businessList.size() > 0 ? businessList.get(0) : null;
	}

	@Override
	public List<Category> getCategories(int taxSeasonId) {
		return catRepo.findByTaxSeasonId(taxSeasonId);
	}

	@Override
	public Category getCategoryFromName(String name, int taxSeasonId) {
		var categoryList = getCategories(taxSeasonId).stream()
				.filter(c -> c.getName().equalsIgnoreCase(name)).toList();
		return categoryList.size() > 0 ? categoryList.get(0) : null;
	}

	@Override
	public Category getCategoryFromId(int id, int taxSeasonId) {
		var categoryList = getCategories(taxSeasonId).stream()
				.filter(c -> c.getId() == id).toList();
		return categoryList.size() > 0 ? categoryList.get(0) : null;
	}

	@Override
	public List<CategoryToAccount> getCatToAcc() {
		return catToAccRepo.findAll();
	}

	@Override
	public List<CategoryToAccount> getCatToAcc(int taxSeasonId) {
		return catToAccRepo.findByTaxSeasonId(taxSeasonId);
	}

	@Override
	public List<TaxSeason> getTaxSeasons() {
		return taxRepo.findAll();
	}

	@Override
	public List<TaxSeason> getTaxSeasons(int taxSeasonId) {
		return taxRepo.findByTaxSeasonId(taxSeasonId);
	}

	@Override
	public Transaction findById(int id, int taxSeasonId) {
		var transactionList = getTransactions(taxSeasonId).stream().filter(t -> t.getId() == id).toList();
		return transactionList.size() > 0 ? transactionList.get(0) : null;
	}

	@Override
	public List<Transaction> saveTransactions(List<Transaction> rawData) {
		return transRepo.saveAll(rawData);
	}

	@Override
	public void deleteTransactions(int taxSeasonId) {
		transRepo.deleteByTaxSeasonId(taxSeasonId);
	}

	public void deleteTransactions() {
		transRepo.deleteAll();
	}

	@Override
	public TaxSeason getTaxSeasonFromName(String name) {
		var taxList = getTaxSeasons().stream()
				.filter(t -> t.getName().equalsIgnoreCase(name)).toList();
		return taxList.size() > 0 ? taxList.get(0) : null;
	}

	@Override
	public TaxSeason getTaxSeasonsFromId(int id) {
		var taxList = getTaxSeasons().stream()
				.filter(t -> t.getTaxSeasonId() == id).toList();
		return taxList.size() > 0 ? taxList.get(0) : null;
	}

	@Override
	public List<Transaction> getTransactions(int taxSeasonId) {
		return transRepo.findByTaxSeasonId(taxSeasonId);
	}

	@Override
	public List<Transaction> getTransactions() {
		return transRepo.findAll();
	}

}
