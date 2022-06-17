package domain.utils;

import domain.models.Customer;
import domain.models.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CustomerPageUtil {

    @Autowired
    private OrderUtils orderUtils;

    private List<Order> cachedOrders;

    public List<Order> getProcessedOrdersByCustomer(Customer customer) {
        List<Order> result = new ArrayList<>();
        if (cachedOrders == null
                || cachedOrders.isEmpty()
                || !cachedOrders.get(0).getCustomer().equals(customer)) {
            cachedOrders = orderUtils.getAllByCustomer(customer);
        }
        for(Order order : cachedOrders) {
            if (order.isProcessed()) {
                result.add(order);
            }
        }
        return result;
    }

//    public Order getCartByCustomer(Customer customer) {
//
//    }
}
