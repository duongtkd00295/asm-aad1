/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.dto;

/**
 *
 * @author HP Z220
 */
public class OrderHistoryDto {
    private int id;
    private String cusName;
    private String phone;
    private String email;
    private double total;

    public OrderHistoryDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderHistoryDto(int id, String cusName, String phone, String email, double total) {
        this.id = id;
        this.cusName = cusName;
        this.phone = phone;
        this.email = email;
        this.total = total;
    }
    
}
