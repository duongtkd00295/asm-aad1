/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.service;

import com.main.refector.SingoltonConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author HP Z220
 */
public class UserService {
    public boolean Login(String username,String password){
        String query = "select * from Users where username = ? and password = ?";
        ResultSet myRs = null;
        PreparedStatement prsm = null;
        try {
            prsm = SingoltonConnection.makePreparedStatement(query);
            prsm.setString(1, username);
            prsm.setString(2, password);
            myRs = prsm.executeQuery();
            if (myRs.first()) {
                   return true;
                }   
        } catch (Exception e) {
        }     
        return false;
    }
}
