package com.jtang.springboot.biz;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import com.jtang.springboot.biz.entities.*;
import com.jtang.springboot.biz.repo.*;
import com.jtang.springboot.biz.service.BizExpenseReportService;
import com.jtang.springboot.biz.service.ReferenceDataProvider;
import com.jtang.springboot.biz.service.impl.DefaultReferenceDataProvider;
import com.jtang.springboot.biz.service.TestDataGenerator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jtang.springboot.biz.service.FileProcessorService;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReferenceDataProviderTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultReferenceDataProvider.class);
	@MockBean
	private BizExpenseAccountRepository accRepo;
	@MockBean
	private BizExpenseBusinessRepository bizRepo;
	@MockBean
	private BizExpenseCategoryRepository catRepo;
	@MockBean
	private BizExpenseCatToAccRepository catToAccRepo;
	@MockBean
	private BizExpenseTaxSeasonRepository taxRepo;
	@MockBean
	private BizExpenseTransactionRepository transRepo;
	@Autowired
	private ReferenceDataProvider rdp;
	@Autowired
	private FileProcessorService fileProcessorService;
	@Autowired
	private BizExpenseReportService bizExpenseReportService;

	private TestDataGenerator tdg = new TestDataGenerator();

	List<Account> accounts = tdg.createMockAccounts();
	List<Business> businesses = tdg.createMockBusinesses();
	List<Category> categories = tdg.createMockCategories();
	List<Transaction> transactions;
	{
		try {
			transactions = tdg.createMockTransactions(false, 1);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	List<TaxSeason> taxSeasons = tdg.createMockTaxSeasons();

	@Test
	void testRDPAccount() {
		when(accRepo.findAll()).thenReturn(accounts);
		when(accRepo.findByTaxSeasonId(1)).thenReturn(accounts);
		assertEquals(rdp.getAccountFromId(16, 1).getName(), "Utilities");
		assertEquals(rdp.getAccountFromName("Meal - C", 1).getDescription(), "account9");
	}

	@Test
	void testRDPBusiness() {
		when(bizRepo.findAll()).thenReturn(businesses);
		when(bizRepo.findByTaxSeasonId(1)).thenReturn(businesses);
		assertEquals(rdp.getBusinessFromId(1, 1).getName(), "Financial Service");
		assertEquals(rdp.getBusinessFromName("153 Orange", 1).getDescription(), "more money");
		assertNull(rdp.getBusinessFromId(5, 1));
	}

	@Test
	void testRDPCategory() {
		when(catRepo.findAll()).thenReturn(categories);
		when(catRepo.findByTaxSeasonId(1)).thenReturn(categories);
		assertEquals(rdp.getCategoryFromId(1, 1).getName(), "Advertising");
		assertEquals(rdp.getCategoryFromName("Auto and travel - C & E", 1).getDescription(), "cat2");
	}


	//save one record
}
