package com.jtang.springboot.biz;

import com.jtang.springboot.biz.controllers.BizExpenseReportController;
import com.jtang.springboot.biz.entities.Transaction;
import com.jtang.springboot.biz.repo.BizExpenseTransactionRepository;
import com.jtang.springboot.biz.service.BizExpenseReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class JPATests {

    @Autowired
    private BizExpenseReportController bizExpenseReportController;

    @Autowired
    private BizExpenseTransactionRepository bizExpenseTransactionRepository;

    @BeforeEach
    void cleanup() {
        bizExpenseTransactionRepository.deleteAll();
    }
    @Test
    void testControllerUploadAndGet() throws IOException { // sql overrides given primary key id (auto increments)
        File file = new File("src/test/resources/sheet.xlsx");
        assertEquals(bizExpenseReportController.uploadRawData(new MockMultipartFile("sheet.xlsx",
                Files.readAllBytes(file.toPath())), 1), "ok");
        List<Transaction> transactions = bizExpenseReportController.getRawData(1);
        assertEquals(transactions.size(), 2);
        assertEquals(transactions.get(0).getBusinessId(), 1);
    }

    // save one record etc
    // save all
    // save without id
    // save with id
    // test findbytaxid
}