package com.jtang.springboot.biz.service;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import com.jtang.springboot.biz.entities.Transaction;

public interface FileProcessorService {

    List<Transaction> readTransactions(InputStream stream);

    List<Transaction> readTransactions(File file);
	
}
