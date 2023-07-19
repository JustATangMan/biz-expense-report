package com.jtang.springboot.biz.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.jtang.springboot.biz.entities.Transaction;
import com.jtang.springboot.biz.service.FileProcessorService;

@Service
public class DefaultFileProcessorService implements FileProcessorService {

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
				trans.setCategoryId((int)row.getCell(6).getNumericCellValue()); // need to make refdataprovider for these
				trans.setBusinessId((int)row.getCell(7).getNumericCellValue());
				trans.setAccountId((int)row.getCell(8).getNumericCellValue());
//				for (Cell cell : row) {
//					// use cell column index
//					switch (cell.getColumnIndex()) {
//					case 0: 
//						trans.setSource(cell.getStringCellValue());
//						break;
//					case 1:
//						trans.setDate(cell.getDateCellValue());
//						break;
//					case 2:
//						trans.setDescription(cell.getStringCellValue());
//						break;
//					case 3:
//						trans.setAmount(cell.getNumericCellValue());
//						break;
//					case 4:
//						trans.setAdjustedAmount(cell.getNumericCellValue());
//						break;
//					case 6:
//						trans.setCategoryId(col); //util to get id of cat/business/account from name
//						break; // reference provider into config; 
//					case 7:
//						trans.setBusinessId(col);
//						break;
//					case 8:
//						trans.setAccountId(col);
//						break;
//					}
//					col++;
//				}
				transactions.add(trans);
			}
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return transactions;
	}

}
