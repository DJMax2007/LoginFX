package com.example.javafxapp;

import java.sql.*;

public class MyJDBC {
    public static void main(String[] args) {
        String url = JDBC_URL_PATH;
        String user = JDBC USER;
        String password = JDBC_PASSWORD;

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {

            while (resultSet.next()) {
                System.out.println(resultSet.getString("Username"));
                System.out.println(resultSet.getString("Password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isUsernameTaken(String username) {
        String url = JDBC_URL_PATH;
        String user = JDBC USER;
        String password = JDBC_PASSWORD;
        String query = "SELECT COUNT(*) FROM users WHERE Username = ?";

        username = username.toUpperCase();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isEmailTaken(String email) {
       String url = JDBC_URL_PATH;
        String user = JDBC USER;
        String password = JDBC_PASSWORD;
        String query = "SELECT COUNT(*) FROM users WHERE Email = ?";

        email = email.toUpperCase();

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isPhoneTaken(String phone, String countryCode) {
        String url = JDBC_URL_PATH;
        String user = JDBC USER;
        String password = JDBC_PASSWORD;
        String query = "SELECT COUNT(*) FROM users WHERE Phone_number = ?";

        phone = countryCode + phone ;

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, phone);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getPasswordFromDatabase(String username) {
        String url = JDBC_URL_PATH;
        String user = JDBC USER;
        String password = JDBC_PASSWORD;
        String query = "SELECT Password FROM users WHERE Username = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("Password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getEmailFromDatabase(String username) {
       String url = JDBC_URL_PATH;
        String user = JDBC USER;
        String password = JDBC_PASSWORD;
        String query = "SELECT Email FROM users WHERE Username = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getPhoneFromDatabase(String username) {
        String url = JDBC_URL_PATH;
        String user = JDBC USER;
        String password = JDBC_PASSWORD;
        String query = "SELECT Phone_number FROM users WHERE Username = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("Phone_number");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getUserFromDatabase(String email) {
       String url = JDBC_URL_PATH;
        String user = JDBC USER;
        String password = JDBC_PASSWORD;
        String query = "SELECT Username FROM users WHERE Email = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, email);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("Username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getCCFromDatabase(String username) {
       String url = JDBC_URL_PATH;
        String user = JDBC USER;
        String password = JDBC_PASSWORD;
        String query = "SELECT Country_code FROM users WHERE Username = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString("Country_code");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
