CREATE TABLE customers
(
id identity NOT NULL,
email varchar(256) NOT NULL,
name varchar(256) NOT NULL,
national_id bigint NOT NULL,
passport_number varchar(128),
primary key(id)
);
ALTER TABLE customers ADD CONSTRAINT EMAIL_UNIQUE UNIQUE(email);
ALTER TABLE customers ADD CONSTRAINT NATIONAL_ID_NUM_UNIQUE UNIQUE(national_id);
ALTER TABLE customers ADD CONSTRAINT PASSPORT_NUM_UNIQUE UNIQUE(passport_number);


CREATE TABLE customer_accounts
(
id identity NOT NULL,
customer_id bigint  NOT NULL,
account_type smallint NOT NULL DEFAULT 1 COMMENT ' 1 = current account',
balance decimal(19,4) NOT NULL DEFAULT 0,
created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
updated_at TIMESTAMP WITH TIME ZONE,
primary key(id),
foreign key (customer_id) references customers(id)
);


CREATE TABLE customer_transactions
(
id identity NOT NULL,
customer_id bigint NOT NULL,
customer_account_id bigint NOT NULL,
transaction_type smallint COMMENT ' 1 = deposit, 2 = withdrawal',
amount decimal(19,4) NOT NULL DEFAULT 0,
created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
primary key(id),
foreign key (customer_id) references customers(id),
foreign key (customer_account_id) references customer_accounts(id)
);