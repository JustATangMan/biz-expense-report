package com.jtang.springboot.biz;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jtang.springboot.biz.service.FileProcessorService;

@SpringBootTest
class BizExpenseReportApplicationTests {

	@Autowired
	private FileProcessorService service;
	
	@Test
	void testExcel() {
		File file = new File("src/main/resources/sheet.xlsx");
		service.readTransactions(file);
		System.out.println();
	}

}
