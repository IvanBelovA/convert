CREATE TABLE users
(
    id       VARCHAR PRIMARY KEY NOT NULL,
    password VARCHAR
);

CREATE TABLE currency
(
    id          VARCHAR(7) PRIMARY KEY NOT NULL,
    name        VARCHAR(100)           NOT NULL,
    number_code VARCHAR(3)             NOT NULL,
    char_code   VARCHAR(3)             NOT NULL,
    nominal     INT                    NOT NULL
);

CREATE TABLE exchange_rate
(
    id             BIGSERIAL PRIMARY KEY NOT NULL,
    date           DATE                  NOT NULL,
    currency_from  VARCHAR REFERENCES currency (id),
    currency_to    VARCHAR REFERENCES currency (id),
    price_per_unit BIGSERIAL
);

CREATE TABLE exchange_history
(
    id           BIGSERIAL PRIMARY KEY NOT NULL,
    date         DATE                  NOT NULL,
    rate_from_id VARCHAR REFERENCES currency (id),
    rate_to_id   VARCHAR REFERENCES currency (id),
    quantity     BIGSERIAL             NOT NULL,
    sum          BIGSERIAL             NOT NULL,
    user_id      VARCHAR REFERENCES users (id)
);
