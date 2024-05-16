create table event (
    id bigserial primary key,
    price integer,
    date_time timestamp(6),
    name varchar(500),
    organization varchar(500)
);

create table ordr (
    id bigserial not null primary key,
    event_id bigint,
    user_id bigint
);

create table user_role (
    user_id bigint not null,
    roles varchar(20) check (roles in ('USER','ORGANIZER','ADMIN'))
);

create table usr (
    id bigserial not null primary key,
    birth_date date,
    email varchar(255) unique,
    first_name varchar(255),
    last_name varchar(255),
    password varchar(255),
    patronymic varchar(255),
    phone_number varchar(50)
);

alter table ordr
    add constraint fk_ordr_event
    foreign key (event_id) references event(id);

alter table ordr
    add constraint fk_ordr_usr
    foreign key (user_id) references usr(id);

alter table user_role
    add constraint fk_user_role_user
    foreign key (user_id) references usr;