create table if not exists good
(
    id          bigserial    not null primary key,
    title       varchar(255) not null,
    producer    varchar(255) not null,
    year        integer      not null,
    country     varchar(255) not null,
    description varchar      not null,
    price       integer      not null,
    quantity    integer      not null,
    type        varchar
);

create table if not exists orders
(
    id           bigserial not null primary key,
    total_price  float,
    data         timestamp,
    first_name   varchar   not null,
    last_name    varchar   not null,
    city         varchar   not null,
    address      varchar   not null,
    email        varchar   not null,
    phone_number varchar   not null,
    post_index   integer   not null,
    good_id      bigserial not null references good (id)
);

create table if not exists usr
(
    id       bigserial not null primary key,
    username varchar   not null,
    password varchar   not null,
    email    varchar   not null,
    active   boolean   not null,
    quantity integer   not null
);

create table orders_perfume_list
(
    order_id        bigserial not null,
    perfume_list_id bigserial not null,
    good_list_order bigserial not null,
    primary key (order_id, good_list_order)
);



create table user_role
(
    user_id bigserial not null,
    roles   varchar(255)
);

create table usr_perfume_list
(
    user_id         bigserial not null,
    good_list_id bigserial not null
);

alter table if exists orders
    add constraint FK7ncuqw9n77odylknbo8aikc9w
        foreign key (id) references usr;

alter table if exists orders_good_list
    add constraint FKi6hpa14qaenek8pc9pf3vmlei
        foreign key (good_list_id) references good;

alter table if exists orders_perfume_list
    add constraint FK8jft4d30d5dgvauht7ssndwau
        foreign key (order_id) references orders;

alter table if exists user_role
    add constraint FKfpm8swft53ulq2hl11yplpr5
        foreign key (user_id) references usr;


alter table if exists usr_good_list
    add constraint FKc5b4lo20noteewtlrq1kd8nhs
        foreign key (user_id) references usr;