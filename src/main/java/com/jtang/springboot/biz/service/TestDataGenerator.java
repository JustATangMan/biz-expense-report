package com.jtang.springboot.biz.service;

import com.jtang.springboot.biz.entities.Account;
import com.jtang.springboot.biz.entities.Business;
import com.jtang.springboot.biz.entities.Category;

import java.util.ArrayList;
import java.util.List;

public class TestDataGenerator {

    public List<Account> createMockAccounts() {
        List<Account> accounts = new ArrayList<>();
        Account acc = new Account();
        acc.setId(1);
        acc.setDescription("blah blah");
        acc.setName("Account1");
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

    public List<Business> createMockBusinesses() {
        List<Business> businesses = new ArrayList<>();
        Business biz = new Business();
        biz.setId(1);
        biz.setDescription("money");
        biz.setName("Biz1");
        biz.setTaxSeason(1);
        Business biz2 = new Business();
        biz2.setId(2);
        biz2.setDescription("more money");
        biz2.setName("Biz2");
        biz2.setTaxSeason(1);
        businesses.add(biz);
        businesses.add(biz2);
        return businesses;
    }

    public List<Category> createMockCategories() {
        List<Category> categories = new ArrayList<>();
        Category cat = new Category();
        cat.setId(1);
        cat.setDescription("cat1");
        cat.setName("Category1");
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

}
