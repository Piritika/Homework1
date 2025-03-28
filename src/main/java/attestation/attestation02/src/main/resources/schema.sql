create table if not exists users (
    id bigserial primary key,
    fio varchar
);

comment on table users is 'Таблица покупателей';

create table if not exists orders (
    fk_product_id bigint,
    user_id bigint references users (id),
    date date,
    count_of_orders numeric
);

comment on table orders is 'Таблица заказов';

create table if not exists product (
    id bigserial primary key,
    description varchar,
    cost bigint,
    count bigint
);

comment on table product is 'Таблица продуктов';