package dao;

import domain.models.OrderLine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import(DAOTestContextConfiguration.class)
class OrderLineDAOTest {

    @Autowired
    OrderLineDAO orderLineDAO;

    @Test
    void getAllByOrderId() {
        try {
            List<OrderLine> list = orderLineDAO.getAllByOrderId(1);
            Assertions.assertEquals(3, list.size());
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void saveAll() {
        OrderLine orderLine = new OrderLine();
        orderLine.setOrder_id(1);
        orderLine.setGood_id(1);
        orderLine.setAmount(20);
        orderLine.setReady(true);
        OrderLine orderLine1 = new OrderLine(orderLine);
        orderLine1.setAmount(40);
        OrderLine orderLine2 = new OrderLine(orderLine);
        orderLine2.setAmount(60);
        ArrayList<OrderLine> list = new ArrayList<>();
        list.add(orderLine);
        list.add(orderLine1);
        list.add(orderLine2);
        try {
            orderLineDAO.saveAll(list);
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }
}