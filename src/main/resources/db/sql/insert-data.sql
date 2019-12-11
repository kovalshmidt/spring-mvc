DELETE FROM phoneUserAccount;
DELETE FROM phoneUser;
DELETE FROM phoneCompany;
DELETE FROM users;

INSERT INTO phoneUser (id, fullName) VALUES (1, 'Stepan Giga');
INSERT INTO phoneUser (id, fullName) VALUES (2, 'Viktor Pavlik');

INSERT INTO users (id, email, password, roles, phoneUser_id) VALUES (31, 'giga@giga.com','$2a$10$y3/je8TF1.6zUq6Mh743M.e/1sHEC7nM.LCZsswTfyI5LhI3UAn6C', 'REGISTERED_USER', 1);
INSERT INTO users (id, email, password, roles, phoneUser_id) VALUES (32, 'pavlik@pavlik.com', '$2a$10$ul71VFDGhnTTNpV0OXAjFuvL8RGG3Wmxxyun/BEC1WIUVLI.dROcC', 'REGISTERED_USER', 2);
INSERT INTO users (id, email, password, roles) VALUES (33, 'admin@admin.com', '$2a$10$TbxXjR1kptKbswMKXfolbOzJtDMxBV05qfniFd3J0HIZqzHmdrC62', 'REGISTERED_USER,BOOKING_MANAGER');

INSERT INTO phoneCompany (id, companyName) VALUES (22, 'beeline');
INSERT INTO phoneCompany (id, companyName) VALUES (23, 'vodafone');

INSERT INTO phoneUserAccount (id, phoneNumber, phoneCompany_id, phoneUser_id) VALUES (27, '380534265345', 22, 1);
INSERT INTO phoneUserAccount (id, phoneNumber, phoneCompany_id, phoneUser_id) VALUES (28, '380534265344', 22, 2);
