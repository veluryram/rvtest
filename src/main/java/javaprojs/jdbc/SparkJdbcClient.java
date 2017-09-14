package javaprojs.jdbc;

/**
 * Created by Ram Velury on 7/27/16.
 */
import java.io.IOException;
import java.sql.*;

public class SparkJdbcClient
{

    protected final static String driverName = "org.apache.hive.jdbc.HiveDriver";
    protected static String URL = "jdbc:hive2://localhost:10000";
    protected Connection connection;

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException
    {
        SparkJdbcClient client = new SparkJdbcClient();
        client.connection =  JdbcTestUtil.getConnection(driverName, URL, "", "");

        try
        {
            JdbcTestUtil.query(client.connection, "select count(*) from part");
            JdbcTestUtil.query(client.connection, "select count(*) from supplier");
            JdbcTestUtil.query(client.connection, "select count(*) from customer");
            JdbcTestUtil.query(client.connection, "select count(*) from date");
            JdbcTestUtil.query(client.connection, "select count(*) from lineorder");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
