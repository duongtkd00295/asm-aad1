/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.table;

import com.main.dto.OrderDetailsDto;
import com.main.dto.OrderHistoryDto;
import com.main.dto.OrderProductDto;
import com.main.model.OrderDetails;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author HP Z220
 */
public class OrderDetailsTableModel implements TableModel{

     private String[] columnName = {"ID", "PRODUCT NAME", "QUANTITY", "PRICE", "TOTAL"};
    private Class[] columnClass = {int.class, String.class, int.class, Double.class, Double.class};
    ArrayList<OrderDetailsDto> _arrayList;
    public OrderDetailsTableModel(ArrayList<OrderDetailsDto> arrayList){
        _arrayList = arrayList;
    }
    @Override
    public int getRowCount() {
          return _arrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public String getColumnName(int i) {
       return columnName[i];
    }

    @Override
    public Class<?> getColumnClass(int i) {
        return columnClass[i];
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
       return false;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        OrderDetailsDto dto = _arrayList.get(i);
        switch (i1) {
            case 0:
                return dto.getId();
            case 1:
                return dto.getProductName();
            case 2:
                return dto.getQuantity();
            case 3:
                return dto.getPrice();
            case 4:
                return dto.getTotal();
        }
        return null;
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        
    }

    @Override
    public void addTableModelListener(TableModelListener tl) {
 
    }

    @Override
    public void removeTableModelListener(TableModelListener tl) {
       
    }
    
}
