package domain.utils;

import dao.DAOException;
import dao.OrderLineDAO;
import domain.models.Order;
import domain.models.OrderLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineUtils {
    @Autowired
    private OrderLineDAO orderLineDAO;

    @Autowired
    private GoodUtils goodUtils;

    public List<OrderLine> getAllByOrder(Order order) {
        try {
            List<OrderLine> orderLines = orderLineDAO.getAllByOrderId(order.getId());
            for (OrderLine line : orderLines) {
                line.setGood(goodUtils.getById(line.getGood_id()));
                line.setReady(true);
            }
            return orderLines;
        } catch (DAOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public void deleteAllByOrderId(int orderId) {
        try {
            orderLineDAO.deleteAllByOrderId(orderId);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public void saveUpdateAllLines(Order order) {
        try {
            orderLineDAO.deleteAllByOrderId(order.getId());
            orderLineDAO.saveAll(order.getOrderList());
        } catch (DAOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
