package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;
import java.time.LocalDate;

public interface OrderDAO {
    public String generateNewOrderId() throws SQLException, ClassNotFoundException ;
    public boolean exitsOrder(String orderId) throws SQLException, ClassNotFoundException ;
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException ;

}
