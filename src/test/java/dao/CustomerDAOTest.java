package dao;

import domain.models.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


class CustomerDAOTest {

    CustomerDAO customerDAO = new CustomerDAO();

    @Test
    @Order(1)
    void getById() {
        Customer customer = null;
        try {
            customer = customerDAO.getById(1);
            Assertions.assertEquals("Mark", customer.getName());
            Assertions.assertEquals("Hey", customer.getSurname());
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(2)
    void getIdByName() {
        String name = "Mark";
        String surname = "Hey";
        try {
            int id = customerDAO.getIdByName(name, surname);
            Assertions.assertEquals(1, id);
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    private int tmpId = 2;

    @Test
    @Order(3)
    void addCustomer() {
        Customer customer = new Customer("AddDeleteName", "AddDeleteSurname", "AddDeletePassword");
        customer.setMoney(500);
        try {
            tmpId = customerDAO.addCustomer(customer);
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test
    @Order(4)
    void updateById() {
        if (tmpId == -500) {
            fail();
        }
        Customer customer = new Customer("AddDeleteName-Updated", "AddDeleteSurname-Updated", "AddDeletePassword-Updated");
        customer.setMoney(1000);
        try {
            customerDAO.updateById(tmpId, customer);
            Customer tmp = customerDAO.getById(tmpId);
            Assertions.assertEquals("AddDeleteName-Updated", tmp.getName());
            Assertions.assertEquals("AddDeleteSurname-Updated", tmp.getSurname());
            Assertions.assertEquals("AddDeletePassword-Updated", tmp.getPassword());
            Assertions.assertEquals(1000, tmp.getMoney());
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(5)
    void updateMoney() {
        if (tmpId == -500) {
            fail();
        }
        try {
            Customer customer = customerDAO.getById(tmpId);
            customer.setMoney(100500);
            customerDAO.updateMoney(customer);
            customer = customerDAO.getById(tmpId);
            Assertions.assertEquals(100500, customer.getMoney());
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(6)
    void updatePassword() {
        if (tmpId == -500) {
            fail();
        }
        try {
            Customer customer = customerDAO.getById(tmpId);
            customer.setPassword("Sole-password-update");
            customerDAO.updatePassword(customer);
            customer = customerDAO.getById(tmpId);
            Assertions.assertEquals("Sole-password-update", customer.getPassword());
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @Order(7)
    void deleteById() {
        if (tmpId == -500) {
            fail();
        }
        try {
            customerDAO.deleteById(tmpId);
            Assertions.assertThrowsExactly(DAOException.class, () -> customerDAO.getById(tmpId));
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void getByName() {
        Customer customer = null;
        try {
            customer = customerDAO.getByName("Mark", "Hey");
            Assertions.assertEquals("Mark", customer.getName());
            Assertions.assertEquals("Hey", customer.getSurname());
            Assertions.assertEquals("qwerty12", customer.getPassword());
            Assertions.assertEquals(100, customer.getMoney());
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }
}