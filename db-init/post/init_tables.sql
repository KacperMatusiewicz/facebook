create table post
(
    id              bigint not null,
    attachment_path varchar(255),
    content         varchar(255),
    creation_date   timestamp,
    user_id         bigint,
    primary key (id)
);
create view post_view as
    select
        p.id as id,
        p.user_id as user_id,
        p.content as content,
        p.creation_date as creation_date
    from post p;