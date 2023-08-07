package com.jtang.springboot.biz;

import com.jtang.springboot.biz.entities.*;
import com.jtang.springboot.biz.repo.*;
import com.jtang.springboot.biz.service.BizExpenseReportService;
import com.jtang.springboot.biz.service.FileProcessorService;
import com.jtang.springboot.biz.service.ReferenceDataProvider;
import com.jtang.springboot.biz.service.TestDataGenerator;
import com.jtang.springboot.biz.service.impl.DefaultBizExpenseReportService;
import com.jtang.springboot.biz.service.impl.DefaultFileProcessorService;
import com.jtang.springboot.biz.service.impl.DefaultReferenceDataProvider;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

@ExtendWith(MockitoExtension.class)
public class FileProcessorTests {
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
    private DefaultReferenceDataProvider rdp = new DefaultReferenceDataProvider(accRepo,bizRepo,catRepo,catToAccRepo,taxRepo,transRepo);
    private FileProcessorService fileProcessorService;
    private BizExpenseReportService bizExpenseReportService;

    File file = new File("src/test/resources/sheet.xlsx");
    File badFile = new File("src/test/resources/badsheet.xlsx");
    private TestDataGenerator tdg = new TestDataGenerator();
    List<Account> accounts = tdg.createMockAccounts();
    List<Business> businesses = tdg.createMockBusinesses();
    List<Category> categories = tdg.createMockCategories();
    List<TaxSeason> taxSeasons = tdg.createMockTaxSeasons();

    @BeforeEach
    void mockRepos() {
        fileProcessorService = new DefaultFileProcessorService(rdp);
        bizExpenseReportService = new DefaultBizExpenseReportService(rdp);
        when(accRepo.findByTaxSeasonId(1)).thenReturn(accounts);
        when(bizRepo.findByTaxSeasonId(1)).thenReturn(businesses);
        when(catRepo.findByTaxSeasonId(1)).thenReturn(categories);
//        when(taxRepo.findByTaxSeasonId(1)).thenReturn(taxSeasons);
    }

    @Test
    void testExcelWithFile() {
        List<Transaction> transactions = fileProcessorService.readTransactions(file, 1);
        System.out.println();
        LOGGER.info("Transaction size: {}", transactions.size());
        assertEquals(transactions.size(), 7);
    }

    @Test
    void testExcelWithStream() throws FileNotFoundException {
        List<Transaction> transactions = fileProcessorService.readTransactions(file, 1);
        System.out.println();
        LOGGER.info("Transaction size: {}", transactions.size());
        assertEquals(transactions.size(), 7);
    }

    @Test
    void testBadData() {
        List<Transaction> badTransactions = fileProcessorService.readTransactions(badFile, 1);
//            List<Transaction> transactions = tdg.createBadMockTransactions(false, 1);
        System.out.println(badTransactions);
    }
}
