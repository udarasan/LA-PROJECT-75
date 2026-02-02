package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderBO {
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    public boolean existItem(String id) throws SQLException, ClassNotFoundException;
    public CustomerDTO findCustomer(String id) throws SQLException, ClassNotFoundException;
    public ItemDTO findItem(String id) throws SQLException, ClassNotFoundException;
    public String generateNewOrderID() throws SQLException, ClassNotFoundException;
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
}
