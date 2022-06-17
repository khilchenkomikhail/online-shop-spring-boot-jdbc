package domain.utils;

import dao.CustomerDAO;
import dao.DAOException;
import domain.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomerUtils {
    private final CustomerDAO customerDAO;

    public CustomerUtils(@Autowired CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void addCustomer(Customer customer) {
        try {
            customerDAO.addCustomer(customer);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidCustomer(Customer customer) {
        try {
            int id = customerDAO.getIdByName(customer.getName(), customer.getSurname());
            return customerDAO.getById(id).getPassword().equals(customer.getPassword());
        } catch (DAOException e) {
            return false;
        }
    }

    public Customer getById(int id) {
        try {
            return customerDAO.getById(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public Customer getCustomerByName(String name, String surname) {
        Customer customer = null;
        try {
            customer = customerDAO.getByName(name, surname);
        } catch (DAOException e) {
            throw new IllegalArgumentException();
        }
        return customer;
    }

    public void addMoneyToCustomer(String name, String surname, int money) {
        Customer customer = null;
        int id = -1;
        try {
            customer = customerDAO.getByName(name, surname);
            customer.addMoney(money);
            customerDAO.updateMoney(customer);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
