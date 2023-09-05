package com.jtang.springboot.biz.entities;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ExpenseSummaryResponse {
    private Map<String, Map<String, Double>> summary; //account needs hash serializable etc.
    private List<String> businesses;

    public ExpenseSummaryResponse from(ExpenseSummary expenseSummary) {
        // TODO: construct new ESR from ES, get names of accounts/businesses
        //  try to use stream map function
        Set<Map.Entry<Account,Map<Business,Double>>> entries = expenseSummary.getSummary().entrySet();
        summary = entries.stream().collect(Collectors.toMap(
                e -> e.getKey().getName(),
                e -> e.getValue().entrySet().stream().collect(Collectors.toMap(
                        b -> b.getKey().getName(),
                        b -> b.getValue()
                ))
        ));
        businesses = summary.values().stream().toList().get(0).keySet().stream().toList();
        return this;
    }

    public List<String> getBusinesses() {
        return businesses;
    }

    public Map<String, Map<String, Double>> getSummary() {
        return summary;
    }
}
