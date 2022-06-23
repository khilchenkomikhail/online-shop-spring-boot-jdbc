package domain.utils;

import dao.CustomerDAO;
import dao.DAOException;
import dao.OrderDAO;
import domain.models.Customer;
import domain.models.Good;
import domain.models.Order;
import domain.models.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderUtils {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderLineUtils orderLineUtils;

    @Autowired
    private CustomerDAO customerDAO;

    public List<Order> getAllByCustomer(Customer customer) {
        try {
            List<Order> orders = orderDAO.getAllByCustomerId(customer.getId());
            for (Order order : orders) {
                order.setOrderList(orderLineUtils.getAllByOrder(order));
                order.setCustomer(customer);
            }
            return orders;
        } catch (DAOException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public Order getById(int id) {
        try {
            Order order = orderDAO.getById(id);
            order.setOrderList(orderLineUtils.getAllByOrder(order));
            order.setCustomer(customerDAO.getById(order.getCustomer_id()));
            return order;
        } catch (DAOException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public void clearCartOrder(Order order) {
        orderLineUtils.deleteAllByOrderId(order.getId());
        order.clearOrder();
    }

    public void addToOrder(Order order, Good good, int amount) {
        OrderLine newLine = new OrderLine();
        newLine.setGood(good);
        newLine.setAmount(amount);
        newLine.setGood_id(good.getId());        newLine.setOrder_id(order.getId());
        newLine.setReady(true);

        order.addOrderLine(newLine);
        orderLineUtils.saveUpdateAllLines(order);
    }

    public Order createNewOrder(Customer customer) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setCustomer_id(customer.getId());
        try {
            order = orderDAO.setUpNewOrder(order);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return order;
    }

    public void processOrder(Order order, Customer customer) {
        order.setProcessed(true);
        try {
            orderDAO.updateOrder(order);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        customer.subtractCredit(order.getSum());
        try {
            customerDAO.updateMoney(customer);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
