package com.jtang.springboot.biz.service;

import com.jtang.springboot.biz.entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestDataGenerator {

    public static List<Account> createMockAccounts() {
        List<Account> accounts = new ArrayList<>();
        Account acc = new Account();
        acc.setId(1);
        acc.setDescription("blah blah");
        acc.setName("Utilities");
        acc.setTaxSeason(1);
        Account acc2 = new Account();
        acc2.setId(2);
        acc2.setDescription("he he he haw");
        acc2.setName("Account2");
        acc2.setTaxSeason(1);
        accounts.add(acc);
        accounts.add(acc2);
        return accounts;
    }

    public static List<Business> createMockBusinesses() {
        List<Business> businesses = new ArrayList<>();
        Business biz = new Business();
        biz.setId(1);
        biz.setDescription("money");
        biz.setName("Financial Service");
        biz.setTaxSeason(1);
        Business biz2 = new Business();
        biz2.setId(2);
        biz2.setDescription("more money");
        biz2.setName("153 Orange");
        biz2.setTaxSeason(1);
        businesses.add(biz);
        businesses.add(biz2);
        return businesses;
    }

    public static List<Category> createMockCategories() {
        List<Category> categories = new ArrayList<>();
        Category cat = new Category();
        cat.setId(1);
        cat.setDescription("cat1");
        cat.setName("Utilities");
        cat.setTaxSeason(1);
        Category cat2 = new Category();
        cat2.setId(2);
        cat2.setDescription("cat2");
        cat2.setName("Category2");
        cat2.setTaxSeason(1);
        categories.add(cat);
        categories.add(cat2);
        return categories;
    }

    public static List<TaxSeason> createMockTaxSeasons() {
        List<TaxSeason> taxSeasons = new ArrayList<>();
        TaxSeason tax = new TaxSeason();
        tax.setTaxSeasonId(1);
        tax.setDescription("first tax season");
        tax.setYear(2023);
        tax.setName("tax");
        taxSeasons.add(tax);
        return taxSeasons;
    }

    public static List<Transaction> createMockTransactions(boolean makeId) throws ParseException {
        List<Transaction> transactions = new ArrayList<>();
        Transaction trans = new Transaction();
        trans.setTaxSeason(1);
        if (makeId) {
            trans.setId(1);
        }
        trans.setDescription("trans1");
        trans.setAmount(226.93);
        trans.setAdjustedAmount(113.0);
        trans.setAppliedAmount(113.0);
        trans.setAccountId(1);
        trans.setBusinessId(1);
        trans.setCategoryId(1);
        trans.setDate(new Date(new SimpleDateFormat("MMM dd yyyy zzz").parse("Jan 14 2022 EST").getTime()));
        trans.setSource("BoA-7797");
        trans.setNotes("trans in season 1");

        Transaction trans2 = new Transaction();
        trans2.setTaxSeason(1);
        if (makeId) {
            trans2.setId(2);
        }
        trans2.setDescription("trans2");
        trans2.setAmount(26.03);
        trans2.setAdjustedAmount(0.0);
        trans2.setAppliedAmount(26.03);
        trans2.setAccountId(1);
        trans2.setBusinessId(2);
        trans2.setCategoryId(1);
        trans2.setDate(new Date(new SimpleDateFormat("MMM dd yyyy zzz").parse("Jan 14 2022 EST").getTime()));
        trans2.setSource("BoA-7797");
        trans2.setNotes("trans in season 2");

        transactions.add(trans);
        transactions.add(trans2);

        return transactions;
    }
}
