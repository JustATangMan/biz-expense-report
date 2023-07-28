package com.jtang.springboot.biz.controllers;

import java.io.*;
import java.util.List;

import com.jtang.springboot.biz.service.impl.DefaultBizExpenseReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.jtang.springboot.biz.entities.Summary;
import com.jtang.springboot.biz.entities.Transaction;
import com.jtang.springboot.biz.service.FileProcessorService;

@RestController
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
    @RequestMapping(value="/upload/{id}", method = RequestMethod.POST)
    public String uploadRawData(@RequestBody MultipartFile file, @PathVariable("id") int taxSeasonId) throws IOException { //GET & POST
        List<Transaction> transactions = fileProcessorService.readTransactions(file.getInputStream());
        defaultBizExpenseReportService.saveTransactions(transactions, taxSeasonId);
        return "ok";
    }

    //edit raw
    @GetMapping("/get-raw-data/{taxSeasonId}")
    public List<Transaction> getRawData(@PathVariable("taxSeasonId") int taxSeasonId) { //GET
        //edit data -> repo update/put into database
        // pass taxSeasonId into bizexpensereportservice
        // call getAllTransactions, give list
        // return list
        return defaultBizExpenseReportService.getAllTransactions(taxSeasonId);
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
