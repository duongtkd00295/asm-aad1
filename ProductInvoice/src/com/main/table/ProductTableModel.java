/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.main.table;

import com.main.dto.ProductDto;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author HP Z220
 */
public class ProductTableModel implements TableModel{

    private String[] columnName = {"ID", "NAME","PRICE","STATUS"};
    private Class[] columnClass = {int.class, String.class,Double.class, String.class};
    ArrayList<ProductDto> _arrayList;
    public ProductTableModel(ArrayList<ProductDto> arrayList){
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
     ProductDto dTO = _arrayList.get(i);
        switch (i1) {
            case 0:
                return dTO.getId();
            case 1:
                return dTO.getName();
            case 2:
                return dTO.getPrice();
            case 3:
                return dTO.getStatus();
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
    
    public ProductDto getSelectedObject(int selectedRow) {
        return _arrayList.get(selectedRow);
    }
    
}
