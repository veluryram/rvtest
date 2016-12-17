package javaprojs.jdbc;

/**
 * Created by Ram Velury on 7/27/16.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class PgTestClient1
{

    protected final static String driverName = "org.postgresql.Driver";
    protected static String URL = "jdbc:postgresql://localhost:5432/actuals";
    protected Connection connection;

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException
    {
        PgTestClient1 client = new PgTestClient1();
        client.init();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("->");
        String cmd = "select * from reports limit 1";
        try
        {
            PgTestUtil.query(client.connection, cmd);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void init() throws ClassNotFoundException, SQLException
    {
        Class.forName(driverName);
        connection = DriverManager.getConnection(URL, "cascade", "cascade");
    }
}
