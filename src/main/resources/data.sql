-- Insert data into RESTAURANTS table
insert into restaurants.RESTAURANTS (NAME, DATE_ESTABLISHED, SEATING_CAPACITY)
values
    ('San Remo', '1989-01-14', 20),
    ('Zeus', '2001-04-13', 23);

-- Insert data into CHEFS table
insert into restaurants.CHEFS (FIRST_NAME, LAST_NAME, DATE_OF_BIRTH, RESTAURANT_ID)
values
    ('Tom', 'Hanks', '1981-04-22', 2),
    ('Gordon', 'Ramsey', '1978-09-08', 2),
    ('Mary', 'Poppins', '1971-12-20', 1),
    ('Billie', 'Eilish', '2004-02-11', 1);

-- Insert data into MENU_ITEMS table
insert into restaurants.MENU_ITEMS (NAME, PRICE, COURSE, VEGETARIAN, SPICE_LVL, RESTAURANT_ID)
values
    ('Beef burger', 3.5, 'Main', FALSE, 0, 1),
    ('Red pepper pizza', 5, 'Main', FALSE, 2, 1),
    ('Spaghetti carbonara', 5, 'Main', FALSE, 0, 2),
    ('Vanilla ice Cream', 1.5, 'Dessert', TRUE, 0, 1);

-- Insert data into MENU_ITEMS_CHEFS table
insert into restaurants.MENU_ITEMS_CHEFS (MENU_ITEM_ID, CHEF_ID)
values
    (1, 1),
    (1, 2),
    (2, 1),
    (2, 3),
    (3, 4),
    (4, 1),
    (4, 2),
    (4, 4);
