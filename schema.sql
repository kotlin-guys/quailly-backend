CREATE TABLE account
(
    id                   serial,
    username             text,
    email                text        NOT NULL,
    emailVerified        boolean,
    firstName            text        NOT NULL,
    familyName           text        NOT NULL,
    givenName            text,
    pictureUrl           text,
    locale               text,
    password             text,
    phoneNumber          text,
    lastVisit            timestamptz NOT NULL,
    registrationDateTime timestamptz,
    token                text
);

CREATE TABLE merchandise
(
    id          serial,
    name        text,
    description text,
    category    text,
    authorId    bigint
);


CREATE TABLE swipe_history
(
    id            serial,
    swiperId      bigint,
    merchandiseId bigint,
    result        text
);

CREATE TABLE dialog
(
    id            serial,
    senderId      bigint,
    destinationId bigint
);

CREATE TABLE message
(
    id            serial,
    senderId      bigint,
    destinationId bigint,
    content       text
);

CREATE TABLE exchange
(
    id                  serial,
    firstMerchandiseId  bigint,
    secondMerchandiseId bigint,
    publicationDateTime bigint,
    status              text,
    exchangeStatus      text
);



ALTER TABLE account
    ADD CONSTRAINT account_pk PRIMARY KEY (id);

ALTER TABLE merchandise
    ADD CONSTRAINT merchandise_pk PRIMARY KEY (id),
    ADD CONSTRAINT merchandise_authorId_fk FOREIGN KEY (authorId) REFERENCES account (id);

ALTER TABLE swipe_history
    ADD CONSTRAINT swipe_history_pk PRIMARY KEY (id);

ALTER TABLE dialog
    ADD CONSTRAINT dialog_pk PRIMARY KEY (id);

ALTER TABLE message
    ADD CONSTRAINT message_pk PRIMARY KEY (id);

ALTER TABLE exchange
    ADD CONSTRAINT exchange_pk PRIMARY KEY (id);