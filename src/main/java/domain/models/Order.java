package domain.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private List<OrderLine> orderList;
    private Customer customer;

    public Order(Customer customer) {
        orderList = new ArrayList<>();
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(orderList, order.orderList) && Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderList, customer);
    }

    public Order(Order otherOrder) {
        this.orderList = new ArrayList<>();
        for (OrderLine line:
                otherOrder.getOrderList()) {
            this.orderList.add(new OrderLine(line));
        }
        this.customer = new Customer(otherOrder.getCustomer());
    }

    public int getSum() {
        int sum = 0;
        for (OrderLine line:
             orderList) {
            sum += line.getPrice();
        }
        return sum;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderLine> getOrderList() {
        return orderList;
    }

    public void addOrderLine(OrderLine orderLine) {
        orderList.add(orderLine);
    }

    public void deleteOrderLine(OrderLine orderLine) {
        orderList.remove(orderLine);
    }
}
