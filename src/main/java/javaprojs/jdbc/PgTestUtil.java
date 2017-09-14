package javaprojs.jdbc;

/**
 * Created by Ram Velury on 7/27/16.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class PgTestUtil
{

    protected final static String driverName = "org.postgresql.Driver";
    protected static String URL = "jdbc:postgresql://localhost:5432/actuals";
    protected Connection connection;

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException
    {
        PgTestUtil client = new PgTestUtil();
        client.init();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("->");
        String cmd = "select * from reports limit 1";
        try
        {
            client.query(client.connection, cmd);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.print("->");
            cmd = reader.readLine().trim();
        }
    }

    private void init() throws ClassNotFoundException, SQLException
    {
        Class.forName(driverName);
        connection = DriverManager.getConnection(URL, "cascade", "cascade");
    }

    public static void query(Connection connection, String queryStmt) throws SQLException
    {
        Statement statement = connection.createStatement();

        statement.setMaxRows(5);
        long beginTime = System.currentTimeMillis();
        ResultSet rs = null;
        int count = 1;
        for (int i = 0; i < count; i++)
        {
            rs = statement.executeQuery(queryStmt);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("cost time: " + (endTime - beginTime) / count + " ms");

        ResultSetMetaData rsmd = rs.getMetaData();
        int cols = rsmd.getColumnCount();
        for (int i = 1; i <= cols; i++)
        {
            System.out.print(rsmd.getColumnName(i) + "\t");
        }
        System.out.print("\n");
        for (int i = 1; i <= cols; i++)
        {
            System.out.print("(" + rsmd.getColumnTypeName(i) + ")" + "\t");
        }
        System.out.print("\n");
        for (int i = 1; i <= cols; i++)
        {
            System.out.print("(" + rsmd.getColumnType(i) + ")" + "\t");
        }
        System.out.print("\n");
        while (rs.next())
        {
            for (int i = 1; i <= cols; i++)
            {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.print("\n");
        }
    }

}
