/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.table;

import com.main.dto.OrderHistoryDto;
import com.main.dto.OrderProductDto;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author HP Z220
 */
public class OrderHistoryTableModel implements TableModel{

    private String[] columnName = {"ID", "CUSTOMER NAME", "PHONE", "EMAIL", "TOTAL"};
    private Class[] columnClass = {int.class, String.class, String.class, String.class, Double.class};
    ArrayList<OrderHistoryDto> _arrayList;
    public OrderHistoryTableModel(ArrayList<OrderHistoryDto> arrayList){
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
        OrderHistoryDto dto = _arrayList.get(i);
        switch (i1) {
            case 0:
                return dto.getId();
            case 1:
                return dto.getCusName();
            case 2:
                return dto.getPhone();
            case 3:
                return dto.getEmail();
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
    public int GetId(int selectRow){
        int id = _arrayList.get(selectRow).getId(); 
        return id;
    }
    
}
