package com.jtang.springboot.biz.service.impl;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

import jakarta.annotation.PostConstruct;

@Service
public class DefaultReferenceDataProvider implements ReferenceDataProvider {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultReferenceDataProvider.class);
	private List<Account> accounts;
	private List<Business> businesses;
	private List<Category> categories;
	private Map<Category, Account> catToAcc;
	private List<TaxSeason> taxSeasons;
	private List<Transaction> transactions;

	@Autowired
	private BizExpenseAccountRepository accRepo;
	@Autowired
	private BizExpenseBusinessRepository bizRepo;
	@Autowired
	private BizExpenseCategoryRepository catRepo;
	@Autowired
	private BizExpenseCatToAccRepository catToAccRepo;
	@Autowired
	private BizExpenseTaxSeasonRepository taxRepo;
	@Autowired
	private BizExpenseTransactionRepository transRepo;

	public BizExpenseAccountRepository getAccRepo() {
		return accRepo;
	}
	public BizExpenseBusinessRepository getBizRepo() {
		return bizRepo;
	}
	public BizExpenseCategoryRepository getCatRepo() {
		return catRepo;
	}
	public BizExpenseCatToAccRepository getCatToAccRepo() {
		return catToAccRepo;
	}
	public BizExpenseTaxSeasonRepository getTaxRepo() {
		return taxRepo;
	}
	public BizExpenseTransactionRepository getTransRepo() {
		return transRepo;
	}

	@PostConstruct
	@Override
	public void init() { // only grab tax season
		//TODO: refresh cached lists
		accounts = accRepo.findAll();
		businesses = bizRepo.findAll();
		categories = catRepo.findAll();
		taxSeasons = taxRepo.findAll();
		transactions = transRepo.findAll();
		catToAcc = new HashMap<>();
		List<CategoryToAccount> CTA = catToAccRepo.findAll();
		for (CategoryToAccount cta : CTA) {
			catToAcc.put(getCategoryFromId(cta.getCategory_id()), getAccountFromId(cta.getAccount_id()));
		}
		LOGGER.info("********************ReferenceDataProvider init done **********************************");
		LOGGER.info("Account size: {}", accounts.size());
	}

	@Override
	public List<Account> getAccounts(int taxSeasonId) {
		return accounts.stream().filter(a -> a.getTaxSeason() == taxSeasonId).toList(); // 330 fold
	}

	@Override
	public List<Account> getAccounts() {
		return accounts;
	}

	@Override
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public Account getAccountFromName(String name) {
		for (Account account : accounts) {
			if (account.getName().equalsIgnoreCase(name)) {
				return account;
			}
		}
		return null;
	}

	@Override
	public Account getAccountFromId(int id) {
		for (Account account : accounts) {
			if (account.getId() == id) {
				return account;
			}
		}
		return null;
	}

	@Override
	public List<Business> getBusinesses(int taxSeasonId) {
		return businesses.stream().filter(b -> b.getTaxSeason() == taxSeasonId).toList();
	}

	@Override
	public List<Business> getBusinesses() {
		return businesses;
	}

	@Override
	public void setBusinesses(List<Business> businesses) {
		this.businesses = businesses;
	}

	@Override
	public Business getBusinessFromName(String name) {
		for (Business business : businesses) {
			if (business.getName().equalsIgnoreCase(name)) {
				return business;
			}
		}
		return null;
	}

	@Override
	public Business getBusinessFromId(int id) {
		var businessList = businesses.stream().filter(b -> b.getId() == id).toList();
		return businessList.size() > 0 ? businessList.get(0) : null;
	}

	@Override
	public List<Category> getCategories(int taxSeasonId) {
		return categories.stream().filter(c -> c.getTaxSeason() == taxSeasonId).toList();
	}

	@Override
	public List<Category> getCategories() {
		return categories;
	}

	@Override
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	@Override
	public Category getCategoryFromName(String name) {
		System.out.println("input name: " + name);
		for (Category category : categories) {
			System.out.println("category name: " + category.getName());
			if (category.getName().equalsIgnoreCase(name)) {
				return category;
			}
		}
		return null;
	}

	@Override
	public Category getCategoryFromId(int id) {
		for (Category category : categories) {
			if (category.getId() == id) {
				return category;
			}
		}
		return null;
	}

	@Override
	public Map<Category, Account> getCatToAcc() {
		return catToAcc;
	}

	@Override
	public void setCatToAcc(Map<Category, Account> catToAcc) {
		this.catToAcc = catToAcc;
	}

	@Override
	public List<TaxSeason> getTaxSeasons(int taxSeasonId) {
		return taxSeasons.stream().filter(t -> t.getTaxSeasonId() == taxSeasonId).toList();
	}

	@Override
	public List<TaxSeason> getTaxSeasons() {
		return taxSeasons;
	}

	@Override
	public void setTaxSeasons(List<TaxSeason> taxSeasons) {
		this.taxSeasons = taxSeasons;
	}

	@Override
	public TaxSeason getTaxSeasonFromName(String name) {
		for (TaxSeason tax : taxSeasons) {
			if (tax.getName().equalsIgnoreCase(name)) {
				return tax;
			}
		}
		return null;
	}

	@Override
	public TaxSeason getTaxSeasonsFromId(int id) {
		for (TaxSeason tax : taxSeasons) {
			if (tax.getTaxSeasonId() == id) {
				return tax;
			}
		}
		return null;
	}

	@Override
	public List<Transaction> getTransactions(int taxSeasonId) {
		return transactions.stream().filter(t -> t.getTaxSeason() == taxSeasonId).toList();
	}

	@Override
	public List<Transaction> getTransactions() {
		return transactions;
	}

	@Override
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
