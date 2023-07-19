package com.jtang.springboot.biz.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jtang.springboot.biz.entities.Summary;
import com.jtang.springboot.biz.entities.Transaction;
import com.jtang.springboot.biz.service.FileProcessorService;

@RestController
public class BizExpenseReportController {
	
	@Autowired
	private FileProcessorService fileService;
	
	//home page
	
	//upload
	public String uploadRawData(MultipartFile file) { //GET & POST
		//upload file -> fileservice -> reportservice save
		byte[] bytes;
		try {
			bytes = file.getBytes();
			File data = new File("src/main/resources/targetFile.tmp");
			try (OutputStream os = new FileOutputStream(data)) {
				os.write(bytes);
				fileService.readTransactions(data);
				return "ok";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "bad";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "bad";
		}
			
	}
	//edit raw
	public List<Transaction> editRawData(int taxSeasonId) { //GET
		//edit data -> repo update/put into database
		return null;
	}
	
	public String updateTransaction(Transaction transaction) { //POST
		//update database for given transaction
		return null;
	}
	
	//display summary
	public List<Summary> displaySummary(int taxSeasonId) { //GET
		//select taxseason -> repo.get + calculate summary -> display
		return null;
	}
	
	//delete
	public String deleteTaxSeason(int taxSeasonId) { //GET
		return null;	
	}
	
}
