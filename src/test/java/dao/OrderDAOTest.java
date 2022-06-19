package dao;

import domain.models.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import(DAOTestContextConfiguration.class)
class OrderDAOTest {

    @Autowired
    OrderDAO orderDAO;

    @Test
    void getAllByCustomerId() {
        try {
            List<Order> list = orderDAO.getAllByCustomerId(1);
            Assertions.assertEquals(1, list.size());
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void setUpNewOrder() {
        Order order = new Order();
        order.setCustomer_id(1);
        try {
            Order newOrder = orderDAO.setUpNewOrder(order);
            Assertions.assertNotEquals(0, newOrder.getId());
            Assertions.assertEquals(order.getCustomer_id(), newOrder.getCustomer_id());
            Assertions.assertEquals(order.isProcessed(), newOrder.isProcessed());
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void updateOrder() {
        Order order = new Order();
        order.setCustomer_id(1);
        order.setId(1);
        order.setProcessed(true);
        try {
            orderDAO.updateOrder(order);
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }
}