INSERT INTO public.users ( name, surname, phone, passport_series, passport_number, email, driver_license_number, login, password, gender, is_deleted) VALUES ( 'qwq', 'eqwe', 42342, 'tr', 34564, 'dfgdg', 345634634, 'q', '123', 'MALE', false);
INSERT INTO public.users ( name, surname, phone, passport_series, passport_number, email, driver_license_number, login, password, gender, is_deleted) VALUES ( 'q', 'qwe', 0, 'string', 0, 'string', 0, 'string', '12', 'MALE', false);
INSERT INTO public.users ( name, surname, phone, passport_series, passport_number, email, driver_license_number, login, password, gender, is_deleted) VALUES ( 'dima', 'rer', 4343, 'gf', 43, 'fgrege', 24452542, 'rfrfr', '2', 'MALE', false);
INSERT INTO public.users ( name, surname, phone, passport_series, passport_number, email, driver_license_number, login, password, gender, is_deleted) VALUES ( 'vanya', 'rere', 4364, 'rt', 23523, 'ter', 53425, 'van', '2', 'MALE', false);
INSERT INTO public.users ( name, surname, phone, passport_series, passport_number, email, driver_license_number, login, password, gender, is_deleted) VALUES ( 'g', 'g', 6, 'g', 6, 'g', 3, 'g', 'е', 'MALE', false);

INSERT INTO public.discount ( percentages, start_date, end_date) VALUES ( 20, '2021-11-05 13:51:31.000000', '2021-11-15 13:51:33.000000');
INSERT INTO public.discount ( percentages, start_date, end_date) VALUES ( 14, '2021-11-08 10:02:22.000000', '2021-11-15 10:02:28.000000');
INSERT INTO public.discount ( percentages, start_date, end_date) VALUES ( 12, '2021-12-01 15:12:12.000000', '2021-12-31 15:12:12.000000');
INSERT INTO public.discount ( percentages, start_date, end_date) VALUES ( 20, '2022-01-01 15:12:12.000000', '2022-01-10 15:12:12.000000');




INSERT INTO public.cars ( name_car, model, release_date, color, v_motor, power, transmission, cost_per_day, car_status, discount_id, link_photo) VALUES ('opel', 'astra', '2020-10-28 10:52:48.000000', 'white', 1.6, 100, 'AUTO', 20, 'AVAILABLE', 2, 'dgfsd');
INSERT INTO public.cars ( name_car, model, release_date, color, v_motor, power, transmission, cost_per_day, car_status, discount_id, link_photo) VALUES ('mercedes', 'w123', '1982-10-29 09:51:20.000000', 'black', 2, 100, 'AUTO', 20, 'AVAILABLE', 2, 'dsvsdv');
INSERT INTO public.cars ( name_car, model, release_date, color, v_motor, power, transmission, cost_per_day, car_status, discount_id, link_photo) VALUES ('volvo', '850', '2004-10-29 11:35:32.000000', 'blue', 2.5, 150, 'AUTO', 30, 'AVAILABLE', 2, '2525');
INSERT INTO public.cars ( name_car, model, release_date, color, v_motor, power, transmission, cost_per_day, car_status, discount_id, link_photo) VALUES ( 'kalina', 'vaz', '2018-01-12 15:12:12.000000', 'white', 1.5, 90, 'ROBOT', 50, 'NOT_AVAILABLE', null, 'string');
INSERT INTO public.cars ( name_car, model, release_date, color, v_motor, power, transmission, cost_per_day, car_status, discount_id, link_photo) VALUES ( 's4', 'audi', '2015-01-12 15:12:12.000000', 'white', 3.2, 200, 'ROBOT', 40, 'NOT_AVAILABLE', null, 'wetwtw');
INSERT INTO public.cars ( name_car, model, release_date, color, v_motor, power, transmission, cost_per_day, car_status, discount_id, link_photo) VALUES ( 's8', 'audi', '2015-01-12 15:12:12.000000', 'white', 3.2, 400, 'ROBOT', 40, 'NOT_AVAILABLE', 6, 'twtw');
INSERT INTO public.cars ( name_car, model, release_date, color, v_motor, power, transmission, cost_per_day, car_status, discount_id, link_photo) VALUES ( 'Q8', 'audi', '2015-01-12 15:12:12.000000', 'black', 5, 500, 'ROBOT', 40, 'NOT_AVAILABLE', 6, '');



INSERT INTO public.roles (role_name) VALUES ( 'USER');
INSERT INTO public.roles (role_name) VALUES ( 'ADMIN');
INSERT INTO public.roles (role_name) VALUES ( '');
INSERT INTO public.roles (role_name) VALUES ( '"SUPERADMIN"');


INSERT INTO public.user_roles ( role_id, users_id) VALUES (1, 1);
INSERT INTO public.user_roles ( role_id, users_id) VALUES (1, 2);
INSERT INTO public.user_roles ( role_id, users_id) VALUES (1, 4);
INSERT INTO public.user_roles ( role_id, users_id) VALUES ( 3, 4);
INSERT INTO public.user_roles ( role_id, users_id) VALUES ( 2, 4);
INSERT INTO public.user_roles ( role_id, users_id) VALUES ( 5, 4);


INSERT INTO public.orders ( received_date, return_date, order_status, user_id, car_id) VALUES ('2021-10-29 12:32:27.000000', '2021-10-31 12:32:39.000000', 'NOT_CONFIRMED', 1, 5);
INSERT INTO public.orders ( received_date, return_date, order_status, user_id, car_id) VALUES ( '2021-12-12 15:12:12.000000', '2021-12-25 15:12:12.000000', 'CONFIRMED', 1, 6);
INSERT INTO public.orders ( received_date, return_date, order_status, user_id, car_id) VALUES ('2021-12-12 15:12:12.000000', '2021-12-16 15:12:12.000000', 'CONFIRMED', 2, 6);




INSERT INTO public.bills ( number_bill, create_date, total_price, bill_status, order_id) VALUES ( 345345, '2021-10-28 12:32:05.000000', 50, 'AWAITING_PAYMENT', 3);
INSERT INTO public.bills ( number_bill, create_date, total_price, bill_status, order_id) VALUES ( 23131, '2021-11-03 16:28:30.642696', 80, 'AWAITING_PAYMENT', null);
INSERT INTO public.bills ( number_bill, create_date, total_price, bill_status, order_id) VALUES ( 1231, '2021-11-03 16:47:15.588086', 80, 'AWAITING_PAYMENT', 5);
INSERT INTO public.bills ( number_bill, create_date, total_price, bill_status, order_id) VALUES ( 231341, '2021-11-03 17:08:53.179678', 234, 'AWAITING_PAYMENT', 16);
INSERT INTO public.bills ( number_bill, create_date, total_price, bill_status, order_id) VALUES ( 213, '2021-11-03 17:20:26.646732', 234, 'AWAITING_PAYMENT', 16);