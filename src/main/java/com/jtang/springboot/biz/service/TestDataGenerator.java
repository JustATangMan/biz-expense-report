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
    private static File missingAppliedFile = new File("src/test/resources/missing_applied_amount.xlsx");

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
    }

    public List<Business> createMockBusinesses() {
        List<Business> businesses = new ArrayList<>();

        Business biz = new Business();
        biz.setId(1);
        biz.setDescription("money");
        biz.setName("Business 1");
        biz.setTaxSeason(1);

        Business biz2 = new Business();
        biz2.setId(2);
        biz2.setDescription("more money");
        biz2.setName("Business 2");
        biz2.setTaxSeason(1);

        Business biz3 = new Business();
        biz3.setId(3);
        biz3.setDescription("reeeeed robin");
        biz3.setName("Business 3");
        biz3.setTaxSeason(1);

        Business biz4 = new Business();
        biz4.setId(4);
        biz4.setDescription("beacon");
        biz4.setName("Business 4");
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
                Account account = findAccount(row.getCell(8).getStringCellValue(), taxSeasonId);
                Business business = findBusiness(row.getCell(7).getStringCellValue(), taxSeasonId);
                Category category = findCategory(row.getCell(6).getStringCellValue(), taxSeasonId);
                if (account == null || business == null || category == null) {
                    continue;
                }
                trans.setTaxSeasonId(taxSeasonId);
                trans.setSource(row.getCell(0).getStringCellValue());
                trans.setDate(row.getCell(1).getDateCellValue());
                trans.setDescription(row.getCell(2).getStringCellValue());
                trans.setAmount(row.getCell(3).getNumericCellValue());
                trans.setAdjustedAmount(row.getCell(4).getNumericCellValue());
                trans.setAppliedAmount(row.getCell(5).getNumericCellValue());
                trans.setCategoryId(category.getId()); // need to make refdataprovider for these
                trans.setBusinessId(business.getId());
                trans.setAccountId(account.getId());
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
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(missingAppliedFile));
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) { //loop through sheet, populate a transaction with each row
                Transaction trans = new Transaction();
                Account account = findAccount(row.getCell(8).getStringCellValue(), taxSeasonId);
                Business business = findBusiness(row.getCell(7).getStringCellValue(), taxSeasonId);
                Category category = findCategory(row.getCell(6).getStringCellValue(), taxSeasonId);
                if (account == null || business == null || category == null) {
                    continue;
                }
                trans.setTaxSeasonId(taxSeasonId);
                trans.setSource(row.getCell(0).getStringCellValue());
                trans.setDate(row.getCell(1).getDateCellValue());
                trans.setDescription(row.getCell(2).getStringCellValue());
                trans.setAmount(row.getCell(3).getNumericCellValue());
                trans.setAdjustedAmount(row.getCell(4).getNumericCellValue());
                trans.setAppliedAmount(row.getCell(5).getNumericCellValue());
                trans.setCategoryId(category.getId()); // need to make refdataprovider for these
                trans.setBusinessId(business.getId());
                trans.setAccountId(account.getId());
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
