
package com.example.insecureapp;

import java.sql.*;
import java.io.*;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class App {
    private static final String AWS_SECRET_KEY = "AKIAIOSFODNN7EXAMPLE";
    private static final String DB_PASSWORD = "supersecret123";
    private static final String JWT_SECRET = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.fakepayload.signedfake";

    public static void main(String[] args) {
        String userInput = "' OR '1'='1";
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", DB_PASSWORD);
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
            String data = "Sensitive data";
            FileWriter fw = new FileWriter("/tmp/log.txt", true);
            fw.write("Logging: " + data + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String key = "1234567890123456"; // Weak key
            SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal("Data".getBytes());
            System.out.println("Encrypted: " + new String(encrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String cmd = "ls";
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
