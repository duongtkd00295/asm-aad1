/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.refector;

import static com.main.refector.IConstantManager.DATABASE_DRIVER;
import static com.main.refector.IConstantManager.DATABASE_NAME;
import static com.main.refector.IConstantManager.DATABASE_PASSWORD;
import static com.main.refector.IConstantManager.DATABASE_URL;
import static com.main.refector.IConstantManager.DATABASE_USERNAME;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author HP Z220
 */
public class SingoltonConnection implements IConstantManager{
    private static Connection connection;

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DATABASE_DRIVER);
        System.out.println("CONNECTED" );
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            System.out.println("DATABASE_NAME = " + DATABASE_NAME);
        }
        return connection;
    }
    public static PreparedStatement makePreparedStatement(String sql) throws SQLException, ClassNotFoundException {
        return getConnection().prepareStatement(sql);
    }
    public static void main(String[] args) {
        try {
            getConnection();
        } catch (Exception e) {
        }
    }
}
