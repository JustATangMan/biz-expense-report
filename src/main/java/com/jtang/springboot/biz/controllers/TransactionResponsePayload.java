package com.jtang.springboot.biz.controllers;

import com.jtang.springboot.biz.entities.TransactionResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionResponsePayload {

    private List<TransactionResponse> transactionResponses;
    private Map<Integer, String> accIdToName;
    private Map<Integer, String> bizIdToName;
    private Map<Integer, String> catIdToName;

    public TransactionResponsePayload(List<TransactionResponse> transactionResponses, Map<Integer, String> accIdToName, Map<Integer, String> bizIdToName, Map<Integer, String> catIdToName) {
        this.transactionResponses = transactionResponses;
        this.accIdToName = accIdToName;
        this.bizIdToName = bizIdToName;
        this.catIdToName = catIdToName;
    }

    public List<TransactionResponse> getTransactionResponses() {
        return transactionResponses;
    }

    public Map<Integer, String> getAccIdToName() {
        return accIdToName;
    }

    public Map<Integer, String> getBizIdToName() {
        return bizIdToName;
    }

    public Map<Integer, String> getCatIdToName() {
        return catIdToName;
    }
}
