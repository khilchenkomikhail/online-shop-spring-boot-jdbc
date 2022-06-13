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
        customerDAO.addCustomer(customer);
    }

    public boolean isValidCustomer(Customer customer) {
        try {
            customerDAO.getIdByCustomer(customer);
            return true;
        } catch (DAOException e) {
            return false;
        }
    }

    public Customer getCustomerByName(String name) {
        Customer customer = null;
        try {
            customer = customerDAO.getByName(name);
        } catch (DAOException e) {
            throw new IllegalArgumentException();
        }
        return customer;
    }

    public void addMoneyToCustomer(String name, int money) {
        Customer customer = null;
        int id = -1;
        try {
            customer = customerDAO.getByName(name);
            id = customerDAO.getIdByCustomer(customer);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        customer.addMoney(money);
        customerDAO.updateById(id, customer);
    }
}
