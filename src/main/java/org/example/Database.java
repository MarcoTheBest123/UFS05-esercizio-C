package org.example;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;
        import java.sql.Statement;

public class Database {
    Database() {
        String DB_URL = "jdbc:mysql://localhost/";
        final String USER = "root";
        final String PASSWORD = "";
        String sql;
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement stmt = null;

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            sql = "CREATE DATABASE plates";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException e) {
            System.out.println("Database already exists");
        }

        DB_URL = "jdbc:mysql://localhost/plates";

        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            sql = "CREATE TABLE `plates` (`description` VARCHAR(200) NOT NULL, `id` INT NOT NULL AUTO_INCREMENT , `name` VARCHAR(200) NOT NULL , `price` DOUBLE NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB;";
            stmt.executeUpdate(sql);
            System.out.println("Table created");

            sql = "INSERT INTO plates (description, name, price) VALUES ( 'Piatto di pasta con uova e pancetta','Carbonara', 15.30), ( 'Pizza con gorgonzola e speck','Pizza speck e gorgonzola', 12.00), ('Dolce pesante' ,'Tiramisù', 10.70);";

            stmt.executeUpdate(sql);
            System.out.println("table records added");

        } catch (SQLException e) {
            System.out.println("Table already exists");
        }
    }
}