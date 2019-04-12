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
public class OrderDetailsDto {
    private int id;
    private String productName;
    private int quantity;
    private double price;
    private double total;

    public OrderDetailsDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderDetailsDto(int id, String productName, int quantity, double price, double total) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }
    
}
