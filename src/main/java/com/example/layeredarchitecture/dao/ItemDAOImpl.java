package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.CustomerDTO;
import com.example.layeredarchitecture.model.ItemDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class ItemDAOImpl {
        public ArrayList<ItemDTO>loadAllItems() throws  SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");
        ArrayList<ItemDTO> items=new ArrayList<>();
        while (rst.next()) {
            String code=rst.getString("code");
            String description =rst.getString("description");
            BigDecimal unitPrice =rst.getBigDecimal("unitPrice");
            Integer qtyOnHand=rst.getInt("qtyOnHand");
            ItemDTO ItemDTO=new ItemDTO(code,description,unitPrice,qtyOnHand);
            items.add(ItemDTO);
        }
        return items;

        }
        public void saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item (code, description, unitPrice, qtyOnHand) VALUES (?,?,?,?)");
            pstm.setString(1, itemDTO.getCode());
            pstm.setString(2, itemDTO.getDescription());
            pstm.setBigDecimal(3, itemDTO.getUnitPrice());
            pstm.setInt(4, itemDTO.getQtyOnHand());
            pstm.executeUpdate();
        }
        public void updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
            pstm.setString(1, itemDTO.getDescription());
            pstm.setBigDecimal(2, itemDTO.getUnitPrice());
            pstm.setInt(3, itemDTO.getQtyOnHand());
            pstm.setString(4, itemDTO.getCode());
            pstm.executeUpdate();
        }
        public boolean deleteItem(String itemCode) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();
            String query = "DELETE FROM Item WHERE code=?";

            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, itemCode);

            int rs = pstm.executeUpdate();
            return rs > 0;
        }
        public boolean existsItem(String itemCode) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();

            PreparedStatement pstm = connection.prepareStatement("SELECT code FROM Item WHERE code=?");
            pstm.setString(1, itemCode);
            return pstm.executeQuery().next();
        }
        public String generateNewId() throws SQLException, ClassNotFoundException {
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
        public ItemDTO findItem(String itemCode) throws SQLException, ClassNotFoundException {
            Connection connection = DBConnection.getDbConnection().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
            pstm.setString(1, itemCode + "");
            ResultSet rst = pstm.executeQuery();

            if (rst.next()) {
                return new ItemDTO(
                        rst.getString("code"),
                        rst.getString("description"),
                        rst.getBigDecimal("unitPrice"),
                        rst.getInt("qtyOnHand")
                );
            }
            return null;
        }
    }

