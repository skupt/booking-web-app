INSERT INTO user_impl (id, name, email) VALUES (-1, 'Vasya', 'e@e');
INSERT INTO user_impl (id, name, email) VALUES (-2, 'Serg', 'e1@e');
INSERT INTO user_impl (id, name, email) VALUES (-3, 'Anna', 'e2@e');

INSERT INTO event (id, title, date, price) VALUES (-1, 'Cinema', '2022-06-28', 50);
INSERT INTO event (id, title, date, price) VALUES (-2, 'Theatre', '2022-06-28', 100);
INSERT INTO event (id, title, date, price) VALUES (-3, 'Circus', '2022-06-29', 150);

INSERT INTO ticket (id, event_id, user_id, category, place) VALUES (-1, -1, -1, 'STANDARD', 1);
INSERT INTO ticket (id, event_id, user_id, category, place) VALUES (-2, -1, -2, 'STANDARD', 2);
INSERT INTO ticket (id, event_id, user_id, category, place) VALUES (-3, -1, -3, 'STANDARD', 3);
INSERT INTO ticket (id, event_id, user_id, category, place) VALUES (-4, -1, -1, 'PREMIUM', 1);
INSERT INTO ticket (id, event_id, user_id, category, place) VALUES (-5, -1, -1, 'BAR', 1);

INSERT INTO user_account (user_id, money_amount) VALUES (-1, 1000);
INSERT INTO user_account (user_id, money_amount) VALUES (-2, 2000);
INSERT INTO user_account (user_id, money_amount) VALUES (-3, 3000);
