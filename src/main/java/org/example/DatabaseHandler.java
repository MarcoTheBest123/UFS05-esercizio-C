package org.example;

import java.sql.*;

public class DatabaseHandler {
    final String DB_URL = "jdbc:mysql://localhost/cities";
    final String USER = "root";
    final String PASSWORD = "";
    Connection conn;
    Statement stmt = null;

    DatabaseHandler() {

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    String all_sorted() {
        String result = "[";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cities ORDER BY name");
            while(rs.next())
                result += "{\"description\":" + rs.getString(1) +
                        ", \"id\":\"" + rs.getInt(2) +
                        "\", \"name\":" + rs.getString(3) +
                        "\", \"price\":" + rs.getDouble(4) +" " +
                        "}, ";
        } catch(Exception e){
            System.out.println(e);
        }
        result = cleanResult(result);

        return result;
    }

    String all() {
        String result = "[";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM plates");
            while(rs.next())
                result += "{\"description\":" + rs.getString(1) +
                        ", \"id\":\"" + rs.getInt(2) +
                        "\", \"name\":" + rs.getString(3) +
                        "\", \"price\":" + rs.getDouble(4) +" " +
                        "}, ";

        }catch(Exception e){
            System.out.println(e);
        }
        result = cleanResult(result);

        return result;
    }





    String maxPrice() {

        String result = "";

        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM plates ORDER BY price DESC LIMIT 1";
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            result += "{\"description\":" + rs.getString(1) +
                    ", \"id\":\"" + rs.getInt(2) +
                    "\", \"name\":" + rs.getString(3) +
                    "\", \"price\":" + rs.getDouble(4) +" " +
                    "}, ";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        result = cleanSingleResult(result);
        return result;
    }

    String cleanResult(String result) {
        return result.substring(0, result.length() - 2) + "]";
    }

    String cleanSingleResult(String result) { return result.substring(0, result.length() - 2);}
}