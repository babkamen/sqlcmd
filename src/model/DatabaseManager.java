package model;

import java.sql.*;

/**
 * Created by Rostyslav on 08/08/16.
 */
public class DatabaseManager {
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
           public void showDb(){
           try {
               stmt = connection.createStatement();
               rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public'" +
                       " AND table_type='BASE TABLE'");
               while (rs.next()) {
                   System.out.println(rs.getString("table_name"));
               }

           } catch (SQLException e) {
               e.printStackTrace();
           }}
            public void insert(String tableName, String name, String password){
                try {
                    stmt = connection.createStatement();
                    stmt.executeUpdate("INSERT INTO public."+tableName+" (\"NAME\" , \"PASSWORD\")" +
                            "VALUES ("+name+","+password+")");

                } catch (SQLException e) {
                    e.printStackTrace();
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
            public void delete(String id){
                try {
                    stmt = connection.createStatement();
                    stmt.executeUpdate("DELETE FROM public.user WHERE \"ID\" =="+id+" ");
                    } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
           public void update(String tableName,String name,String password){

               try {
                   ps = connection.prepareStatement(
                           "UPDATE public."+tableName+" SET \"PASSWORD\" = ? WHERE \"NAME\" =="+name+"");
                   ps.setString(1, password);
                   ps.executeUpdate();
                   } catch (SQLException e) {
                   e.printStackTrace();
               }
            }

}