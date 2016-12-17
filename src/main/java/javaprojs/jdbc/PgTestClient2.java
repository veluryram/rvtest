package javaprojs.jdbc;

/**
 * Created by Ram Velury on 7/27/16.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

public class PgTestClient2
{

    protected final static String driverName = "org.postgresql.Driver";
    protected static String URL = "jdbc:postgresql://localhost:5432/otp";
    protected Connection connection;

/*
create table public.hello (
    customer varchar(15),
    product varchar(15),
    store varchar(15),
    timestamp TIMESTAMP,
    date DATE,
    sales int,
    cost int
);

insert into public.hello (customer, product, store, timestamp, date, sales, cost) values ('CUSTOMER-00374','PRODUCT-00029','STORE-08','2016-12-15','2016-12-15','27930','19950');
*/

    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException
    {
        PgTestClient2 client = new PgTestClient2();
        client.init();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("->");
        String cmd = "select * from public.hello limit 1";
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
