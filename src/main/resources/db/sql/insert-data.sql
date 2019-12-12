DELETE FROM phoneUserAccount;
DELETE FROM phoneCompany;
DELETE FROM users;

INSERT INTO users (id, name, surname, email, password, roles) VALUES (31, 'Stepan', 'Giga', 'giga@giga.com','$2a$10$y3/je8TF1.6zUq6Mh743M.e/1sHEC7nM.LCZsswTfyI5LhI3UAn6C', 'REGISTERED_USER');
INSERT INTO users (id, name, surname, email, password, roles) VALUES (32, 'Viktor', 'Pavlik', 'pavlik@pavlik.com', '$2a$10$ul71VFDGhnTTNpV0OXAjFuvL8RGG3Wmxxyun/BEC1WIUVLI.dROcC', 'REGISTERED_USER');
INSERT INTO users (id, name, surname, email, password, roles) VALUES (33, ' ', ' ', 'admin@admin.com', '$2a$10$TbxXjR1kptKbswMKXfolbOzJtDMxBV05qfniFd3J0HIZqzHmdrC62', 'REGISTERED_USER,BOOKING_MANAGER');

INSERT INTO phoneCompany (id, companyName) VALUES (22, 'beeline');
INSERT INTO phoneCompany (id, companyName) VALUES (23, 'vodafone');
INSERT INTO phoneCompany (id, companyName) VALUES (24, 'lifecell');
INSERT INTO phoneCompany (id, companyName) VALUES (25, 'kyivstar');

INSERT INTO phoneUserAccount (id, phoneNumber, amount, phoneCompany_id, user_id) VALUES (27, '380534265345', 100, 22, 31);
INSERT INTO phoneUserAccount (id, phoneNumber, amount, phoneCompany_id, user_id) VALUES (28, '380534265344', 20, 22, 32);
