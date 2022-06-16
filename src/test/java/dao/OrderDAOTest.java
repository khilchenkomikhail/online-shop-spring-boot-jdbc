package dao;

import domain.models.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOTest {

    OrderDAO orderDAO = new OrderDAO();

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