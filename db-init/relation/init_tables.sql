create table relation_request
(
    id             bigint not null,
    recipient_id   bigint,
    request_status integer,
    request_type   integer,
    requester_id   bigint,
    primary key (id)
);