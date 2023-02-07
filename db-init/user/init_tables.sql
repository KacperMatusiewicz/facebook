create table if not exists user_table
(
    id          bigint not null,
    email       varchar(255),
    last_name   varchar(255),
    name        varchar(255),
    password    varchar(255),
    user_status integer,
    creation_date timestamp,
    primary key (id)
);

create table if not exists activation_link
(
    key     int8 not null,
    user_id bigint,
    creation_date timestamp,
    used boolean,
    primary key (key)
);
alter table activation_link
    add constraint fk_activation_link_user_table
    foreign key (user_id)
    references user_table;

create view user_details_view as
    select
        u.id as id,
        u.name as name,
        u.last_name as last_name,
        u.email as email
    from user_table u;

create view user_email_view as
    select
        u.email as email
    from user_table u;
