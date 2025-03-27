INSERT INTO s_roles(id, name)
VALUES ('R001', 'staff');

INSERT INTO s_roles(id, name)
VALUES ('R002', 'manager');

--
-- =====================================================================================================================
INSERT INTO s_users(id, id_role, username, active)
VALUES ('U001', 'R001', 'mrizkisaputra', true);

INSERT INTO s_users(id, id_role, username, active)
VALUES ('U002', 'R002', 'kiki', true);

--
-- =====================================================================================================================
--password: rahasia
INSERT INTO s_password(password, id_user)
VALUES ('$2a$12$RtCOzfJJFwc.OX6UwwfVFe5.FxzoUh/nLMhdaeU3wUijRhCvZYxxO', 'U001');

--password: rahasia
INSERT INTO s_password(password, id_user)
VALUES ('$2a$12$RtCOzfJJFwc.OX6UwwfVFe5.FxzoUh/nLMhdaeU3wUijRhCvZYxxO', 'U002');

--
-- =====================================================================================================================
INSERT INTO s_permissions(id, label, value)
VALUES ('P001', 'Lihat Data Transaksi', 'VIEW_TRANSAKSI');

INSERT INTO s_permissions(id, label, value)
VALUES ('P002', 'Edit Data Transaksi', 'EDIT_TRANSAKSI');

--
-- =====================================================================================================================
INSERT INTO s_roles_permissions(id_role, id_permission)
VALUES ('R001', 'P001');

INSERT INTO s_roles_permissions(id_role, id_permission)
VALUES ('R002', 'P001'),
       ('R002', 'P002');