/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.refector;

import com.main.dto.OrderDetailsDto;
import com.main.dto.OrderHistoryDto;
import com.main.dto.ProductDto;
import com.main.enums.Status;
import com.main.model.Order;
import com.main.model.OrderDetails;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public class OrderService {

    public ArrayList<OrderHistoryDto> GetOrders(String search, String type) {
        String sql = "select o.*from Orders as o where not o.status =?";
        ArrayList<OrderHistoryDto> list = new ArrayList<OrderHistoryDto>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        if (search != null) {
            if (search.trim().length() > 0) {
                if (type.equalsIgnoreCase("id")) {
                    sql += " and o.id='" + search + "'";
                } else if (type.equalsIgnoreCase("phone")) {
                    sql += " and o.phone like'%" + search + "%'";
                }
            }
        }
        try {
            ps = SingoltonConnection.makePreparedStatement(sql);
            ps.setString(1, Status.Delete.name());
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderHistoryDto dto = new OrderHistoryDto(rs.getInt("id"), rs.getString("customer_name"), rs.getString("phone"), rs.getString("email"),0.0);
                list.add(dto);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public ArrayList<OrderDetailsDto> GetOrderDetails(int id) {
        String sql = "select o.*,o.quantity*o.price as total,p.name as product_name from OrderDetails as o INNER JOIN Products as p on o.product_id = p.id where o.order_id =?";
        ArrayList<OrderDetailsDto> list = new ArrayList<OrderDetailsDto>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = SingoltonConnection.makePreparedStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetailsDto dto = new OrderDetailsDto(rs.getInt("id"), rs.getString("product_name"), rs.getInt("quantity"),rs.getDouble("price"),rs.getDouble("total"));
                list.add(dto);
            }

        } catch (Exception e) {
            
        }
        return list;

    }

    public Boolean CreateOrder(Order order, ArrayList<OrderDetails> listDetails) {
        Connection connection = null;
        String sqlOrder = "insert into Orders (customer_name,phone,email,status) values  (?,?,?,?)";
        String sqlDetails = "insert into OrderDetails (order_id,product_id,quantity,price) values  (?,?,?,?)";
        PreparedStatement ps;
        try {
            connection = SingoltonConnection.getConnection();
            connection.setAutoCommit(false);
            ps = connection.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, order.getCustomerName());
            ps.setString(2, order.getPhone());
            ps.setString(3, order.getEmail());
            ps.setString(4, Status.Active.name());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                return false;
            }
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1);
                ps = connection.prepareStatement(sqlDetails);
                for (OrderDetails listDetail : listDetails) {
                    ps.setInt(1, orderId);
                    ps.setInt(2, listDetail.getProductId());
                    ps.setInt(3, listDetail.getQuantity());
                    ps.setDouble(4, listDetail.getPrice());
                    ps.executeUpdate();
                }
            }
            connection.commit();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}
