/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.table;

import com.main.dto.OrderProductDto;
import com.main.dto.ProductDto;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author duong
 */
public class OrderTableModel implements TableModel {

    private String[] columnName = {"ID", "NAME", "PRICE", "QUANTITY", "TOTAL"};
    private Class[] columnClass = {int.class, String.class, Double.class, int.class, Double.class};
    ArrayList<OrderProductDto> _arrayList;

    public OrderTableModel(ArrayList<OrderProductDto> arrayList) {
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
        OrderProductDto dto = _arrayList.get(i);
        switch (i1) {
            case 0:
                return dto.getProductDto().getId();
            case 1:
                return dto.getProductDto().getName();
            case 2:
                return dto.getProductDto().getPrice();
            case 3:
                return dto.getQuantity();
            case 4:
                return dto.getProductDto().getPrice()*dto.getQuantity();
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
    
    public OrderProductDto getSelectedObject(int selectedRow) {
        return _arrayList.get(selectedRow);
    }

}
