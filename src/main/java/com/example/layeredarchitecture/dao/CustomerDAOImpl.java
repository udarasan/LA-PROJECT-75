package com.example.layeredarchitecture.dao;


import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAOImpl {

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ResultSet rst=CRUDUtil.execute("SELECT * FROM Customer");
        ArrayList<CustomerDTO> customers=new ArrayList<CustomerDTO>();
        while (rst.next()) {
            String id=rst.getString("id");
            String name=rst.getString("name");
            String address=rst.getString("address");
            CustomerDTO customerDTO=new CustomerDTO(id,name,address);
            customers.add(customerDTO);
        }
        return customers;
    }
    public void saveCustomer(String id, String name, String address) throws SQLException, ClassNotFoundException {
        CRUDUtil.execute("INSERT INTO Customer (id,name, address) VALUES (?,?,?)",id,name,address);
    }
    public void updateCustomer(String id, String name, String address) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Customer SET name=?, address=? WHERE id=?");
        pstm.setString(1, name);
        pstm.setString(2, address);
        pstm.setString(3, id);
        pstm.executeUpdate();
    }
    public void deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
        pstm.setString(1, id);
        pstm.executeUpdate();

    }
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst =CRUDUtil.execute("SELECT id FROM Customer ORDER BY id DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("id");
            int newCustomerId = Integer.parseInt(id.replace("C00-", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        } else {
            return "C00-001";
        }
    }
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst=CRUDUtil
                .execute("SELECT * FROM Customer WHERE id=?", id);
        return rst.next();
    }
    public CustomerDTO findCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM Customer WHERE id=?",id);
        if (rst.next()) {
            return new CustomerDTO(
                    rst.getString("id"),
                    rst.getString("name"),
                    rst.getString("address")
            );
        }
        return null;
    }

}
