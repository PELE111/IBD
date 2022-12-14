Drop table if exists meal_ingredients;
Drop table if exists product_spec;
Drop table if exists product;
Drop table if exists user_meal;
Drop table if exists meal;
Drop table if exists diet;
Drop table if exists client;


CREATE TABLE client
(
    client_id SERIAL NOT NULL PRIMARY KEY,
    login VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE diet
(
    diet_id SERIAL not NULL PRIMARY KEY ,
    client_id INT NOT NULL references client(client_id),
    daily_calories INT,
    diabetes bool,
    active bool
);

CREATE TABLE meal
(
    meal_id SERIAL not null PRIMARY KEY ,
    name VARCHAR(255)
);

CREATE TABLE user_meal
(
    user_meal_id SERIAL not null PRIMARY KEY ,
    client_id INT references client(client_id),
    date DATE,
    meal_id INT references meal(meal_id)
);

CREATE TABLE product
(
    product_id SERIAL not null PRIMARY KEY ,
    name varchar(255)
);

CREATE TABLE product_spec
(
    product_spec_id SERIAL not null PRIMARY KEY ,
    product_id int references product(product_id),
    unit varchar(10),
    amount float,
    calories float,
    sugar float,
    fats float,
    protein float
);



CREATE TABLE meal_ingredients
(
    meal_ingredients_id SERIAL not null PRIMARY key ,
    meal_id INT references meal(meal_id),
    product_id INT references product(product_id),
    amount float
);

INSERT INTO public.client (client_id, login, password)
VALUES
(1, 'login', 'haslo'),
(2, 'klient', 'klient'),
(3, 'klient1', 'klient1');

INSERT INTO public.diet (diet_id, client_id, daily_calories, diabetes)
VALUES
(3, 2, 2000, true),
(4, 1, 1500, false),
(5, 3, 2500, false);

INSERT INTO public.meal (meal_id, name)
VALUES
(1, 'sniadanie'),
(2, 'obiad'),
(3, 'kolacja');

INSERT INTO public.product (product_id, name)
VALUES
    (3, 'szynka'),
    (4, 'ser żołty'),
    (5, 'pierś z kurczaka'),
    (1, 'jajko'),
    (2, 'mleko');

INSERT INTO public.meal_ingredients (meal_ingredients_id, meal_id, product_id, amount)
VALUES
(2, 1, 1, 2),
(3, 1, 2, 250);



INSERT INTO public.product_spec (product_spec_id, product_id, unit, amount, calories, sugar, fats, protein)
VALUES
(1, 1 ,'g', 100, 143, 0.72, 9.51, 12.5),
(2, 2, 'ml', 100, 100, null, null, null);

INSERT INTO public.user_meal (user_meal_id, client_id, date, meal_id)
VALUES
(1, 2, '2022-12-01', 1);







