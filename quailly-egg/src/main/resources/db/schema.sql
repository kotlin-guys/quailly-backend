DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

CREATE TABLE account
(
    id                    serial,
    username              text,
    email                 text        NOT NULL,
    email_verified        boolean,
    first_name            text        NOT NULL,
    family_name           text        NOT NULL,
    given_name            text,
    picture_url           text,
    locale                text,
    password              text,
    phone_number          text,
    last_visit            timestamptz NOT NULL,
    registration_datetime timestamptz,
    token                 text
);

CREATE TABLE merchandise
(
    id          serial,
    name        text   NOT NULL,
    description text   NOT NULL,
    category_id bigint NOT NULL,
    author_id   bigint NOT NULL
);

CREATE TYPE swipe_direction AS ENUM ('left', 'right');

CREATE TABLE swipe
(
    id             serial,
    swiper_id      bigint          NOT NULL,
    merchandise_id bigint          NOT NULL,
    direction      swipe_direction NOT NULL
);

CREATE TABLE dialog
(
    id             serial,
    sender_id      bigint NOT NULL,
    destination_id bigint NOT NULL
);

CREATE TABLE message
(
    id             serial,
    dialog_id      bigint NOT NULL,
    sender_id      bigint NOT NULL,
    destination_id bigint NOT NULL,
    content        text   NOT NULL
);

CREATE TABLE exchange
(
    id                    serial,
    author_id             bigint NOT NULL,
    first_merchandise_id  bigint NOT NULL,
    second_merchandise_id bigint NOT NULL,
    publication_date_time bigint NOT NULL,
    status                text   NOT NULL
);

CREATE TABLE merchandise_category
(
    id   serial,
    name text NOT NULL
);

CREATE TABLE desired_merchandise_catalog
(
    merchandise_id bigint NOT NULL,
    category_id    bigint NOT NULL
);

BEGIN;

ALTER TABLE account
    ADD CONSTRAINT account_pk PRIMARY KEY (id);

ALTER TABLE merchandise_category
    ADD CONSTRAINT merchandise_category_pk PRIMARY KEY (id);

ALTER TABLE merchandise
    ADD CONSTRAINT merchandise_pk PRIMARY KEY (id),
    ADD CONSTRAINT merchandise_author_id_fk FOREIGN KEY (author_id) REFERENCES account (id),
    ADD CONSTRAINT merchandise_category_id_fk FOREIGN KEY (category_id) REFERENCES merchandise_category (id);


ALTER TABLE desired_merchandise_catalog
    ADD CONSTRAINT desired_merchandise_catalog_merchandise_id_fk FOREIGN KEY (merchandise_id) REFERENCES merchandise (id),
    ADD CONSTRAINT desired_merchandise_catalog_category_id_fk FOREIGN KEY (category_id) REFERENCES merchandise_category (id);

ALTER TABLE swipe
    ADD CONSTRAINT swipe_pk PRIMARY KEY (id),
    ADD CONSTRAINT swipe_swiper_id_fk FOREIGN KEY (swiper_id) REFERENCES account (id),
    ADD CONSTRAINT swipe_merchandise_id_fk FOREIGN KEY (merchandise_id) REFERENCES merchandise (id);

ALTER TABLE dialog
    ADD CONSTRAINT dialog_pk PRIMARY KEY (id),
    ADD CONSTRAINT dialog_sender_id_fk FOREIGN KEY (sender_id) REFERENCES account (id),
    ADD CONSTRAINT dialog_destination_id_fk FOREIGN KEY (destination_id) REFERENCES account (id);

ALTER TABLE message
    ADD CONSTRAINT message_pk PRIMARY KEY (id),
    ADD CONSTRAINT message_sender_id_fk FOREIGN KEY (sender_id) REFERENCES account (id),
    ADD CONSTRAINT message_destination_id_fk FOREIGN KEY (destination_id) REFERENCES account (id),
    ADD CONSTRAINT message_dialog_id_fk FOREIGN KEY (dialog_id) REFERENCES dialog (id);

ALTER TABLE exchange
    ADD CONSTRAINT exchange_pk PRIMARY KEY (id),
    ADD CONSTRAINT message_author_id_fk FOREIGN KEY (author_id) REFERENCES account (id),
    ADD CONSTRAINT exchange_first_merchandise_id_fk FOREIGN KEY (first_merchandise_id) REFERENCES merchandise (id),
    ADD CONSTRAINT exchange_second_merchandise_id_fk FOREIGN KEY (second_merchandise_id) REFERENCES merchandise (id);

COMMIT;
