create database biz_expense;

use biz_expense;

create table businesses (
	business_ID int,
    business_name varchar(100),
    business_description varchar(500),
    PRIMARY KEY (business_ID)
);

create table categories (
	category_ID int,
    category_name varchar(100),
    category_description varchar(500),
    PRIMARY KEY (category_ID)
);

create table accounts (
	account_ID int,
    account_name varchar(100),
    account_description varchar(500),
    PRIMARY KEY (account_ID)
);

create table category_to_account (
	category_ID int,
    account_ID int,
    FOREIGN KEY (category_ID) REFERENCES categories(category_ID),
    FOREIGN KEY (account_ID) REFERENCES accounts(account_ID)
);

create table transactions (
	id int,
    source varchar(100),
    date date,
    description varchar(500),
    amount decimal(10,2),
    category_ID int,
    business_ID int,
    account_ID int,
    notes varchar(500),
    tax_season int,
    FOREIGN KEY (category_ID) REFERENCES categories(category_ID),
    FOREIGN KEY (account_ID) REFERENCES accounts(account_ID),
    FOREIGN KEY (business_ID) REFERENCES businesses(business_ID)
);
