CREATE TABLE running_number
(
    id     character varying(255) NOT NULL,
    prefix character varying(100) not null,
    last_number bigint not null
);

ALTER TABLE running_number
    ADD CONSTRAINT pkey_id__running_number PRIMARY KEY (id);

--
-- =====================================================================================================================
CREATE TABLE customer
(
    id              character varying(255) NOT NULL,
    last_created_at timestamp(6) without time zone,
    last_created_by character varying(255),
    last_updated_at timestamp(6) without time zone,
    last_updated_by character varying(255),
    status_record   character varying(255),
    code            character varying(100) not null,
    name            character varying(255) not null,
    email           character varying(100) not null,
    mobile_phone    character varying(255) not null
);

ALTER TABLE customer
    ADD CONSTRAINT pkey_id__customer PRIMARY KEY (id);

--
-- =====================================================================================================================
CREATE TABLE invoice_type
(
    id              character varying(255) NOT NULL,
    last_created_at timestamp(6) without time zone,
    last_created_by character varying(255),
    last_updated_at timestamp(6) without time zone,
    last_updated_by character varying(255),
    status_record   character varying(255),
    code            character varying(100) NOT NULL,
    name            character varying(100) NOT NULL,
    CONSTRAINT invoice_type_status_record_check CHECK (((status_record)::text = ANY ((ARRAY['ACTIVE':: character varying, 'INACTIVE':: character varying])::text[])
) )
);

ALTER TABLE invoice_type
    ADD CONSTRAINT pkey_id__invoice_type PRIMARY KEY (id);

--
-- =====================================================================================================================
CREATE TABLE invoice
(
    id              character varying(255) NOT NULL,
    last_created_at timestamp(6) without time zone,
    last_created_by character varying(255),
    last_updated_at timestamp(6) without time zone,
    last_updated_by character varying(255),
    status_record   character varying(255),
    amount          numeric(38, 2)         NOT NULL,
    description     character varying(100) NOT NULL,
    due_date        date                   NOT NULL,
    invoice_number  character varying(100) NOT NULL,
    paid            boolean                NOT NULL,
    id_invoice_type character varying(255) NOT NULL,
    id_customer     character varying(255) NOT NULL,
    CONSTRAINT invoice_amount_check CHECK ((amount >= (0)::numeric)),
    CONSTRAINT invoice_status_record_check CHECK (((status_record)::text = ANY ((ARRAY['ACTIVE':: character varying, 'INACTIVE':: character varying])::text[])
) )
);

ALTER TABLE ONLY invoice
    ADD CONSTRAINT pkey_id__invoice PRIMARY KEY (id);

ALTER TABLE ONLY invoice
    ADD CONSTRAINT fkco4sbxv9cj2oevm6cdpq76ffb FOREIGN KEY (id_invoice_type) REFERENCES invoice_type(id);

ALTER TABLE ONLY invoice
    ADD CONSTRAINT fk_id_customer__invoice FOREIGN KEY (id_customer) REFERENCES customer(id);

--
-- =====================================================================================================================
CREATE TABLE payment_provider
(
    id              character varying(255) NOT NULL,
    code            character varying(100) NOT NULL,
    name            character varying(100) NOT NULL,
    last_created_at timestamp(6) without time zone,
    last_created_by character varying(255),
    last_updated_at timestamp(6) without time zone,
    last_updated_by character varying(255),
    status_record   character varying(255),
    logo            character varying(255),
    CONSTRAINT payment_provider_status_record_check CHECK (((status_record)::text = ANY ((ARRAY['ACTIVE':: character varying, 'INACTIVE':: character varying])::text[])
) )
);

ALTER TABLE payment_provider
    ADD CONSTRAINT pkey_id__payment_provider PRIMARY KEY (id);

ALTER TABLE ONLY payment_provider
    ADD CONSTRAINT payment_provider_code_unique UNIQUE (code);

--
-- =====================================================================================================================
CREATE TABLE virtual_account
(
    id                   character varying(255) NOT NULL,
    last_created_at      timestamp(6) without time zone,
    last_created_by      character varying(255),
    last_updated_at      timestamp(6) without time zone,
    last_updated_by      character varying(255),
    status_record        character varying(255),
    account_number       character varying(255) NOT NULL,
    company_id           character varying(255) NOT NULL,
    virtual_account_type character varying(255) NOT NULL,
    id_invoice           character varying(255) NOT NULL,
    id_payment_provider  character varying(255) NOT NULL,
    CONSTRAINT virtual_account_status_record_check CHECK (((status_record)::text = ANY ((ARRAY['ACTIVE':: character varying, 'INACTIVE':: character varying])::text[])
) ),
    CONSTRAINT virtual_account_virtual_account_type_check CHECK (((virtual_account_type)::text = ANY ((ARRAY['CLOSED'::character varying, 'OPEN'::character varying, 'INSTALLMENT'::character varying])::text[])))
);

ALTER TABLE virtual_account
    ADD CONSTRAINT pkey__id_virtual_account PRIMARY KEY (id);

ALTER TABLE ONLY virtual_account
    ADD CONSTRAINT fkbbdwdxpgdisiikyyhf2xteblc FOREIGN KEY (id_invoice) REFERENCES invoice(id);

ALTER TABLE ONLY virtual_account
    ADD CONSTRAINT fkt3t7f64hvgk4xjblsovqqkpll FOREIGN KEY (id_payment_provider) REFERENCES payment_provider(id);

-- =====================================================================================================================
CREATE TABLE payment
(
    id                 character varying(255) NOT NULL,
    last_created_at    timestamp(6) without time zone,
    last_created_by    character varying(255),
    last_updated_at    timestamp(6) without time zone,
    last_updated_by    character varying(255),
    status_record      character varying(255),
    amount             numeric(38, 2)         NOT NULL,
    provider_reference character varying(255) NOT NULL,
    transaction_time   timestamp(6) without time zone NOT NULL,
    id_virtual_account character varying(255) NOT NULL,
    CONSTRAINT payment_amount_check CHECK ((amount >= (1)::numeric)),
    CONSTRAINT payment_status_record_check CHECK (((status_record)::text = ANY ((ARRAY['ACTIVE':: character varying, 'INACTIVE':: character varying])::text[])
) )
);

ALTER TABLE payment
    ADD CONSTRAINT pkey_id__payment PRIMARY KEY (id);

ALTER TABLE ONLY payment
    ADD CONSTRAINT fkptriq88d7e8io9mhri8p10cq0 FOREIGN KEY (id_virtual_account) REFERENCES virtual_account(id);

--
-- =====================================================================================================================
CREATE TABLE invoice_type_provider
(
    id_invoice_type     character varying(255) NOT NULL,
    id_payment_provider character varying(255) NOT NULL
);

ALTER TABLE ONLY invoice_type_provider
    ADD CONSTRAINT pkey_id_invoice_type_id_payment_provider__invoice_type_provider PRIMARY KEY (id_invoice_type, id_payment_provider);

ALTER TABLE ONLY invoice_type_provider
    ADD CONSTRAINT fk_id_invoice_type_invoice_type_provider FOREIGN KEY (id_invoice_type) REFERENCES invoice_type(id);

ALTER TABLE ONLY invoice_type_provider
    ADD CONSTRAINT fk_id_payment_provider_invoice_type_provider FOREIGN KEY (id_payment_provider) REFERENCES payment_provider(id);