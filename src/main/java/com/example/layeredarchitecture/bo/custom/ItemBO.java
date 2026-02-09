package com.example.layeredarchitecture.bo.custom;

import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException;
    public boolean saveItem(ItemDTO item) throws SQLException, ClassNotFoundException;
    public boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException;
    public boolean existItem(String id) throws SQLException, ClassNotFoundException;
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException;
    public String generateItemNewID() throws SQLException, ClassNotFoundException;
    public ItemDTO findItem(String id) throws SQLException, ClassNotFoundException;

}
