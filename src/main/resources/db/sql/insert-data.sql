INSERT INTO users (id, fullName) VALUES (1, 'Stepan Giga');
INSERT INTO users (id, fullName) VALUES (2, 'Viktor Pavlik');

INSERT INTO phoneCompany (id, companyName) VALUES (22, 'beeline');
INSERT INTO phoneCompany (id, companyName) VALUES (23, 'vodafone');

INSERT INTO phoneNumber (id, phoneNumber, phoneCompany_id, users_id) VALUES (27, '380534265345', 22, 1);
INSERT INTO phoneNumber (id, phoneNumber, phoneCompany_id, users_id) VALUES (28, '380534265344', 22, 2);
