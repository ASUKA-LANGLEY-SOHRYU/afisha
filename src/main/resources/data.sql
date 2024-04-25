INSERT INTO event (price, name, date_time, organization) VALUES (100, 'Test Event 1', '2023-01-01 12:00:00', 'Test Organization 1');
INSERT INTO event (price, name, date_time, organization) VALUES (200, 'Test Event 2', '2022-01-02 12:00:00', 'Test Organization 2');

INSERT INTO usr (last_name, first_name, patronymic, phone_number, email, birth_date, password) VALUES ('Иванов', 'Иван', 'Иванович', '1234567890', 'test@example.com', '2000-01-01', '123');
INSERT INTO usr (last_name, first_name, patronymic, phone_number, email, birth_date, password) VALUES ('Петров', 'Петр', 'Петрович', '1234567890', 'test2@example.com', '2001-01-01', '123');

INSERT INTO ordr (user_id, event_id) VALUES (1, 1);
INSERT INTO ordr (user_id, event_id) VALUES (1, 2);
INSERT INTO ordr (user_id, event_id) VALUES (2, 1);