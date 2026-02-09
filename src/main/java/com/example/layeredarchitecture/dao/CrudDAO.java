package com.example.layeredarchitecture.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    public boolean save(T customerDTO) throws SQLException, ClassNotFoundException ;
    public boolean update(T customerDTO) throws SQLException, ClassNotFoundException ;
    public boolean delete(String id) throws SQLException, ClassNotFoundException ;
    public String generateNewID() throws SQLException, ClassNotFoundException;
    public boolean exists(String id) throws SQLException, ClassNotFoundException;
    public T find(String id) throws SQLException, ClassNotFoundException;
}
