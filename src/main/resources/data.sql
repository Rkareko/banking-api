
insert into customers
(email, name, national_id, passport_number)
values('jane@onlinebank.com', 'Jane Doe', 12345678, 'ke-12345');

insert into customer_accounts
(customer_id, balance)
values(1, 4500.00);
