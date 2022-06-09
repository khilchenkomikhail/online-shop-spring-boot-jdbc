package domain.utils;

import dao.CustomerDAO;
import dao.DAOException;
import domain.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
