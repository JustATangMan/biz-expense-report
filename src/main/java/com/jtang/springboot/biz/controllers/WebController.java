package com.jtang.springboot.biz.controllers;

import com.jtang.springboot.biz.entities.Transaction;
import com.jtang.springboot.biz.entities.TransactionResponse;
import com.jtang.springboot.biz.rest.BizExpenseReportController;
import com.jtang.springboot.biz.service.BizExpenseReportService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class WebController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebController.class);
    @Autowired
    BizExpenseReportController bizExpenseReportController;
    @Autowired
    BizExpenseReportService bizExpenseReportService;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping(value="/home", method=RequestMethod.POST)
    public String setCookie(@RequestParam(name="taxSeasonId") int taxSeasonId, HttpServletResponse response) {
        Cookie cookie = new Cookie("taxSeasonId", String.valueOf(taxSeasonId));
        response.addCookie(cookie);
        return "home";
    }


    @RequestMapping("/uploadTransactions")
    public String uploadTransactions(HttpServletRequest request) {
        if (getTaxSeasonId(request) < 0) {
            return "home";
        }
        return "uploadTransactions";
    }

    @RequestMapping("/uploadToDisplay")
    public String uploadToDisplay(@RequestBody @RequestParam(name="file") MultipartFile file, @RequestParam(name="taxSeasonId") int taxSeasonId, Model model, HttpServletRequest request) {
        try {
            bizExpenseReportController.uploadRawData(file, taxSeasonId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return displayTransactions(model, request);
    }

    @RequestMapping("/displayTransactions") // rename to display
    public String displayTransactions(Model model, HttpServletRequest request) {
        //call bizreportservice getTransactionResponse
        int taxSeasonId = getTaxSeasonId(request);
        if (taxSeasonId < 0) {
            return "home";
        }
        List<Transaction> transactions = bizExpenseReportController.getRawData(taxSeasonId);
        TransactionResponsePayload transactionResponsePayload = bizExpenseReportService.getTransactionResponsePayload(transactions);
        model.addAttribute("payload", transactionResponsePayload);
        return "displayTransactions";
    }

    @RequestMapping(value="/editSingle/{id}", method = RequestMethod.GET)
    public String editSingle(@PathVariable("id") int transactionId, HttpServletRequest request, Model model) {
        // id from edittransactions points here
        int taxSeasonId = getTaxSeasonId(request);
        if (taxSeasonId < 0) {
            return "home";
        }
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
    public String saveSinglePost(@ModelAttribute TransactionResponse response, Model model, HttpServletRequest request) {
        System.out.println("singlepost");
        bizExpenseReportService.updateTransaction(response.getTransaction());
        // save single to database
        // return to edittransactions
        return displayTransactions(model, request);
    }

    @RequestMapping(value="/summary")
    public String summary(Model model, HttpServletRequest request) {
        int taxSeasonId = getTaxSeasonId(request);
        if (taxSeasonId < 0) return "home";
        model.addAttribute("summaryPayload", bizExpenseReportService.getSummaryTable(taxSeasonId));
        return "summary";
    }

    public int getTaxSeasonId(HttpServletRequest request) { // move to utility
        int taxSeasonId = -1;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return taxSeasonId;
        for (Cookie c : cookies) {
            if (c.getName().equalsIgnoreCase("taxSeasonId")) {
                taxSeasonId = Integer.parseInt(c.getValue());
                break;
            }
        }
        return taxSeasonId;
    }
}
