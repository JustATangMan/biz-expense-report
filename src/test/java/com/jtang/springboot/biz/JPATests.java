package com.jtang.springboot.biz;

import com.jtang.springboot.biz.controllers.BizExpenseReportController;
import com.jtang.springboot.biz.entities.Transaction;
import com.jtang.springboot.biz.service.impl.DefaultReferenceDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JPATests {

    @Autowired
    private DefaultReferenceDataProvider rdp;
    @Autowired
    private BizExpenseReportController bizExpenseReportController;

    //    @BeforeAll
//    void cleanup() {
//        bizExpenseTransactionRepository.deleteAll();
//    }
    @Test
    void testControllerUploadAndGet() throws IOException { // sql overrides given primary key id (auto increments)
        rdp.deleteTransactions();
        File file = new File("src/test/resources/sheet.xlsx");
        assertEquals(bizExpenseReportController.uploadRawData(new MockMultipartFile("sheet.xlsx",
                Files.readAllBytes(file.toPath())), 1).size(), 7);
        List<Transaction> transactions = bizExpenseReportController.getRawData(1);
        assertEquals(transactions.size(), 7);
        assertEquals(transactions.get(0).getBusinessId(), 1);
    }

    @Test
    void testUpdate() {
        List<Transaction> transactions = bizExpenseReportController.getRawData(1);
        Transaction updateTransaction = transactions.get(1);
        updateTransaction.setNotes("blaaaaaaaaaaaaaah");
        bizExpenseReportController.updateTransaction(updateTransaction);
        transactions = bizExpenseReportController.getRawData(1);
        assertEquals(transactions.get(1).getNotes(), "blaaaaaaaaaaaaaah");
    }

    // save one record etc
    // save all
    // save without id
    // save with id
    // test findbytaxid
}