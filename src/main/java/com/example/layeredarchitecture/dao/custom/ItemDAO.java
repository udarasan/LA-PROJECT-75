package com.example.layeredarchitecture.dao.custom;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO {
    public ArrayList<ItemDTO> loadAllItems() throws SQLException, ClassNotFoundException ;
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException ;
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException ;
    public boolean deleteItem(String itemCode) throws SQLException, ClassNotFoundException ;
    public boolean existsItem(String itemCode) throws SQLException, ClassNotFoundException ;
    public String generateNewId() throws SQLException, ClassNotFoundException ;
    public ItemDTO findItem(String itemCode) throws SQLException, ClassNotFoundException ;

}
