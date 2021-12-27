create table author
(
    id                 bigint auto_increment primary key,
    author_name        varchar(255) not null,
    status             int          null,
    created_date       datetime(6)  null,
    last_modified_date datetime(6)  null,
    constraint UK_ccl4rp0t1cxnkcud4m5r9e9ls
        unique (author_name)
);

create table book
(
    id                 bigint auto_increment
        primary key,
    book_category      varchar(255) null,
    book_name          varchar(50)  not null,
    isbn               varchar(13)  null,
    status             int          not null,
    created_date       datetime(6)  null,
    last_modified_date datetime(6)  null,
    constraint UK_jy2eu6s9e28na3fwo7fxv0a0x
        unique (book_name)
);

create table book_authors
(
    books_id   bigint not null,
    authors_id bigint not null,
    primary key (books_id, authors_id),
    constraint FK551i3sllw1wj7ex6nir16blsm
        foreign key (authors_id) references author (id),
    constraint FKmuhqocx8etx13u6jrtutnumek
        foreign key (books_id) references book (id)
);



