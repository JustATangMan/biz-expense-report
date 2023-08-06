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

@SpringBootTest
public class FileProcessorTests {
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

    File file = new File("src/test/resources/sheet.xlsx");
    private TestDataGenerator tdg = new TestDataGenerator();
    List<Account> accounts = tdg.createMockAccounts();
    List<Business> businesses = tdg.createMockBusinesses();
    List<Category> categories = tdg.createMockCategories();
    List<TaxSeason> taxSeasons = tdg.createMockTaxSeasons();

    @Test
    void testExcelWithFile() {
        when(accRepo.findAll()).thenReturn(accounts);
        when(accRepo.findByTaxSeasonId(1)).thenReturn(accounts);
        when(bizRepo.findAll()).thenReturn(businesses);
        when(bizRepo.findByTaxSeasonId(1)).thenReturn(businesses);
        when(catRepo.findAll()).thenReturn(categories);
        when(catRepo.findByTaxSeasonId(1)).thenReturn(categories);

        List<Transaction> transactions = fileProcessorService.readTransactions(file, 1);
        System.out.println();
        LOGGER.info("Transaction size: {}", transactions.size());
        assertEquals(transactions.size(), 7);
    }

    @Test
    void testExcelWithStream() throws FileNotFoundException {
        when(accRepo.findAll()).thenReturn(accounts);
        when(accRepo.findByTaxSeasonId(1)).thenReturn(accounts);
        when(bizRepo.findAll()).thenReturn(businesses);
        when(bizRepo.findByTaxSeasonId(1)).thenReturn(businesses);
        when(catRepo.findAll()).thenReturn(categories);
        when(catRepo.findByTaxSeasonId(1)).thenReturn(categories);

        List<Transaction> transactions = fileProcessorService.readTransactions(file, 1);
        System.out.println();
        LOGGER.info("Transaction size: {}", transactions.size());
        assertEquals(transactions.size(), 7);
    }

    @Test
    void testBadData() {
        when(accRepo.findAll()).thenReturn(accounts);
        when(accRepo.findByTaxSeasonId(1)).thenReturn(accounts);
        when(bizRepo.findAll()).thenReturn(businesses);
        when(bizRepo.findByTaxSeasonId(1)).thenReturn(businesses);
        when(catRepo.findAll()).thenReturn(categories);
        when(catRepo.findByTaxSeasonId(1)).thenReturn(categories);
        try {
            List<Transaction> transactions = tdg.createBadMockTransactions(false, 1);
            System.out.println(transactions);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
