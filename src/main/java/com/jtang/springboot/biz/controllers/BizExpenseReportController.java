package com.jtang.springboot.biz.controllers;

import java.io.*;
import java.util.List;

import com.jtang.springboot.biz.entities.*;
import com.jtang.springboot.biz.service.impl.DefaultBizExpenseReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jtang.springboot.biz.service.FileProcessorService;

@RestController
@Transactional
public class BizExpenseReportController {

    @Autowired
    private FileProcessorService fileProcessorService;

    @Autowired
    private DefaultBizExpenseReportService defaultBizExpenseReportService;

    //home page

    //upload

    /**
     * Takes a raw data Excel file and saves it to the database for a given tax season
     *
     * @param file File containing raw data
     * @param taxSeasonId Tax Season ID the data is saved under
     * @return String indicating upload status
     * @throws IOException Error when processing file
     */
    @PostMapping(value="/upload/{id}")
    public List<Transaction> uploadRawData(@RequestBody MultipartFile file, @PathVariable("id") int taxSeasonId) throws IOException { //GET & POST
        defaultBizExpenseReportService.deleteRawData(taxSeasonId);
        List<Transaction> transactions = fileProcessorService.readTransactions(file.getInputStream(), taxSeasonId);
        return defaultBizExpenseReportService.saveTransactions(transactions, taxSeasonId);
    }

    //edit raw

    /**
     *
     * @param taxSeasonId Tax Season ID
     * @return All transactions under tax season (provided by parameter)
     */
    @GetMapping("/get-raw-data/{taxSeasonId}")
    public List<Transaction> getRawData(@PathVariable("taxSeasonId") int taxSeasonId) { //GET
        //edit data -> repo update/put into database
        // pass taxSeasonId into bizexpensereportservice
        // call getAllTransactions, give list
        // return list
        return defaultBizExpenseReportService.getAllTransactions(taxSeasonId);
    }

    @PostMapping(value="/update")
    public Transaction updateTransaction(@RequestBody Transaction transaction) { //POST
        //check if id is valid (exists to be updated)
        return defaultBizExpenseReportService.updateTransaction(transaction);
        //update database for given transaction
    }

    //display summary
    @GetMapping("/summary/{taxSeasonId}")
    public ExpenseSummary displaySummary(@PathVariable("taxSeasonId") int taxSeasonId) { //GET
        //select taxseason -> repo.get + calculate summary -> display
        return defaultBizExpenseReportService.getSummaryTable(taxSeasonId);
    }

    //delete
    public String deleteTaxSeason(int taxSeasonId) { //GET
        return null;
    }

}
