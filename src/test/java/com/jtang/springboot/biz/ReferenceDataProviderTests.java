package com.jtang.springboot.biz;

import java.text.ParseException;
import java.util.List;

import com.jtang.springboot.biz.entities.*;
import com.jtang.springboot.biz.repo.*;
import com.jtang.springboot.biz.service.BizExpenseReportService;
import com.jtang.springboot.biz.service.impl.DefaultBizExpenseReportService;
import com.jtang.springboot.biz.service.impl.DefaultFileProcessorService;
import com.jtang.springboot.biz.service.impl.DefaultReferenceDataProvider;
import com.jtang.springboot.biz.service.TestDataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jtang.springboot.biz.service.FileProcessorService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReferenceDataProviderTests {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultReferenceDataProvider.class);
	@Mock
	private BizExpenseAccountRepository accRepo;
	@Mock
	private BizExpenseBusinessRepository bizRepo;
	@Mock
	private BizExpenseCategoryRepository catRepo;
	@Mock
	private BizExpenseCatToAccRepository catToAccRepo;
	@Mock
	private BizExpenseTaxSeasonRepository taxRepo;
	@Mock
	private BizExpenseTransactionRepository transRepo;
	@InjectMocks
	private DefaultReferenceDataProvider rdp;

	private FileProcessorService fileProcessorService;
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

	@BeforeEach
	void mockRepos() {
		fileProcessorService = new DefaultFileProcessorService(rdp);
		bizExpenseReportService = new DefaultBizExpenseReportService(rdp);
	}

	@Test
	void testRDPAccount() {
		when(accRepo.findByTaxSeasonId(1)).thenReturn(accounts);
		assertEquals(rdp.getAccountFromId(16, 1).getName(), "Utilities");
		assertEquals(rdp.getAccountFromName("Meal - C", 1).getDescription(), "account9");
	}

	@Test
	void testRDPBusiness() {
		when(bizRepo.findByTaxSeasonId(1)).thenReturn(businesses);
		assertEquals(rdp.getBusinessFromId(1, 1).getName(), "Business 1");
		assertEquals(rdp.getBusinessFromName("Business 2", 1).getDescription(), "more money");
		assertNull(rdp.getBusinessFromId(5, 1));
	}

	@Test
	void testRDPCategory() {
		when(catRepo.findByTaxSeasonId(1)).thenReturn(categories);
		assertEquals(rdp.getCategoryFromId(1, 1).getName(), "Advertising");
		assertEquals(rdp.getCategoryFromName("Auto and travel - C & E", 1).getDescription(), "cat2");
	}


	//save one record
}
