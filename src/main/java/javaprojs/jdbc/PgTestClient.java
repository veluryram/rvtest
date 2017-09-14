package javaprojs.jdbc;

/**
 * Created by Ram Velury on 7/27/16.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class PgTestClient
{

    protected final static String driverName = "org.postgresql.Driver";
    protected static String URL = "jdbc:postgresql://localhost:5432/actuals";
    protected Connection connection;

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException
    {
        PgTestClient client = new PgTestClient();
        client.connection =  JdbcTestUtil.getConnection(driverName, URL, "", "");

        try
        {
            JdbcTestUtil.query(client.connection, "select * from reports limit 1");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
