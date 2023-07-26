package com.jtang.springboot.biz.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

    @RequestMapping(value="/upload/{id}", method = RequestMethod.POST)
    public String uploadRawData(@RequestBody MultipartFile file, @PathVariable("id") int taxSeasonId) { //GET & POST
        // upload file -> fileservice -> reportservice save
        // read stream, return file
        // pass file into fileprocessor, get back list of transactions
        // pass list into bizexpenseservice, save into database
        // return ok
        byte[] bytes;
        try {
            bytes = file.getBytes();
            File data = new File("src/main/resources/targetFile.tmp");
            try (OutputStream os = new FileOutputStream(data)) {
                os.write(bytes);
                List<Transaction> transactions = fileProcessorService.readTransactions(data);
                defaultBizExpenseReportService.saveTransactions(transactions, taxSeasonId);
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
