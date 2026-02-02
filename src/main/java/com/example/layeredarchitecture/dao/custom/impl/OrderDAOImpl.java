package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CRUDUtil;
import com.example.layeredarchitecture.dao.custom.OrderDAO;
import com.example.layeredarchitecture.dao.custom.OrderDetailDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.OrderDTO;

import java.sql.*;
import java.time.LocalDate;

public class OrderDAOImpl implements OrderDAO {

    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d",
                (Integer.parseInt(rst.getString("oid")
                        .replace("OID-", "")) + 1)) : "OID-001";
    }
    public boolean exitsOrder(String orderId) throws SQLException, ClassNotFoundException {
        ResultSet rst= CRUDUtil.execute("SELECT oid FROM `Orders` WHERE oid=?", orderId);
        return rst.next();
    }
    public boolean saveOrder(OrderDTO orderDTO) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)",orderDTO.getOrderId(),orderDTO.getOrderDate(),orderDTO.getCustomerId());
    }
}
