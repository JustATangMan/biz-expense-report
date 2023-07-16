package com.jtang.springboot.biz.service;

import java.io.File;
import java.util.List;

import com.jtang.springboot.biz.entities.Transaction;

public interface FileProcessorService {
	
	public List<Transaction> readTransactions(File file);
	
}
