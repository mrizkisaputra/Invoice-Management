package com.mrizkisaputra.helper;

public class SqlHelper {

    public static String SQL_QUERY_LOGIN = """
            select
                u.username, p.password, u.active
            from s_users as u
                     inner join s_password as p on p.id_user = u.id
            where u.username = ?;
            """;

    public static String SQL_QUERY_PERMISSION = """
            select
                u.username,
                p.value as authority
            from s_users as u
                     inner join s_roles as r on u.id_role = r.id
                     inner join s_roles_permissions as rp on r.id = rp.id_role
                     inner join s_permissions as p on rp.id_permission = p.id
            where u.username = ?;
            """;
}
