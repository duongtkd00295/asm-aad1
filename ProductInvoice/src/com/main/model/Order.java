/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.model;

import java.sql.Timestamp;

/**
 *
 * @author HP Z220
 */
public class Order {
    private int id;
    private String customerName;
    private String phone;
    private String email;
    private Timestamp createdAt;

    public Order(int id, String customerName, String phone, String email, Timestamp createdAt) {
        this.id = id;
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.createdAt = createdAt;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
}
