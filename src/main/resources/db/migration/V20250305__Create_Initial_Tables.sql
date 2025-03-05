CREATE TABLE invoice
(
    id VARCHAR(36)
);

CREATE TABLE invoice_type
(
    id VARCHAR(36)
);

CREATE TABLE virtual_account
(
    id VARCHAR(36)
);

CREATE TABLE payment
(
    id VARCHAR(36)
);

CREATE TABLE payment_provider
(
    id VARCHAR(36),
    code VARCHAR(100) NOT NULL,
    name VARCHAR(100) NOT NULL
);

-- ============================================ Constraint Primary Key =================================================
ALTER TABLE invoice
    ADD CONSTRAINT invoice_id_pk PRIMARY KEY (id);

ALTER TABLE invoice_type
    ADD CONSTRAINT invoice_type_id_pk PRIMARY KEY (id);

ALTER TABLE virtual_account
    ADD CONSTRAINT virtual_account_id_pk PRIMARY KEY (id);

ALTER TABLE payment
    ADD CONSTRAINT payment_id_pk PRIMARY KEY (id);

ALTER TABLE payment_provider
    ADD CONSTRAINT payment_provider_id_pk PRIMARY KEY (id);

-- ============================================ Constraint UNIQUE =================================================
ALTER TABLE payment_provider
    ADD CONSTRAINT payment_provider_code_unique UNIQUE (code);