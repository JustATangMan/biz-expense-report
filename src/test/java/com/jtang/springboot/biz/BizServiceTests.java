package com.jtang.springboot.biz;

import com.jtang.springboot.biz.entities.*;
import com.jtang.springboot.biz.repo.*;
import com.jtang.springboot.biz.service.BizExpenseReportService;
import com.jtang.springboot.biz.service.ReferenceDataProvider;
import com.jtang.springboot.biz.service.TestDataGenerator;
import com.jtang.springboot.biz.service.impl.DefaultBizExpenseReportService;
import com.jtang.springboot.biz.service.impl.DefaultReferenceDataProvider;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//TODO: remove springboottest, try @ExtendWith(MockitoExtension.class), mock repos, use
// new call + constructor injection in @BeforeEach test (do data mocks "when(repo)" etc.)
// experiment with @InjectMocks on RDP
// then use new call to pass RDP into biz service
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
                ExpenseSummary summary = bizExpenseReportService.getSummaryTable(1);
                System.out.println(summary);
                assertEquals(summary.getSummary().keySet().size(), rdp.getAccounts(1).size());
                assertEquals(summary.getSummary().get(rdp.getAccountFromName("Utilities", 1)).
                        get(rdp.getBusinessFromName("153 Orange", 1)), 0.0);
                assertEquals(summary.getSummary().get(rdp.getAccountFromName("Utilities", 1)).
                        get(rdp.getBusinessFromName("Financial Service", 1)), 139.03);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
