package javaprojs.jdbc;

/**
 * Created by Ram Velury on 7/27/16.
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MysqlTestClient
{

    protected final static String driverName = "com.mysql.jdbc.Driver";
    protected static String URL = "jdbc:mysql://localhost:3306/otp";
    protected Connection connection;

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException
    {
        MysqlTestClient client = new MysqlTestClient();
        client.connection =  JdbcTestUtil.getConnection(driverName, URL, "root", "password");

        try
        {
            JdbcTestUtil.query(client.connection, "select * from otp limit 1");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
