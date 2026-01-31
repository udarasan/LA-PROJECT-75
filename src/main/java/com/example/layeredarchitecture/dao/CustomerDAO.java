package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;
    public void saveCustomer(String id, String name, String address) throws SQLException, ClassNotFoundException ;
    public void updateCustomer(String id, String name, String address) throws SQLException, ClassNotFoundException ;
    public void deleteCustomer(String id) throws SQLException, ClassNotFoundException ;
    public String generateNewID() throws SQLException, ClassNotFoundException;
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    public CustomerDTO findCustomer(String id) throws SQLException, ClassNotFoundException;

}
