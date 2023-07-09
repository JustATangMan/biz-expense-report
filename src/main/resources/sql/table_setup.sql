create database if not exists biz_expense;
use biz_expense;

create table if not exists businesses (
	business_id int,
    business_name varchar(100),
    business_description varchar(500),
    tax_season int,
    PRIMARY KEY (business_id)
);

create table if not exists categories (
	category_id int,
    category_name varchar(100),
    category_description varchar(500),
    tax_season int,
    PRIMARY KEY (category_id)
);

create table if not exists accounts (
	account_id int,
    account_name varchar(100),
    account_description varchar(500),
    tax_season int,
    PRIMARY KEY (account_id)
);

create table if not exists category_to_account (
	cat_to_acc_id int,
	category_id int,
    account_id int,
    tax_season int,
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);

create table if not exists transactions (
	id int,
    source varchar(100),
    date date,
    description varchar(500),
    amount decimal(10,2),
    adjusted_amount decimal(10,2),
    category_id int,
    business_id int,
    account_id int,
    notes varchar(500),
    tax_season int,
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (account_id) REFERENCES accounts(account_id),
    FOREIGN KEY (business_id) REFERENCES businesses(business_id)
);
