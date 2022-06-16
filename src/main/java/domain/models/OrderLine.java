package domain.models;

import java.util.Objects;

public class OrderLine {
    private int order_id;
    private int good_id;
    private Good good;
    private int amount;
    private boolean isReady = false;

    public OrderLine() {
    }

    public OrderLine(OrderLine otherOrderLine) {
        this.order_id = otherOrderLine.order_id;
        this.good_id = otherOrderLine.good_id;
        this.isReady = otherOrderLine.isReady;
        if (otherOrderLine.good != null) {
            this.good = new Good(otherOrderLine.getGood());
        }
        this.amount = otherOrderLine.getAmount();
    }

    public int getPrice() {
        if (!isReady) {
            throw new IllegalStateException();
        }
        return good.getCost() * amount;
    }

    public Good getGood() {
        if (!isReady) {
            throw new IllegalStateException();
        }
        return good;
    }

    public void setGood(Good good) {
        this.good = good;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getGood_id() {
        return good_id;
    }

    public void setGood_id(int good_id) {
        this.good_id = good_id;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setReady(boolean ready) {
        isReady = ready;
    }
}
