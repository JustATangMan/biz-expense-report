create database biz_expense;

use biz_expense;

create table businesses (
	business_id int,
    business_name varchar(100),
    business_description varchar(500),
    PRIMARY KEY (business_id)
);

create table categories (
	category_id int,
    category_name varchar(100),
    category_description varchar(500),
    PRIMARY KEY (category_id)
);

create table accounts (
	account_id int,
    account_name varchar(100),
    account_description varchar(500),
    PRIMARY KEY (account_id)
);

create table category_to_account (
	category_id int,
    account_id int,
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (account_id) REFERENCES accounts(account_id)
);

create table transactions (
	id int,
    source varchar(100),
    date date,
    description varchar(500),
    amount decimal(10,2),
    category_id int,
    business_id int,
    account_id int,
    notes varchar(500),
    tax_season int,
    FOREIGN KEY (category_id) REFERENCES categories(category_id),
    FOREIGN KEY (account_id) REFERENCES accounts(account_id),
    FOREIGN KEY (business_id) REFERENCES businesses(business_id)
);
