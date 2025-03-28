/*
 Requests for table "users"
 */
select * from users where id = 1;
select * from users where fio = 'Ян Градов';
update users set fio = 'Анна Столярова' where fio = 'Анна Иванова';
update users set fio = 'Лилия Кустова' where id = 9;

/*
 Requests for table "product"
 */

select * from product where description = 'лук';
select * from product where cost = 100;
select * from product where count > 70;
update product set cost = 60 where description = 'Хлеб';

/*
Requests for table "orders"
 */
delete from orders where user_id = 1;
delete from orders where fk_product_id IS NULL;
update orders set fk_product_id = 9 where fk_product_id = 1;