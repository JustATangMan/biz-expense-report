package com.jtang.springboot.biz.service;

import com.jtang.springboot.biz.entities.*;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestDataGenerator {

    private static File accountsFile = new File("src/test/resources/accounts.txt");
    private static File categoriesFile = new File("src/test/resources/categories.txt");
    private static File transactionsFile = new File("src/test/resources/sheet.xlsx");
    private static File badTransactionsFile = new File("src/test/resources/badsheet.xlsx");

    public List<Account> createMockAccounts() {
        try {
            Scanner reader = new Scanner(accountsFile);
            List<Account> accounts = new ArrayList<>(); // switch to file parsing
            while (reader.hasNextLine()) {
                Account account = new Account();
                String[] line = reader.nextLine().split(",");
                account.setId(Integer.parseInt(line[0]));
                account.setName(line[1]);
                account.setDescription(line[2]);
                account.setTaxSeason(Integer.parseInt(line[3]));
                accounts.add(account);
            }
            return accounts;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
//
//
//        Account acc = new Account();
//        acc.setId(1);
//        acc.setDescription("blah blah");
//        acc.setName("Advertising");
//        acc.setTaxSeason(1);
//
//        Account acc2 = new Account();
//        acc2.setId(2);
//        acc2.setDescription("he he he haw");
//        acc2.setName("Auto and Travel - C & E");
//        acc2.setTaxSeason(1);
//
//        Account acc3 = new Account();
//        acc3.setId(3);
//        acc3.setDescription("he he he haw");
//        acc3.setName("Cleaning & maintenance");
//        acc3.setTaxSeason(1);
//
//        Account acc4 = new Account();
//        acc4.setId(4);
//        acc4.setDescription("4th account");
//        acc4.setName("Commissions");
//        acc4.setTaxSeason(1);
//
//        Account acc5 = new Account();
//        acc5.setId(5);
//        acc5.setDescription("5th account");
//        acc5.setName("Depreciation");
//        acc5.setTaxSeason(2);
//
//        Account acc6 = new Account();
//        acc6.setId(6);
//        acc6.setDescription("6th account");
//        acc6.setName("Insurance");
//        acc6.setTaxSeason(2);
//
//        Account acc7 = new Account();
//        acc7.setId(7);
//        acc7.setDescription("7th account");
//        acc7.setName("Legal & Professional Fees");
//        acc7.setTaxSeason(2);
//
//        Account acc8 = new Account();
//        acc8.setId(8);
//        acc8.setDescription("8th account");
//        acc8.setName("Management Fees");
//        acc8.setTaxSeason(2);
//
//        Account acc9 = new Account();
//        acc9.setId(9);
//        acc9.setDescription("9th account");
//        acc9.setName("Meal - C");
//        acc9.setTaxSeason(3);
//
//        Account acc10 = new Account();
//        acc10.setId(10);
//        acc10.setDescription("10th account");
//        acc10.setName("Mortgage interest");
//        acc10.setTaxSeason(3);
//
//        Account acc11 = new Account();
//        acc11.setId(11);
//        acc11.setDescription("11th account");
//        acc11.setName("Other interest");
//        acc11.setTaxSeason(3);
//
//        Account acc12 = new Account();
//        acc12.setId(12);
//        acc12.setDescription("12th account");
//        acc12.setName("Repairs");
//        acc12.setTaxSeason(3);
//
//        Account acc13 = new Account();
//        acc13.setId(13);
//        acc13.setDescription("13th account");
//        acc13.setName("Supplies");
//        acc13.setTaxSeason(4);
//
//        Account acc14 = new Account();
//        acc14.setId(14);
//        acc14.setDescription("14th account");
//        acc14.setName("Taxes");
//        acc14.setTaxSeason(4);
//
//        Account acc15 = new Account();
//        acc15.setId(15);
//        acc15.setDescription("15th account");
//        acc15.setName("Travel - C");
//        acc15.setTaxSeason(4);
//
//        Account acc16 = new Account();
//        acc16.setId(16);
//        acc16.setDescription("16th account");
//        acc16.setName("Utilities");
//        acc16.setTaxSeason(4);
//
//        Account acc17 = new Account();
//        acc17.setId(17);
//        acc17.setDescription("10th account");
//        acc17.setName("Other");
//        acc17.setTaxSeason(4);
//
//        accounts.add(acc);
//        accounts.add(acc2);
//        accounts.add(acc3);
//        accounts.add(acc4);
//        accounts.add(acc5);
//        accounts.add(acc6);
//        accounts.add(acc7);
//        accounts.add(acc8);
//        accounts.add(acc9);
//        accounts.add(acc10);
//        accounts.add(acc11);
//        accounts.add(acc12);
//        accounts.add(acc13);
//        accounts.add(acc14);
//        accounts.add(acc15);
//        accounts.add(acc16);
//        accounts.add(acc17);
//        return accounts;
    }

    public List<Business> createMockBusinesses() {
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

        Business biz3 = new Business();
        biz3.setId(3);
        biz3.setDescription("reeeeed robin");
        biz3.setName("332 Robbins");
        biz3.setTaxSeason(1);

        Business biz4 = new Business();
        biz4.setId(4);
        biz4.setDescription("beacon");
        biz4.setName("207 Beacon");
        biz4.setTaxSeason(1);

        businesses.add(biz);
        businesses.add(biz2);
        businesses.add(biz3);
        businesses.add(biz4);
        return businesses;
    }

    public List<Category> createMockCategories() {
        try {
            Scanner reader = new Scanner(categoriesFile);
            List<Category> categories = new ArrayList<>(); // switch to file parsing
            while (reader.hasNextLine()) {
                Category category = new Category();
                String[] line = reader.nextLine().split(",");
                category.setId(Integer.parseInt(line[0]));
                category.setName(line[1]);
                category.setDescription(line[2]);
                category.setTaxSeason(Integer.parseInt(line[3]));
                categories.add(category);
            }
            return categories;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
//        List<Category> categories = new ArrayList<>();
//
//        Category cat = new Category();
//        cat.setId(1);
//        cat.setDescription("cat1");
//        cat.setName("Utilities");
//        cat.setTaxSeason(1);
//
//        Category cat2 = new Category();
//        cat2.setId(2);
//        cat2.setDescription("cat2");
//        cat2.setName("Category2");
//        cat2.setTaxSeason(1);
//
//        categories.add(cat);
//        categories.add(cat2);
//        return categories;
    }

    public List<TaxSeason> createMockTaxSeasons() {
        List<TaxSeason> taxSeasons = new ArrayList<>();

        TaxSeason tax = new TaxSeason();
        tax.setTaxSeasonId(1);
        tax.setDescription("first tax season");
        tax.setYear(2023);
        tax.setName("tax");

        TaxSeason tax2 = new TaxSeason();
        tax2.setTaxSeasonId(2);
        tax2.setDescription("second tax season");
        tax2.setYear(2024);
        tax2.setName("tax2");

        TaxSeason tax3 = new TaxSeason();
        tax3.setTaxSeasonId(3);
        tax3.setDescription("third tax season");
        tax3.setYear(2025);
        tax3.setName("tax3");

        TaxSeason tax4 = new TaxSeason();
        tax4.setTaxSeasonId(4);
        tax4.setDescription("fourth tax season");
        tax4.setYear(2026);
        tax4.setName("tax4");

        taxSeasons.add(tax);
        taxSeasons.add(tax2);
        taxSeasons.add(tax3);
        taxSeasons.add(tax4);
        return taxSeasons;
    }

    private Account findAccount(String name, int taxSeasonId) {
        for (Account account : createMockAccounts()) {
            if (account.getName().equalsIgnoreCase(name) && account.getTaxSeason() == taxSeasonId) {
                return account;
            }
        }
        return null;
    }

    private Business findBusiness(String name, int taxSeasonId) {
        for (Business business : createMockBusinesses()) {
            if (business.getName().equalsIgnoreCase(name) && business.getTaxSeason() == taxSeasonId) {
                return business;
            }
        }
        return null;
    }

    private Category findCategory(String name, int taxSeasonId) {
        for (Category category : createMockCategories()) {
            if (category.getName().equalsIgnoreCase(name) && category.getTaxSeason() == taxSeasonId) {
                return category;
            }
        }
        return null;
    }

    public List<Transaction> createMockTransactions(boolean makeId, int taxSeasonId) throws ParseException {
        try {
            List<Transaction> transactions = new ArrayList<>();
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(transactionsFile));
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) { //loop through sheet, populate a transaction with each row
                Transaction trans = new Transaction();
                trans.setTaxSeason(taxSeasonId);
                trans.setSource(row.getCell(0).getStringCellValue());
                trans.setDate(row.getCell(1).getDateCellValue());
                trans.setDescription(row.getCell(2).getStringCellValue());
                trans.setAmount(row.getCell(3).getNumericCellValue());
                trans.setAdjustedAmount(row.getCell(4).getNumericCellValue());
                trans.setAppliedAmount(row.getCell(5).getNumericCellValue());
                trans.setCategoryId(findCategory(row.getCell(6).getStringCellValue(), taxSeasonId).getId()); // need to make refdataprovider for these
                trans.setBusinessId(findBusiness(row.getCell(7).getStringCellValue(), taxSeasonId).getId());
                trans.setAccountId(findAccount(row.getCell(8).getStringCellValue(), taxSeasonId).getId());
//				trans.setNotes(row.getCell(9).getStringCellValue());
                transactions.add(trans);
            }
            workbook.close();
            return transactions;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Transaction> createBadMockTransactions(boolean makeId, int taxSeasonId) throws ParseException {
        try {
            List<Transaction> transactions = new ArrayList<>();
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(badTransactionsFile));
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) { //loop through sheet, populate a transaction with each row
                Transaction trans = new Transaction();
                trans.setTaxSeason(taxSeasonId);
                trans.setSource(row.getCell(0).getStringCellValue());
                trans.setDate(row.getCell(1).getDateCellValue());
                trans.setDescription(row.getCell(2).getStringCellValue());
                trans.setAmount(row.getCell(3).getNumericCellValue());
                trans.setAdjustedAmount(row.getCell(4).getNumericCellValue());
                trans.setAppliedAmount(row.getCell(5).getNumericCellValue());
                trans.setCategoryId(findCategory(row.getCell(6).getStringCellValue(), taxSeasonId).getId()); // need to make refdataprovider for these
                trans.setBusinessId(findBusiness(row.getCell(7).getStringCellValue(), taxSeasonId).getId());
                trans.setAccountId(findAccount(row.getCell(8).getStringCellValue(), taxSeasonId).getId());
//				trans.setNotes(row.getCell(9).getStringCellValue());
                transactions.add(trans);
            }
            workbook.close();
            return transactions;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
