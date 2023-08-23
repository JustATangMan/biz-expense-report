package com.jtang.springboot.biz.entities;

public class TransactionResponse {
    private Transaction transaction;
    private String accountName;
    private String businessName;
    private String categoryName;

    public TransactionResponse(Transaction transaction, String accountName, String businessName, String categoryName) {
        this.transaction = transaction;
        this.accountName = accountName;
        this.businessName = businessName;
        this.categoryName = categoryName;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
