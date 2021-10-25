create table if not exists users
(
    id bigserial
        constraint users_pk
            primary key,
    name varchar(100) not null,
    surname varchar(100) not null,
    phone bigint not null,
    passport_series varchar(20) not null,
    passport_number integer not null,
    email varchar(50) not null,
    driver_license_number integer not null,
    login varchar(50) not null,
    password varchar(100),
    gender varchar(30) default 'NOT_SELECTED'::character varying not null
);

alter table users owner to postgres;

create index if not exists users_login_index
    on users (login);

create index if not exists users_name_index
    on users (name);

create index if not exists users_name_surname_index
    on users (name, surname);

create index if not exists users_password_index
    on users (password);

create index if not exists users_surname_index
    on users (surname);

create table if not exists roles
(
    id serial
        constraint role_pk
            primary key,
    role_name varchar(30) not null
);

alter table roles owner to postgres;

create unique index if not exists role_role_name_uindex
    on roles (role_name);

create table if not exists user_roles
(
    id bigserial
        constraint user_roles_pk
            primary key,
    role_id bigint not null
        constraint user_roles_roles_id_fk
            references roles,
    users_id bigint not null
        constraint user_roles_users_id_fk
            references users
);

alter table user_roles owner to postgres;

create index if not exists user_roles_id_index
    on user_roles (id);

create index if not exists user_roles_role_id_index
    on user_roles (role_id);

create index if not exists user_roles_role_id_users_id_index
    on user_roles (role_id, users_id);

create index if not exists user_roles_users_id_index
    on user_roles (users_id);

create table if not exists cars
(
    id bigserial
        constraint cars_pk
            primary key,
    name_car varchar(30) not null,
    model varchar(30) not null,
    release_date timestamp not null,
    color varchar(20) not null,
    v_motor double precision not null,
    power double precision not null,
    transmission varchar(30) not null,
    cost_per_day double precision not null,
    car_status varchar(30) not null,
    discount_id bigint
);

alter table cars owner to postgres;

create table if not exists orders
(
    id bigserial
        constraint orders_pk
            primary key,
    received_date timestamp not null,
    return_date timestamp not null,
    order_status varchar(20) default 'NOT_CONFIRMED'::character varying not null,
    user_id bigint not null
        constraint orders_users_id_fk
            references users,
    car_id bigint not null
        constraint orders_cars_id_fk
            references cars,
    bill_id bigint not null
);

alter table orders owner to postgres;

create unique index if not exists cars_id_uindex
    on cars (id);

create index if not exists cars_car_status_index
    on cars (car_status);

create index if not exists cars_cost_per_day_index
    on cars (cost_per_day);

create index if not exists cars_name_car_index
    on cars (name_car);

create index if not exists cars_name_car_model_index
    on cars (name_car, model);

create index if not exists cars_transmission_index
    on cars (transmission);

create table if not exists bills
(
    id bigserial
        constraint bills_pk
            primary key,
    number_bill integer not null,
    payment_date timestamp not null,
    total_price integer not null,
    bill_status varchar(30) default 'AWAITING_PAYMENT'::character varying not null,
    order_id bigint not null
        constraint bills_orders_id_fk
            references orders
);

alter table bills owner to postgres;

alter table orders
    add constraint orders_bills_id_fk
        foreign key (bill_id) references bills;

create index if not exists bills_bill_status_index
    on bills (bill_status);

create unique index if not exists bills_id_uindex
    on bills (id);

create index if not exists bills_payment_date_index
    on bills (payment_date);

create index if not exists bills_total_price_index
    on bills (total_price);

create table if not exists discount
(
    id bigserial
        constraint discount_pk
            primary key,
    percentages integer not null,
    start_date timestamp not null,
    end_date timestamp not null,
    car_id bigint not null
        constraint discount_cars_id_fk
            references cars
);

alter table discount owner to postgres;

alter table cars
    add constraint cars_discount_id_fk
        foreign key (discount_id) references discount;

create unique index if not exists discount_id_uindex
    on discount (id);

create index if not exists discount_start_date_end_date_index
    on discount (start_date, end_date);

create index if not exists discount_start_date_index
    on discount (start_date);

