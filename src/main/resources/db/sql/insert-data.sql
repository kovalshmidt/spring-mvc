INSERT INTO users (id, fullName) VALUES (100, 'Stepan Giga');
INSERT INTO users (id, fullName) VALUES (101, 'Viktor Pavlik');

INSERT INTO phoneCompany (id, companyName) VALUES (22, 'beeline');

INSERT INTO phoneNumber (id, phoneNumber, phoneCompany_id, users_id) VALUES (27, '380534265345', 22, 100);
INSERT INTO phoneNumber (id, phoneNumber, phoneCompany_id, users_id) VALUES (28, '380534265345', 22, 101);
