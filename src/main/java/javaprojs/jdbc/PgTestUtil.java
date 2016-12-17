package javaprojs.jdbc;

import java.sql.*;

/**
 * Created by Ram Velury on 12/15/16.
 */
public class PgTestUtil
{
    static void query(Connection connection, String queryStmt) throws SQLException
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
