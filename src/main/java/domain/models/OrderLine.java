package domain.models;

import java.util.Objects;

public class OrderLine {
    private Good good;
    private int amount;

    public OrderLine() {
    }

    public OrderLine(OrderLine otherOrderLine) {
        this.good = new Good(otherOrderLine.getGood());
        this.amount = otherOrderLine.getAmount();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return amount == orderLine.amount && Objects.equals(good, orderLine.good);
    }

    @Override
    public int hashCode() {
        return Objects.hash(good, amount);
    }

    public int getPrice() {
        return good.getCost() * amount;
    }

    public Good getGood() {
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
}
