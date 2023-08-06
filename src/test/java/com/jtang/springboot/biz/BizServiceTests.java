package com.jtang.springboot.biz;

import com.jtang.springboot.biz.entities.*;
import com.jtang.springboot.biz.repo.*;
import com.jtang.springboot.biz.service.BizExpenseReportService;
import com.jtang.springboot.biz.service.FileProcessorService;
import com.jtang.springboot.biz.service.ReferenceDataProvider;
import com.jtang.springboot.biz.service.TestDataGenerator;
import com.jtang.springboot.biz.service.impl.DefaultReferenceDataProvider;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

//TODO: remove springboottest, try @ExtendWith(MockitoExtension.class), mock repos, use
// new call + constructor injection in @BeforeEach test
// experiment with @InjectMocks on bizService
@SpringBootTest
public class BizServiceTests {

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
//    @Autowired
//    private FileProcessorService fileProcessorService;
    @Autowired
    private BizExpenseReportService bizExpenseReportService;

    private TestDataGenerator tdg = new TestDataGenerator();
    List<Account> accounts = tdg.createMockAccounts();
    List<Business> businesses = tdg.createMockBusinesses();
    List<Category> categories = tdg.createMockCategories();
    List<TaxSeason> taxSeasons = tdg.createMockTaxSeasons();

    @Test
    void testSummary() {
        List<Transaction> transactions;
        {
            try {
                when(accRepo.findByTaxSeasonId(1)).thenReturn(accounts);
                when(bizRepo.findByTaxSeasonId(1)).thenReturn(businesses);
                when(catRepo.findByTaxSeasonId(1)).thenReturn(categories);
                transactions = tdg.createMockTransactions(false, 1);
//              when(accRepo.findAll()).thenReturn(accounts);
                when(accRepo.findByTaxSeasonId(1)).thenReturn(accounts);
//		        when(bizRepo.findAll()).thenReturn(businesses);
                when(bizRepo.findByTaxSeasonId(1)).thenReturn(businesses);
//		        when(catRepo.findAll()).thenReturn(categories);
                when(catRepo.findByTaxSeasonId(1)).thenReturn(categories);
//		        when(transRepo.findAll()).thenReturn(transactions);
                when(transRepo.findByTaxSeasonId(1)).thenReturn(transactions);
//		        when(taxRepo.findAll()).thenReturn(taxSeasons);
                when(taxRepo.findByTaxSeasonId(1)).thenReturn(taxSeasons);
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
