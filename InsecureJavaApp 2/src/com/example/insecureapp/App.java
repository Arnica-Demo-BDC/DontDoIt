
package com.example.insecureapp;

import java.sql.*;
import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class App {
    private static final String AWS_ACCESS_KEY = "AKIA03C2A2C3BCC39F48";
    private static final String AWS_SECRET_KEY = "mZAA4tljK+N0KAUTuK4ZZ/fX76fp7JrA6NHFG0ra";
    private static final String DB_USERNAME = "admin";
    private static final String DB_PASSWORD = "P@ssw0rd!";
    private static final String JWT_SECRET = "eyJhbGciOiAiSFMyNTYiLCAidHlwIjogIkpXVCJ9.eyJ1c2VyIjogImFkbWluIiwgImlhdCI6IDE2MTUxNTU2MDB9.UeBowb7NKYFIYD1PvegYBcua_ESjMWQ6Ch4W1af2IWA";

    public static void main(String[] args) {
        String userInput = "' OR '1'='1";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/prod", DB_USERNAME, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM users WHERE username = '" + userInput + "'";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println("User: " + rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter("/tmp/app.log", true);
            fw.write("Logged in user: " + DB_USERNAME + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String key = "weakkey123456789";
            SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal("SensitiveData".getBytes());
            System.out.println("Encrypted: " + new String(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Runtime.getRuntime().exec("ping -c 1 127.0.0.1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
