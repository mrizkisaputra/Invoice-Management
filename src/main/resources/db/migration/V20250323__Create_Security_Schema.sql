CREATE TABLE s_roles
(
    id   varchar(100) not null,
    name varchar(100) not null
);

ALTER TABLE s_roles
    ADD CONSTRAINT pkey_id__s_role PRIMARY KEY (id);

ALTER TABLE s_roles
    ADD CONSTRAINT unique_name__s_role UNIQUE (name);

--
-- =====================================================================================================================
CREATE TABLE s_users
(
    id       varchar(100) not null,
    id_role  varchar(100) not null,
    username varchar(100) not null,
    active   boolean      not null
);

ALTER TABLE s_users
    ADD CONSTRAINT pkey_id__s_users PRIMARY KEY (id);

ALTER TABLE s_users
    ADD CONSTRAINT unique_username__s_users UNIQUE (username);

ALTER TABLE s_users
    ADD CONSTRAINT fk_id_role__s_users FOREIGN KEY (id_role) REFERENCES s_roles (id);

--
-- =====================================================================================================================
CREATE TABLE s_reset_password
(
    id varchar(100) not null,
    generated timestamp not null,
    expired timestamp not null,
    unique_code varchar(100) not null,
    id_user varchar(100) not null
);

ALTER TABLE s_reset_password
    ADD CONSTRAINT pkey_id__s_reset_ppassword PRIMARY KEY (id);

ALTER TABLE s_reset_password
    ADD CONSTRAINT unique_unique_code__s_reset_password UNIQUE (unique_code);

ALTER TABLE s_reset_password
    ADD CONSTRAINT fk_id_user__s_reset_password FOREIGN KEY (id_user) REFERENCES s_users (id);

--
-- =====================================================================================================================
CREATE TABLE s_password
(
    password varchar(255) not null,
    id_user  varchar(100) not null
);

ALTER TABLE s_password
    ADD CONSTRAINT pkey_id_user__s_password PRIMARY KEY (id_user);

ALTER TABLE s_password
    ADD CONSTRAINT fk_id_user__s_password FOREIGN KEY (id_user) references s_users (id);

--
-- =====================================================================================================================
CREATE TABLE s_permissions
(
    id    varchar(100) not null,
    label varchar(100) not null,
    value varchar(100) not null
);

ALTER TABLE s_permissions
    ADD CONSTRAINT pkey_id__s_permissions PRIMARY KEY (id);

ALTER TABLE s_permissions
    ADD CONSTRAINT unique_value__s_permissions UNIQUE (value);

--
-- =====================================================================================================================
CREATE TABLE s_roles_permissions
(
    id_role       varchar(100),
    id_permission varchar(100)
);

ALTER TABLE s_roles_permissions
    ADD CONSTRAINT pkey_id_role_id_permission__s_roles_permissions PRIMARY KEY (id_role, id_permission);

ALTER TABLE s_roles_permissions
    ADD CONSTRAINT fk_id_role__s_roles_permissions FOREIGN KEY (id_role) REFERENCES s_roles (id);

ALTER TABLE s_roles_permissions
    ADD CONSTRAINT fk_id_permission__s_roles_permissions FOREIGN KEY (id_permission) REFERENCES s_permissions (id);
