insert into user (user_name, user_address) values ('Jon Snow', 'Castle Black');
insert into user (user_name, user_address) values ('Dany Targaryen', 'Dragonstone');
insert into user (user_name, user_address) values ('Ned Stark', 'Winterfell');
insert into user (user_name, user_address) values ('Tywin Lannister', 'Casterly Rock');
insert into user (user_name, user_address) values ('Retyr Baelish', 'Riverrun');

insert into card (card_number, balance, daily_limit, expiration_date, is_activated, card_holder_id)
values ('4024007170581887', 100, 10, TO_DATE('1-DEC-2024', 'DD-MON-YYYY'), FALSE, (select id from user where user_name = 'Jon Snow'));

insert into card (card_number, balance, daily_limit, expiration_date, is_activated, card_holder_id)
values ('5265227000100063', 4386, 789, TO_DATE('1-SEP-2023', 'DD-MON-YYYY'), TRUE, (select id from user where user_name = 'Dany Targaryen'));

insert into card (card_number, balance, daily_limit, expiration_date, is_activated, card_holder_id)
values ('5522800574180809', 34782364322, 100, TO_DATE('1-MAY-2019', 'DD-MON-YYYY'), FALSE, (select id from user where user_name = 'Dany Targaryen'));

insert into card (card_number, balance, daily_limit, expiration_date, is_activated, card_holder_id)
values ('5281492384844245', 875836, 382, TO_DATE('1-AUG-2019', 'DD-MON-YYYY'), TRUE, (select id from user where user_name = 'Dany Targaryen'));

insert into card (card_number, balance, daily_limit, expiration_date, is_activated, card_holder_id)
values ('5302290447199242', 8473526375612637928109.323, 34423, TO_DATE('1-OCT-2020', 'DD-MON-YYYY'), FALSE, (select id from user where user_name = 'Tywin Lannister'));

insert into card (card_number, balance, daily_limit, expiration_date, is_activated, card_holder_id)
values ('345458473195745', 144321, 1331, TO_DATE('1-APR-2020', 'DD-MON-YYYY'), FALSE, (select id from user where user_name = 'Tywin Lannister'));

insert into card (card_number, balance, daily_limit, expiration_date, is_activated, card_holder_id)
values ('4485157564282519', 32421421, 3423, TO_DATE('1-JUN-2019', 'DD-MON-YYYY'), TRUE, (select id from user where user_name = 'Tywin Lannister'));

insert into card (card_number, balance, daily_limit, expiration_date, is_activated, card_holder_id)
values ('5252893653264111', 234324213.3422, 34242, TO_DATE('1-FEB-2022', 'DD-MON-YYYY'), FALSE, (select id from user where user_name = 'Retyr Baelish'));

insert into card (card_number, balance, daily_limit, expiration_date, is_activated, card_holder_id)
values ('372043802524827', 138297837.989, 86536.5, TO_DATE('1-DEC-2019', 'DD-MON-YYYY'), FALSE, (select id from user where user_name = 'Retyr Baelish'));

insert into card (card_number, balance, daily_limit, expiration_date, is_activated, card_holder_id)
values ('4024007143743572', 83487630237.78768, 5362, TO_DATE('1-DEC-2030', 'DD-MON-YYYY'), TRUE, (select id from user where user_name = 'Retyr Baelish'));

insert into card (card_number, balance, daily_limit, expiration_date, is_activated, card_holder_id)
values ('4929323891053486', 0.0, 0.0, TO_DATE('1-NOV-2021', 'DD-MON-YYYY'), FALSE, (select id from user where user_name = 'Retyr Baelish'));

insert into card (card_number, balance, daily_limit, expiration_date, is_activated, card_holder_id)
values ('371956467105023', 78756767, 677, TO_DATE('1-DEC-2022', 'DD-MON-YYYY'), TRUE, (select id from user where user_name = 'Retyr Baelish'));

insert into card (card_number, balance, daily_limit, expiration_date, is_activated, card_holder_id)
values ('6011849271192019', 1000000, 0, TO_DATE('1-JUL-2019', 'DD-MON-YYYY'), FALSE, (select id from user where user_name = 'Retyr Baelish'));

