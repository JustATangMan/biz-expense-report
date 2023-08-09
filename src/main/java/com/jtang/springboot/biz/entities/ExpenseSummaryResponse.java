package com.jtang.springboot.biz.entities;

import java.util.Map;

public class ExpenseSummaryResponse {
    private Map<String, Map<String, Double>> summary; //account needs hash serializable etc.

    public static ExpenseSummaryResponse from(ExpenseSummary expenseSummary) {
        // TODO: construct new ESR from ES, get names of accounts/businesses
        //  try to use stream map function
        throw new RuntimeException("not implemented");
    }

}
