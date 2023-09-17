package com.jtang.springboot.biz;

import com.jtang.springboot.biz.entities.*;
import com.jtang.springboot.biz.repo.*;
import com.jtang.springboot.biz.service.TestDataGenerator;
import com.jtang.springboot.biz.service.impl.DefaultBizExpenseReportService;
import com.jtang.springboot.biz.service.impl.DefaultReferenceDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//TODO: cleanup imports
@ExtendWith(MockitoExtension.class)
public class BizServiceTests {
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

    private DefaultBizExpenseReportService bizExpenseReportService;

    private TestDataGenerator tdg = new TestDataGenerator();
    List<Account> accounts = tdg.createMockAccounts();
    List<Business> businesses = tdg.createMockBusinesses();
//    List<Category> categories = tdg.createMockCategories();
    List<TaxSeason> taxSeasons = tdg.createMockTaxSeasons();

    @BeforeEach
    void mockRepos() {
        bizExpenseReportService = new DefaultBizExpenseReportService(rdp);
        when(accRepo.findByTaxSeasonId(1)).thenReturn(accounts);
        when(bizRepo.findByTaxSeasonId(1)).thenReturn(businesses);
//        when(catRepo.findByTaxSeasonId(1)).thenReturn(categories);
        when(taxRepo.findByTaxSeasonId(1)).thenReturn(taxSeasons);
    }
    @Test
    void testSummary() {
        List<Transaction> transactions;
        {
            try {
                transactions = tdg.createMockTransactions(false, 1);
                when(transRepo.findByTaxSeasonId(1)).thenReturn(transactions);
                ExpenseSummaryResponse summary = bizExpenseReportService.getSummaryTable(1);
                assertEquals(summary.getSummary().keySet().size(), rdp.getAccounts(1).size());
                assertEquals(summary.getSummary().get("Repairs").
                        get("Business 2"), 2200.0);
                assertEquals(summary.getSummary().get("Utilities").
                        get("Business 4"), 139.03);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    void testMissingAppliedAmountSummary() {
        List<Transaction> transactions;
        {
            try {
                transactions = tdg.createBadMockTransactions(false, 1);
                when(transRepo.findByTaxSeasonId(1)).thenReturn(transactions);
                ExpenseSummaryResponse summary = bizExpenseReportService.getSummaryTable(1);
                assertEquals(summary.getSummary().keySet().size(), rdp.getAccounts(1).size());
                assertEquals(summary.getSummary().get("Utilities").
                        get("Business 4"), 26.03);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
