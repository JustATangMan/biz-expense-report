package com.jtang.springboot.biz.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;

import com.jtang.springboot.biz.service.ReferenceDataProvider;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jtang.springboot.biz.entities.Transaction;
import com.jtang.springboot.biz.service.FileProcessorService;

@Service
public class DefaultFileProcessorService implements FileProcessorService {

	@Autowired
	private ReferenceDataProvider rdp;

	@Override
	public List<Transaction> readTransactions(File file) {

		List<Transaction> transactions = new ArrayList<>();
		FileInputStream stream;
		try {
			stream = new FileInputStream(file); // check for xlsx
			XSSFWorkbook workbook = new XSSFWorkbook(stream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			for (Row row : sheet) { //loop through sheet, populate a transaction with each row
				Transaction trans = new Transaction();
//				int col = 0;
				trans.setSource(row.getCell(0).getStringCellValue());
				trans.setDate(row.getCell(1).getDateCellValue());
				trans.setDescription(row.getCell(2).getStringCellValue());
				trans.setAmount(row.getCell(3).getNumericCellValue());
				trans.setAdjustedAmount(row.getCell(4).getNumericCellValue());
				trans.setCategoryId(rdp.getCategoryFromName(row.getCell(5).getStringCellValue()).getId()); // need to make refdataprovider for these
				trans.setBusinessId(rdp.getBusinessFromName(row.getCell(6).getStringCellValue()).getId());
				trans.setAccountId(rdp.getAccountFromName(row.getCell(7).getStringCellValue()).getId());
				transactions.add(trans);
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transactions;
	}

}
