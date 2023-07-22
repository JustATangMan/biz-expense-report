package com.jtang.springboot.biz.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtang.springboot.biz.entities.Account;
import com.jtang.springboot.biz.entities.Business;
import com.jtang.springboot.biz.entities.Category;
import com.jtang.springboot.biz.entities.CategoryToAccount;
import com.jtang.springboot.biz.entities.TaxSeasons;
import com.jtang.springboot.biz.entities.Transaction;
import com.jtang.springboot.biz.repo.BizExpenseAccountRepository;
import com.jtang.springboot.biz.repo.BizExpenseBusinessRepository;
import com.jtang.springboot.biz.repo.BizExpenseCatToAccRepository;
import com.jtang.springboot.biz.repo.BizExpenseCategoryRepository;
import com.jtang.springboot.biz.repo.BizExpenseTaxSeasonRepository;
import com.jtang.springboot.biz.repo.BizExpenseTransactionRepository;

import jakarta.annotation.PostConstruct;

@Service
public class ReferenceDataProvider {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceDataProvider.class);

	private List<Account> accounts;
	private List<Business> businesses;
	private List<Category> categories;
	private Map<Category, Account> catToAcc;
	private List<TaxSeasons> taxSeasons;
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
	
	@PostConstruct
	public void init() {
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
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Account getAccountFromName(String name) {
		for (Account account : accounts) {
			if (account.getName().equalsIgnoreCase(name)) {
				return account;
			}
		}
		return null;
	}
	
	public Account getAccountFromId(int id) {
		for (Account account : accounts) {
			if (account.getId() == id) {
				return account;
			}
		}
		return null;
	}
	
	public List<Business> getBusinesses() {
		return businesses;
	}

	public void setBusinesses(List<Business> businesses) {
		this.businesses = businesses;
	}

	public Business getBusinessFromName(String name) {
		for (Business business : businesses) {
			if (business.getName().equalsIgnoreCase(name)) {
				return business;
			}
		}
		return null;
	}
	
	public Business getBusinessFromId(int id) {
		for (Business business : businesses) {
			if (business.getId() == id) {
				return business;
			}
		}
		return null;
	}
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

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
	
	public Category getCategoryFromId(int id) {
		for (Category category : categories) {
			if (category.getId() == id) {
				return category;
			}
		}
		return null;
	}
	
	public Map<Category, Account> getCatToAcc() {
		return catToAcc;
	}

	public void setCatToAcc(Map<Category, Account> catToAcc) {
		this.catToAcc = catToAcc;
	}

	public List<TaxSeasons> getTaxSeasons() {
		return taxSeasons;
	}

	public void setTaxSeasons(List<TaxSeasons> taxSeasons) {
		this.taxSeasons = taxSeasons;
	}
	
	public TaxSeasons getTaxSeasonFromName(String name) {
		for (TaxSeasons tax : taxSeasons) {
			if (tax.getName().equalsIgnoreCase(name)) {
				return tax;
			}
		}
		return null;
	}
	
	public TaxSeasons getTaxSeasonsFromId(int id) {
		for (TaxSeasons tax : taxSeasons) {
			if (tax.getTaxSeasonId() == id) {
				return tax;
			}
		}
		return null;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
