package domain.utils;

import dao.OrderDAO;
import dao.OrderLineDAO;
import domain.models.Customer;
import domain.models.Order;
import domain.models.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderUtils {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderLineDAO orderLineDAO;

    public void saveOrder(Order order) {
        if (order.getOrderList().isEmpty()) {
            throw new IllegalArgumentException();
        }

        for (OrderLine line:
                order.getOrderList()) {
            orderLineDAO.addOrderLine(line);
        }

        orderDAO.addOrder(order);
    }

    public List<Order> getAllByCustomer(Customer customer) {
        return orderDAO.getByCustomer(customer);
    }
}
