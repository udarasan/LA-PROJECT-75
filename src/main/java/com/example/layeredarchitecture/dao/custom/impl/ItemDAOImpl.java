package com.example.layeredarchitecture.dao.custom.impl;

import com.example.layeredarchitecture.dao.CRUDUtil;
import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.dto.ItemDTO;
import com.example.layeredarchitecture.entity.Item;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
        public ArrayList<Item>getAll() throws  SQLException, ClassNotFoundException {
        ResultSet rst = CRUDUtil.execute("SELECT * FROM Item");
        ArrayList<Item> items=new ArrayList<>();
        while (rst.next()) {
            String code=rst.getString("code");
            String description =rst.getString("description");
            BigDecimal unitPrice =rst.getBigDecimal("unitPrice");
            Integer qtyOnHand=rst.getInt("qtyOnHand");
            Item entity=new Item(code,description,unitPrice,qtyOnHand);
            items.add(entity);
        }
        return items;

        }
        public boolean save(Item entity) throws SQLException, ClassNotFoundException {
            return CRUDUtil.execute("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)",entity.getCode(),entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand());
        }
        public boolean update(Item entity) throws SQLException, ClassNotFoundException {
            return CRUDUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",entity.getDescription(),entity.getUnitPrice(),entity.getQtyOnHand(),entity.getCode());
        }
        public boolean delete(String itemCode) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();
            String query = "DELETE FROM Item WHERE code=?";

            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, itemCode);

            int rs = pstm.executeUpdate();
            return rs > 0;
        }
        public boolean exists(String itemCode) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();

            PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
            pstm.setString(1, itemCode);
            return pstm.executeQuery().next();
        }
        public String generateNewID() throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();
            ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1;");
            if (rst.next()) {
                String id = rst.getString("code");
                int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
                return String.format("I00-%03d", newItemId);
            }else {
                return "I00-001";
            }
        }
        public Item find(String itemCode) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
            pstm.setString(1, itemCode + "");
            ResultSet rst = pstm.executeQuery();

            if (rst.next()) {
                return new Item(
                        rst.getString("code"),
                        rst.getString("description"),
                        rst.getBigDecimal("unitPrice"),
                        rst.getInt("qtyOnHand")
                );
            }
            return null;
        }
    }

