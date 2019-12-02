CREATE SCHEMA IF NOT EXISTS phones;

create table phoneCompany
(
    id          int auto_increment,
    companyName varchar(255) null,
    constraint phoneCompany_companyName_uindex
        unique (companyName),
    constraint phoneCompany_id_uindex
        unique (id)
);

alter table phoneCompany
    add primary key (id);

create table users
(
    id       int auto_increment,
    fullName varchar(30) null,
    constraint users_id_uindex
        unique (id)
);

alter table users
    add primary key (id);

create table phoneNumber
(
    id              int auto_increment,
    phoneNumber     varchar(255) null,
    phoneCompany_id int          null,
    users_id        int          null,
    constraint phoneNumber_id_uindex
        unique (id),
    constraint phoneNumber_phoneNumber_uindex
        unique (phoneNumber),
    constraint phoneCompany___fk
        foreign key (phoneCompany_id) references phoneCompany (id)
            on update cascade on delete cascade,
    constraint user___fk
        foreign key (users_id) references users (id)
            on update cascade on delete cascade
);

alter table phoneNumber
    add primary key (id);

