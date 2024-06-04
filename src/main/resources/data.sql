INSERT INTO event (price, name, date_time, organization_id) VALUES (100, 'Test Event 1', '2023-01-01 12:00:00', 1);
INSERT INTO event (price, name, date_time, organization_id) VALUES (200, 'Test Event 2', '2022-01-02 12:00:00', 1);
INSERT INTO event (price, name, date_time, organization_id) VALUES (200, 'Test Event 3', '2024-01-02 12:00:00', 1);


INSERT INTO usr (last_name, first_name, patronymic, phone_number, email, birth_date, password) VALUES ('Иванов', 'Иван', 'Иванович', '1234567890', 'test@example.com', '2000-01-01', '123');
INSERT INTO user_role (user_id, role) VALUES ((SELECT id FROM usr WHERE email = 'test@example.com'), 'ROLE_ORGANIZER');
INSERT INTO usr (last_name, first_name, patronymic, phone_number, email, birth_date, password) VALUES ('Петров', 'Петр', 'Петрович', '1234567890', 'test2@example.com', '2001-01-01', '123');
INSERT INTO user_role (user_id, role) VALUES ((SELECT id FROM usr WHERE email = 'test2@example.com'), 'ROLE_USER');
INSERT INTO usr (last_name, first_name, patronymic, phone_number, email, birth_date, password) VALUES ('Михайлов', 'Михаил', 'Михайлович', '1234567890', 'test3@example.com', '2002-02-02', '123');
INSERT INTO user_role (user_id, role) VALUES ((SELECT id FROM usr WHERE email = 'test3@example.com'), 'ROLE_ADMIN');


INSERT INTO ordr (user_id, event_id) VALUES (1, 1);
INSERT INTO ordr (user_id, event_id) VALUES (1, 2);
INSERT INTO ordr (user_id, event_id) VALUES (2, 1);