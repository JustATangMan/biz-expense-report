package com.jtang.springboot.biz.service.impl;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

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
	private DefaultReferenceDataProvider rdp;

	@Override
	public List<Transaction> readTransactions(InputStream stream) {
		return excelWorkbookProcessing(stream);
	}

	@Override
	public List<Transaction> readTransactions(File file) {
		try {
			return excelWorkbookProcessing(new FileInputStream(file)); // check for xlsx
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Transaction> excelWorkbookProcessing(InputStream stream) {
		try {
			List<Transaction> transactions = new ArrayList<>();
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
				trans.setAppliedAmount(row.getCell(5).getNumericCellValue());
				trans.setCategoryId(rdp.getCategoryFromName(row.getCell(6).getStringCellValue()).getId()); // need to make refdataprovider for these
				trans.setBusinessId(rdp.getBusinessFromName(row.getCell(7).getStringCellValue()).getId());
				trans.setAccountId(rdp.getAccountFromName(row.getCell(8).getStringCellValue()).getId());
				transactions.add(trans);
			}
			workbook.close();
			return transactions;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
