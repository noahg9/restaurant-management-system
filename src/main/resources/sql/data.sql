INSERT INTO restaurant (name, date_established, seating_capacity)
VALUES ('San Remo', '1989-01-14', 20),
       ('Zeus', '2001-04-13', 23);

INSERT INTO chef (first_name, last_name, date_of_birth, username, password, role, restaurant_id)
VALUES ('Noah', 'Guerin', '2002-09-12', 'noahg', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6',
        'Admin', 1),
       ('Lars', 'Willemsens', '1994-09-12', 'larsw', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6',
        'Admin', 1),
       ('Gordon', 'Ramsey', '1966-11-08', 'gordonr', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6',
        'Chef', 2),
       ('Jamie', 'Oliver', '1975-05-27', 'jamieo', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6',
        'Chef', 2),
       ('Joan', 'Roca', '1964-02-11', 'joanr', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6',
        'Chef', 1),
       ('David', 'Chang', '1977-08-05', 'davidc', '$2a$10$Ym.DBt/FfM8jW9jBm9rrpeqsEqZXiKi3R5hm626nzvuQnxIJVIIN6',
        'Chef', 1);

INSERT INTO menu_item (name, price, course, vegetarian, spice_lvl, restaurant_id)
VALUES ('Ceasar Salad', 3.5, 'Main', FALSE, 0, 1),
       ('Grilled Salmon', 5, 'Main', FALSE, 2, 1),
       ('Spaghetti Carbonara', 5, 'Main', FALSE, 0, 2),
       ('Vanilla Ice Cream', 1.5, 'Dessert', TRUE, 0, 1);

INSERT INTO menu_item_chef (menu_item_id, chef_id)
VALUES (1, 1),
       (1, 2),
       (2, 1),
       (2, 6),
       (3, 3),
       (3, 4),
       (4, 1),
       (4, 2),
       (4, 5);
