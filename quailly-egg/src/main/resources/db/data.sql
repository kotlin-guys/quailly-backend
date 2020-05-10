BEGIN;

INSERT INTO merchandise_category(name)
VALUES ('Автотовары'),
       ('Аксессуары'),
       ('Аудио-видео техника'),
       ('Бытовая техника'),
       ('Бытовая химия'),
       ('Гигиена'),
       ('Детская одежда'),
       ('Женская одежда'),
       ('Игрушки'),
       ('Интерьер'),
       ('Канцтовары'),
       ('Косметика'),
       ('Мебель для дома'),
       ('Мужская одежда'),
       ('Мультимедиа'),
       ('Обувь'),
       ('Книги'),
       ('Посуда'),
       ('Праздничные товары'),
       ('Техника для офиса'),
       ('Товары для новорожденных'),
       ('Товары для отдыха'),
       ('Товары для сада, дачи'),
       ('Товары для спорта'),
       ('Компьютеры и ноутбуки'),
       ('Домашние питомцы'),
       ('Телефоны');

INSERT INTO account(username, email, email_verified, first_name, family_name, given_name, picture_url, locale,
                    password, phone_number, last_visit, registration_datetime, token)
VALUES ('iapolly', 'iapolly@test.com', true, 'Полина', 'Аликина', null, null, null, null, '2906090', now(), now(),
        'iapolly');

INSERT INTO account(username, email, email_verified, first_name, family_name, given_name, picture_url, locale,
                    password, phone_number, last_visit, registration_datetime, token)
VALUES ('emilg1101', 'emilg1101@test.com', true, 'Эмиль', 'Гафиятуллин', null, null, null, null, '88005553535', now(),
        now(), 'emilg1101');

INSERT INTO account(username, email, email_verified, first_name, family_name, given_name, picture_url, locale,
                    password, phone_number, last_visit, registration_datetime, token)
VALUES ('jaskelai', 'jaskelai@test.com', true, 'Михаил', 'Корнилов', null, null, null, null, '911', now(), now(),
        'jaskelai');

INSERT INTO account(username, email, email_verified, first_name, family_name, given_name, picture_url, locale,
                    password, phone_number, last_visit, registration_datetime, token)
VALUES ('phoenigm', 'phoenigm@test.com', true, 'Азат', 'Мухаметзянов', null, null, null, null, '112', now(), now(),
        'phoenigm');

INSERT INTO merchandise(name, description, category_id, author_id, created, picture_url)
VALUES ('Кровать', 'Кровать',
        (SELECT id FROM merchandise_category WHERE name = 'Товары для отдыха'),
        (SELECT id FROM account WHERE username = 'iapolly'),
        now(), 'not defined');

INSERT INTO merchandise(name, description, category_id, author_id, created, picture_url)
VALUES ('Подушка', 'Подушка',
        (SELECT id FROM merchandise_category WHERE name = 'Товары для отдыха'),
        (SELECT id FROM account WHERE username = 'iapolly'),
        now(), 'not defined');

INSERT INTO merchandise(name, description, category_id, author_id, created, picture_url)
VALUES ('Ноутбук', 'Обменяю',
        (SELECT id FROM merchandise_category WHERE name = 'Компьютеры и ноутбуки'),
        (SELECT id FROM account WHERE username = 'iapolly'),
        now(), 'not defined');

INSERT INTO merchandise(name, description, category_id, author_id, created, picture_url)
VALUES ('Кошка Киви', 'Отдам даром и даже заплачу, если заберете! Только заберите!!!!!',
        (SELECT id FROM merchandise_category WHERE name = 'Игрушки'),
        (SELECT id FROM account WHERE username = 'phoenigm'),
        now(), 'not defined');

INSERT INTO merchandise(name, description, category_id, author_id, created, picture_url)
VALUES ('Клавиатура', 'Оклик',
        (SELECT id FROM merchandise_category WHERE name = 'Техника для офиса'),
        (SELECT id FROM account WHERE username = 'phoenigm'),
        now(), 'not defined');

INSERT INTO merchandise(name, description, category_id, author_id, created, picture_url)
VALUES ('Макбук', 'мак мусор, обменяю на животное',
        (SELECT id FROM merchandise_category WHERE name = 'Компьютеры и ноутбуки'),
        (SELECT id FROM account WHERE username = 'emilg1101'),
        now(), 'not defined');

INSERT INTO merchandise(name, description, category_id, author_id, created, picture_url)
VALUES ('Айфон 10', 'обменяюсь на женскую одежду',
        (SELECT id FROM merchandise_category WHERE name = 'Телефоны'),
        (SELECT id FROM account WHERE username = 'jaskelai'),
        now(), 'not defined');

INSERT INTO desired_merchandise_catalog(merchandise_id, category_id)
VALUES ((SELECT id FROM merchandise WHERE name = 'Кровать'),
        (SELECT id FROM merchandise_category WHERE name = 'Телефоны')),
       ((SELECT id FROM merchandise WHERE name = 'Кровать'),
        (SELECT id FROM merchandise_category WHERE name = 'Мебель для дома')),
       ((SELECT id FROM merchandise WHERE name = 'Кровать'),
        (SELECT id FROM merchandise_category WHERE name = 'Женская одежда')),
       ((SELECT id FROM merchandise WHERE name = 'Подушка'),
        (SELECT id FROM merchandise_category WHERE name = 'Мебель для дома')),
       ((SELECT id FROM merchandise WHERE name = 'Ноутбук'),
        (SELECT id FROM merchandise_category WHERE name = 'Компьютеры и ноутбуки')),
       ((SELECT id FROM merchandise WHERE name = 'Ноутбук'),
        (SELECT id FROM merchandise_category WHERE name = 'Телефоны')),
       ((SELECT id FROM merchandise WHERE name = 'Ноутбук'),
        (SELECT id FROM merchandise_category WHERE name = 'Аудио-видео техника')),
       ((SELECT id FROM merchandise WHERE name = 'Кошка Киви'),
        (SELECT id FROM merchandise_category WHERE name = 'Телефоны')),
       ((SELECT id FROM merchandise WHERE name = 'Кошка Киви'),
        (SELECT id FROM merchandise_category WHERE name = 'Автотовары')),
       ((SELECT id FROM merchandise WHERE name = 'Кошка Киви'),
        (SELECT id FROM merchandise_category WHERE name = 'Аудио-видео техника')),
       ((SELECT id FROM merchandise WHERE name = 'Кошка Киви'),
        (SELECT id FROM merchandise_category WHERE name = 'Компьютеры и ноутбуки')),
       ((SELECT id FROM merchandise WHERE name = 'Кошка Киви'),
        (SELECT id FROM merchandise_category WHERE name = 'Мебель для дома')),
       ((SELECT id FROM merchandise WHERE name = 'Кошка Киви'),
        (SELECT id FROM merchandise_category WHERE name = 'Техника для офиса')),
       ((SELECT id FROM merchandise WHERE name = 'Кошка Киви'),
        (SELECT id FROM merchandise_category WHERE name = 'Домашние питомцы')),
       ((SELECT id FROM merchandise WHERE name = 'Кошка Киви'),
        (SELECT id FROM merchandise_category WHERE name = 'Товары для отдыха')),
       ((SELECT id FROM merchandise WHERE name = 'Кошка Киви'),
        (SELECT id FROM merchandise_category WHERE name = 'Книги')),
       ((SELECT id FROM merchandise WHERE name = 'Кошка Киви'),
        (SELECT id FROM merchandise_category WHERE name = 'Косметика')),
       ((SELECT id FROM merchandise WHERE name = 'Кошка Киви'),
        (SELECT id FROM merchandise_category WHERE name = 'Товары для новорожденных')),
       ((SELECT id FROM merchandise WHERE name = 'Клавиатура'),
        (SELECT id FROM merchandise_category WHERE name = 'Игрушки')),
       ((SELECT id FROM merchandise WHERE name = 'Макбук'),
        (SELECT id FROM merchandise_category WHERE name = 'Игрушки')),
       ((SELECT id FROM merchandise WHERE name = 'Айфон 10'),
        (SELECT id FROM merchandise_category WHERE name = 'Игрушки'));

COMMIT;
