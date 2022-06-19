package dao;

import domain.models.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import(DAOTestContextConfiguration.class)
class CustomerDAOTest {

    @Autowired
    CustomerDAO customerDAO;

    @Test
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

    @Test
    void addCustomer() {
        Customer customer = new Customer("AddDeleteName", "AddDeleteSurname", "AddDeletePassword");
        try {
            int id = customerDAO.addCustomer(customer);
            Customer tmp = customerDAO.getById(id);
            Assertions.assertEquals("AddDeleteName", tmp.getName());
            Assertions.assertEquals("AddDeleteSurname", tmp.getSurname());
            Assertions.assertEquals("AddDeletePassword", tmp.getPassword());
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }


    @Test
    void updateById() {
        Customer customer = new Customer("UpdateName", "UpdateSurname", "UpdatePassword");
        customer.setMoney(100);
        Customer customerUpdated = new Customer("UpdateName-updated", "UpdateSurname-updated", "UpdatePassword-updated");
        customerUpdated.setMoney(1000);
        try {
            int id = customerDAO.addCustomer(customer);
            Customer tmp = customerDAO.getById(id);
            Assertions.assertEquals("UpdateName", tmp.getName());
            Assertions.assertEquals("UpdateSurname", tmp.getSurname());
            Assertions.assertEquals("UpdatePassword", tmp.getPassword());
            Assertions.assertEquals(100, tmp.getMoney());

            customerDAO.updateById(id, customerUpdated);
            tmp = customerDAO.getById(id);
            Assertions.assertEquals("UpdateName-updated", tmp.getName());
            Assertions.assertEquals("UpdateSurname-updated", tmp.getSurname());
            Assertions.assertEquals("UpdatePassword-updated", tmp.getPassword());
            Assertions.assertEquals(1000, tmp.getMoney());
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void updateMoney() {
        Customer customer = new Customer("UpdateMoneyName", "UpdateMoneySurname", "UpdateMoneyPassword");
        customer.setMoney(100);
        try {
            int id = customerDAO.addCustomer(customer);
            Customer tmp = customerDAO.getById(id);
            Assertions.assertEquals("UpdateMoneyName", tmp.getName());
            Assertions.assertEquals("UpdateMoneySurname", tmp.getSurname());
            Assertions.assertEquals("UpdateMoneyPassword", tmp.getPassword());
            Assertions.assertEquals(100, tmp.getMoney());


            customer.setMoney(100500);
            customerDAO.updateMoney(customer);
            tmp = customerDAO.getById(id);
            Assertions.assertEquals("UpdateMoneyName", tmp.getName());
            Assertions.assertEquals("UpdateMoneySurname", tmp.getSurname());
            Assertions.assertEquals("UpdateMoneyPassword", tmp.getPassword());
            Assertions.assertEquals(100500, tmp.getMoney());
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void updatePassword() {
        Customer customer = new Customer("UpdatePasswordName", "UpdatePasswordSurname", "UpdatePasswordPassword");
        customer.setMoney(100);
        try {
            int id = customerDAO.addCustomer(customer);
            Customer tmp = customerDAO.getById(id);
            Assertions.assertEquals("UpdatePasswordName", tmp.getName());
            Assertions.assertEquals("UpdatePasswordSurname", tmp.getSurname());
            Assertions.assertEquals("UpdatePasswordPassword", tmp.getPassword());
            Assertions.assertEquals(100, tmp.getMoney());

            customer.setPassword("UpdatePasswordPassword-updated");

            customerDAO.updatePassword(customer);
            tmp = customerDAO.getById(id);
            Assertions.assertEquals("UpdatePasswordName", tmp.getName());
            Assertions.assertEquals("UpdatePasswordSurname", tmp.getSurname());
            Assertions.assertEquals("UpdatePasswordPassword-updated", tmp.getPassword());
            Assertions.assertEquals(100, tmp.getMoney());
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void deleteById() {
        Customer customer = new Customer("DeleteName", "DeleteSurname", "DeletePassword");
        customer.setMoney(100);
        try {
            int id = customerDAO.addCustomer(customer);
            Customer tmp = customerDAO.getById(id);
            Assertions.assertEquals("DeleteName", tmp.getName());
            Assertions.assertEquals("DeleteSurname", tmp.getSurname());
            Assertions.assertEquals("DeletePassword", tmp.getPassword());
            Assertions.assertEquals(100, tmp.getMoney());

            customerDAO.deleteById(id);
            Assertions.assertThrowsExactly(DAOException.class, () -> customerDAO.getById(id));
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