create table phoneCompany
(
    id          int auto_increment
        primary key,
    companyName varchar(255) null
);

create table phoneUser
(
    id       int auto_increment
        primary key,
    fullName varchar(30) null
);

create table phoneUserAccount
(
    id              int auto_increment
        primary key,
    phoneNumber     varchar(255)      null,
    amount          decimal default 0 not null,
    phoneCompany_id int               null,
    phoneUser_id    int               null,
    constraint phoneCompany___fk
        foreign key (phoneCompany_id) references phoneCompany (id)
            on update cascade on delete cascade,
    constraint phoneUser___fk
        foreign key (phoneUser_id) references phoneUser (id)
            on update cascade on delete cascade
);

create table users
(
    id           int auto_increment,
    email        varchar(30)  not null,
    password     text         not null,
    roles        varchar(100) not null,
    phoneUser_id int          null,
    constraint users_email_uindex
        unique (email),
    constraint users_id_uindex
        unique (id),
    constraint phoneUser_id_fk
        foreign key (phoneUser_id) references phoneUser (id)
            on update cascade on delete cascade
);

alter table users
    add primary key (id);

