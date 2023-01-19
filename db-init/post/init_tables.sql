create table post
(
    id              bigint not null,
    attachment_path varchar(255),
    content         varchar(255),
    creation_date   timestamp,
    user_id         bigint,
    primary key (id)
);
