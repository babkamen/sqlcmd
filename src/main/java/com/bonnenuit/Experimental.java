package com.bonnenuit;

import java.sql.*;

/**
 * Created by Rostyslav on 28/07/16.
 */
public class Experimental {
    Connection connection;
    Statement stmt;
    ResultSet rs;
    PreparedStatement ps;

    public void connect(String database, String user, String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Please add jdbc jar to project.");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/" + database, user,
                    password);
            System.out.println("Conection done!");
            System.out.println("Hello "+user+"!");
        } catch (SQLException e) {
            System.out.println(String.format("Cant get connection for database:%s user:%s", database, user));
            e.printStackTrace();
            connection = null;
        }
    }
    public void select(String tableName) {
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM public."+tableName+"");
            while (rs.next()) {
                System.out.println("id:" + rs.getString("id"));
                System.out.println("name:" + rs.getString("name"));
                System.out.println("password:" + rs.getString("password"));
                System.out.println("-----");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Experimental exp=new Experimental();
        exp.connect("sqlcmd","postgres","postgres");
        exp.select("user");
    }
}
