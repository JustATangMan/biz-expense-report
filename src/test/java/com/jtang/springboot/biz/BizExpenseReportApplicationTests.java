package com.jtang.springboot.biz;

import java.io.File;
import java.util.List;
import com.jtang.springboot.biz.entities.Account;
import com.jtang.springboot.biz.entities.Business;
import com.jtang.springboot.biz.entities.Category;
import com.jtang.springboot.biz.entities.Transaction;
import com.jtang.springboot.biz.repo.*;
import com.jtang.springboot.biz.service.ReferenceDataProvider;
import com.jtang.springboot.biz.service.TestDataGenerator;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jtang.springboot.biz.service.FileProcessorService;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class BizExpenseReportApplicationTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceDataProvider.class);

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
	private FileProcessorService service;

	List<Account> accounts = TestDataGenerator.createMockAccounts();
	List<Business> businesses = TestDataGenerator.createMockBusinesses();
	List<Category> categories = TestDataGenerator.createMockCategories();
	File file = new File("src/test/resources/sheet.xlsx");

	@Test
	void testExcel() {
		when(accRepo.findAll()).thenReturn(accounts);
		when(bizRepo.findAll()).thenReturn(businesses);
		when(catRepo.findAll()).thenReturn(categories);
		rdp.init();

		List<Transaction> transactions = service.readTransactions(file);
		System.out.println();
		LOGGER.info("Transaction size: {}", transactions.size());
		assertEquals(transactions.size(), 2);
	}

	@Test
	void testRDPAccount() {
		when(accRepo.findAll()).thenReturn(accounts);
		rdp.init();
		assertEquals(rdp.getAccountFromId(1).getName(), "Utilities");
		assertEquals(rdp.getAccountFromName("Account2").getDescription(), "he he he haw");
	}

	@Test
	void testRDPBusiness() {
		when(bizRepo.findAll()).thenReturn(businesses);
		rdp.init();
		assertEquals(rdp.getBusinessFromId(1).getName(), "Financial Service");
		assertEquals(rdp.getBusinessFromName("153 Orange").getDescription(), "more money");
		assertEquals(rdp.getBusinessFromId(4), null);
	}

	@Test
	void testRDPCategory() {
		when(catRepo.findAll()).thenReturn(categories);
		rdp.init();
		assertEquals(rdp.getCategoryFromId(1).getName(), "Utilities");
		assertEquals(rdp.getCategoryFromName("Category2").getDescription(), "cat2");
	}

	//save one record
}
