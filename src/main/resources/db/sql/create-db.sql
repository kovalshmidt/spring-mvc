create table phoneCompany
(
    id          int auto_increment
        primary key,
    companyName varchar(255) null
);

create table users
(
    id       int auto_increment
        primary key,
    name     varchar(30)  null,
    surname  varchar(30)  null,
    email    varchar(30)  not null,
    password text         null,
    roles    varchar(100) not null,
    constraint users_email_uindex
        unique (email)
);

create table phoneUserAccount
(
    id              int auto_increment
        primary key,
    phoneNumber     varchar(255)      null,
    amount          decimal default 0 not null,
    phoneCompany_id int               null,
    user_id         int               null,
    constraint phoneUserAccount_phoneCompany_id_fk
        foreign key (phoneCompany_id) references phoneCompany (id)
            on update cascade on delete cascade,
    constraint phoneUserAccount_users_id_fk
        foreign key (user_id) references users (id)
            on update cascade on delete cascade
);

