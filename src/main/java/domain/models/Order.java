package domain.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private List<OrderLine> orderList;
    private Customer customer;
    private int customer_id;
    private int id;
    private boolean isProcessed = false;

    public Order() {
    }

    public Order(Customer customer) {
        orderList = new ArrayList<>();
        this.customer = customer;
    }

    public Order(Order otherOrder) {
        if (otherOrder.orderList != null) {
            this.orderList = new ArrayList<>();
            for (OrderLine line :
                    otherOrder.getOrderList()) {
                this.orderList.add(new OrderLine(line));
            }
        }
        if (otherOrder.customer != null) {
            this.customer = new Customer(otherOrder.getCustomer());
        }
        this.id = otherOrder.id;
        this.customer_id = otherOrder.customer_id;
        this.isProcessed = otherOrder.isProcessed;
    }

    public int getSum() {
        int sum = 0;
        for (OrderLine line:
             orderList) {
            sum += line.getPrice();
        }
        return sum;
    }

    public void setOrderList(List<OrderLine> orderList) {
        this.orderList = orderList;
    }

    public void clearOrder() {
        if (isProcessed) {
            throw new IllegalStateException();
        }
        orderList.clear();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

}
