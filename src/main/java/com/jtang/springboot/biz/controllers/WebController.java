package com.jtang.springboot.biz.controllers;

import com.jtang.springboot.biz.entities.Transaction;
import com.jtang.springboot.biz.entities.TransactionResponse;
import com.jtang.springboot.biz.rest.BizExpenseReportController;
import com.jtang.springboot.biz.service.BizExpenseReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    BizExpenseReportController bizExpenseReportController;
    @Autowired
    BizExpenseReportService bizExpenseReportService;

    @RequestMapping("/uploadTransactions")
    public String uploadTransactions() {
        return "uploadTransactions";
    };

    @RequestMapping("/taxSeasonEntry") // rename
    public String taxSeasonEntry() {
        return "taxSeasonEntry";
    };

    @RequestMapping("/displayTransactions") // rename to display
    public String editTransactions(@RequestParam("taxSeasonId") int taxSeasonId, Model model) {
        //call bizreportservice getTransactionResponse
        List<Transaction> transactions = bizExpenseReportController.getRawData(taxSeasonId);
        TransactionResponsePayload transactionResponsePayload = bizExpenseReportService.getTransactionResponsePayload(transactions);
        model.addAttribute("payload", transactionResponsePayload);
        return "displayTransactions";
    }

    @RequestMapping(value="/editSingle/{id}/{taxSeasonId}", method = RequestMethod.GET)
    public String editSingle(@PathVariable("id") int transactionId, @PathVariable("taxSeasonId") int taxSeasonId, Model model) {
        // id from edittransactions points here
        List<Transaction> transactions = bizExpenseReportController.getRawData(taxSeasonId);
        TransactionResponsePayload transactionResponsePayload = bizExpenseReportService.getTransactionResponsePayload(transactions);
        Transaction transaction = bizExpenseReportService.getSingleTransactionById(transactionId);
        TransactionResponse transactionResponse = new TransactionResponse(transaction,
                transactionResponsePayload.getAccIdToName().get(transaction.getAccountId()),
                transactionResponsePayload.getBizIdToName().get(transaction.getBusinessId()),
                transactionResponsePayload.getCatIdToName().get(transaction.getCategoryId()));
        // grab single transaction from id
        // add single transaction to model
        // display form
        // posts to savesinglepost as modelattribute
        model.addAttribute("response", transactionResponse);
        model.addAttribute("payload", transactionResponsePayload);
        return "editSingle";
    }

    @RequestMapping(value="/editSingle", method = RequestMethod.POST)
//    @PostMapping(value="/editSingle")
    public String saveSinglePost(@ModelAttribute TransactionResponse response, Model model) {
        System.out.println("singlepost");
        bizExpenseReportService.updateTransaction(response.getTransaction());
        // save single to database
        // return to edittransactions
        return editTransactions(response.getTransaction().getTaxSeasonId(), model);
    }
}
