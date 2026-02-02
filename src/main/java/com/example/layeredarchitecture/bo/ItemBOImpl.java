package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.dao.custom.ItemDAO;
import com.example.layeredarchitecture.dao.custom.impl.ItemDAOImpl;
import com.example.layeredarchitecture.model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = new ItemDAOImpl();
    @Override
    public ArrayList<ItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAll();
    }

    @Override
    public boolean saveItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        return itemDAO.save(item);
    }

    @Override
    public boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        return itemDAO.update(item);
    }

    @Override
    public boolean existItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.exists(id);
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(id);
    }

    @Override
    public String generateItemNewID() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewID();
    }
}
