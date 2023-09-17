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
    applied_amount decimal(10,2),
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

insert into accounts values 
(1,"Advertising","account1",1),
(2,"Auto and travel - C & E","account2",1),
(3,"Cleaning & maintenance","account3",1),
(4,"Commissions","account4",1),
(5,"Depreciation","account5",1),
(6,"Insurance","account6",1),
(7,"Legal & professional fees","account7",1),
(8,"Management fees","account8",1),
(9,"Meal - C","account9",1),
(10,"Mortgage interest","account10",1),
(11,"Other interest","account11",1),
(12,"Repairs","account12",1),
(13,"Supplies","account13",1),
(14,"Taxes","account14",1),
(15,"Travel - C","account15",1),
(16,"Utilities","account16",1),
(17,"Other","account17",1);

insert into businesses values
(1,"Business 1","money",1),
(2,"Business 2","more money",1),
(3,"Business 3","red robin",1),
(4,"Business 4","beacon",1);

insert into categories values 
(1,"Advertising","cat1",1),
(2,"Auto and travel - C & E","cat2",1),
(3,"Cleaning & maintenance","cat3",1),
(4,"Commissions","cat4",1),
(5,"Depreciation","cat5",1),
(6,"Dues/Subscription","cat6",1),
(7,"Education","cat7",1),
(8,"Furniture & Fixtures","cat8",1),
(9,"Insurance","cat9",1),
(10,"Legal & professional fees","cat10",1),
(11,"License Fee","cat11",1),
(12,"Machinery & Equipment","cat12",1),
(13,"Management fees","cat13",1),
(14,"Meal - C","cat14",1),
(15,"Mortgage interest","cat15",1),
(16,"Office Expense","cat16",1),
(17,"Other interest","cat17",1),
(18,"Outside Services","cat18",1),
(19,"Phone/Cable/Internet","cat19",1),
(20,"Postage","cat20",1),
(21,"Promotions","cat21",1),
(22,"Renovation","cat22",1),
(23,"Repairs","cat23",1),
(24,"Supplies","cat24",1),
(25,"Taxes","cat25",1),
(26,"Travel - C","cat26",1),
(27,"Utilities","cat27",1)