package com.jtang.springboot.biz.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    @Autowired
    BizExpenseReportController bizExpenseReportController;
    @RequestMapping("/uploadTransactions")

    public String uploadTransactions() {
        return "uploadTransactions";
    };

}
