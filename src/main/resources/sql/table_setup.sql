use biz_expense;

drop table if exists transactions;
drop table if exists businesses;
drop table if exists category_to_account;
drop table if exists accounts;
drop table if exists categories;
drop table if exists tax_season;

create database if not exists biz_expense;

create table if not exists tax_seasons (
	tax_season_id int NOT NULL AUTO_INCREMENT,
    name varchar(50),
    year int,
    description varchar(500),
    PRIMARY KEY (tax_season_id)
);

create table if not exists categories (
	category_id int NOT NULL AUTO_INCREMENT,
    category_name varchar(100),
    category_description varchar(500),
    tax_season_id int,
    PRIMARY KEY (category_id),
	FOREIGN KEY (tax_season_id) REFERENCES tax_seasons(tax_season_id)
);

create table if not exists accounts (
	account_id int NOT NULL AUTO_INCREMENT,
    account_name varchar(100),
    account_description varchar(500),
    tax_season_id int,
    PRIMARY KEY (account_id),
	FOREIGN KEY (tax_season_id) REFERENCES tax_seasons(tax_season_id)
);

create table if not exists businesses (
	business_id int NOT NULL AUTO_INCREMENT,
    business_name varchar(100),
    business_description varchar(500),
    tax_season_id int,
    PRIMARY KEY (business_id),
	FOREIGN KEY (tax_season_id) REFERENCES tax_seasons(tax_season_id)
);

create table if not exists category_to_account (
	cat_to_acc_id int NOT NULL AUTO_INCREMENT,
	category_id int,
    account_id int,
    tax_season_id int,
    PRIMARY KEY (cat_to_acc_id),
    FOREIGN KEY (tax_season_id) REFERENCES tax_seasons(tax_season_id),
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);


create table if not exists transactions (
	transaction_id int NOT NULL AUTO_INCREMENT,
    source varchar(100),
    date date,
    description varchar(500),
    amount decimal(10,2),
    adjusted_amount decimal(10,2),
    category_id int,
    business_id int,
    account_id int,
    notes varchar(500),
    tax_season_id int,
    PRIMARY KEY (transaction_id),
	FOREIGN KEY (tax_season_id) REFERENCES tax_seasons(tax_season_id),
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (account_id) REFERENCES accounts(account_id),
    FOREIGN KEY (business_id) REFERENCES businesses(business_id)
);
