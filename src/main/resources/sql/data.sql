INSERT INTO chef (first_name, last_name, date_of_birth, username, password, role)
VALUES ('Noah', 'Guerin', '2002-09-12', 'noahg', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6',
        'Admin'),
       ('Lars', 'Willemsens', '1994-09-12', 'larsw', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6',
        'Admin'),
       ('Gordon', 'Ramsey', '1966-11-08', 'gordonr', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6',
        'Chef'),
       ('Jamie', 'Oliver', '1975-05-27', 'jamieo', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6',
        'Chef'),
       ('Joan', 'Roca', '1964-02-11', 'joanr', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6',
        'Chef'),
       ('David', 'Chang', '1977-08-05', 'davidc', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6',
        'Chef');

INSERT INTO menu_item (name, price, course, vegetarian, spice_lvl)
VALUES ('Ceasar Salad', 3.5, 'Main', FALSE, 0),
       ('Grilled Salmon', 5, 'Main', FALSE, 2),
       ('Spaghetti Carbonara', 5, 'Main', FALSE, 0),
       ('Vanilla Ice Cream', 1.5, 'Dessert', TRUE, 0);

INSERT INTO menu_item_chef (menu_item_id, chef_id, assigned_date_time)
VALUES (1, 1, '2024-03-01 12:00:00'),
       (1, 2, '2024-03-02 12:00:00'),
       (2, 1, '2024-03-03 12:00:00'),
       (2, 6, '2024-03-04 12:00:00'),
       (3, 3, '2024-03-05 12:00:00'),
       (3, 4, '2024-03-06 12:00:00'),
       (4, 1, '2024-03-07 12:00:00'),
       (4, 2, '2024-03-08 12:00:00'),
       (4, 5, '2024-03-09 12:00:00');
