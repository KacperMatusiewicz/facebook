create table session_entry
(
    session_id varchar(255) not null,
    user_id    bigint,
    creation_date timestamp,
    primary key (session_id)
);
create table user_authentication_data
(
    id       bigint not null,
    login    varchar(255),
    password varchar(255),
    primary key (id)
);

create view user_authentication_data_view as
    select * from user_authentication_data;