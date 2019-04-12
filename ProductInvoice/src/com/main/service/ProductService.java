/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.service;

import com.main.dto.ProductDto;
import com.main.enums.Status;
import com.main.refector.SingoltonConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author HP Z220
 */
public class ProductService {

    public ArrayList<ProductDto> GetProducts(String search, String type) {
        String sql = "select * from Products where not status =?";
        ArrayList<ProductDto> list = new ArrayList<ProductDto>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (search != null) {
            if (search.trim().length() > 0) {
                if (type.equalsIgnoreCase("id")) {
                    sql += " and id='" + search + "'";
                } else if (type.equalsIgnoreCase("name")) {
                    sql += " and name like'%" + search + "%'";
                }
            }
        }
        try {
            ps = SingoltonConnection.makePreparedStatement(sql);
            ps.setString(1, Status.Delete.name());
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductDto productDto = new ProductDto(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), rs.getString("status"));
                list.add(productDto);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public Boolean Create(ProductDto dto) {
        String sql = "insert into Products (name,price,status) values(?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = SingoltonConnection.makePreparedStatement(sql);
            ps.setString(1, dto.getName());
            ps.setDouble(2, dto.getPrice());
            ps.setString(3, dto.getStatus());
            ps.execute();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public Boolean Update(ProductDto dto) {
        String sql = "update Products set name=?,price=?,status=? where id = ?";
        PreparedStatement ps = null;
        try {
            ps = SingoltonConnection.makePreparedStatement(sql);
            ps.setString(1, dto.getName());
            ps.setDouble(2, dto.getPrice());
            ps.setString(3, dto.getStatus());
            ps.setInt(4, dto.getId());
            ps.execute();
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    public Boolean Delete(int id) {
        String sql = "update Products set status=? where id =?";
        PreparedStatement ps = null;
        try {
            ps = SingoltonConnection.makePreparedStatement(sql);
            ps.setInt(2, id);
            ps.setString(1, Status.Delete.name());
            ps.execute();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}
