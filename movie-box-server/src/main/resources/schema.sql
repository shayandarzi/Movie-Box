create sequence movie_seq start with 1 increment by 1;

create table movie (
    id bigint not null,
    description NVARCHAR2(256),
    name NVARCHAR2(100) not null,
    director NVARCHAR2(100) not null,
    writer NVARCHAR2(100) not null,
    actors NVARCHAR2(500) not null,
    content_location NVARCHAR2(256) not null,
    poster BLOB not null,
    primary key (id)
);

create index MOVIE_NAME on movie (name);
create index MOVIE_DESCRIPTION on movie (description);

alter table movie
    add constraint UK_MOVIE_1 unique (name);
