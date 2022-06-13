package dao;

import domain.models.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOTest {

    @Test
    void getById() {
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = null;
        try {
            customer = customerDAO.getById(1);
            System.out.println(customer.toString());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getIdByName() {
        CustomerDAO customerDAO = new CustomerDAO();
        String name = "Mark";
        String surname = "Hey";
        try {
            int id = customerDAO.getIdByName(name, surname);
            System.out.println(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}