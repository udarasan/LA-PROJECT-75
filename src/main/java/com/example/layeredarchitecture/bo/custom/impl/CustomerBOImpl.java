package com.example.layeredarchitecture.bo.custom.impl;

import com.example.layeredarchitecture.bo.custom.CustomerBO;
import com.example.layeredarchitecture.dao.DAOFactory;
import com.example.layeredarchitecture.dao.custom.CustomerDAO;
import com.example.layeredarchitecture.dto.CustomerDTO;
import com.example.layeredarchitecture.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers= customerDAO.getAll();
        ArrayList<CustomerDTO> customerDto=new ArrayList<>();
        for (Customer customer : customers) {
            customerDto.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress()));
        }
        return customerDto;
    }

    @Override
    public boolean saveCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customer.getId(),customer.getName(),customer.getAddress()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customer) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new  Customer(customer.getId(),customer.getName(),customer.getAddress()));
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        //business logic
        return customerDAO.exists(id);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public String generateCustomerNewID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

    @Override
    public CustomerDTO findCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer= customerDAO.find(id);
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getAddress());
    }
}
