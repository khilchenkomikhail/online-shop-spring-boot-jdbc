package domain.models;

import java.util.Objects;

public class Customer {
    private String name;
    private String password;
    private int credit;

    public Customer(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return credit == customer.credit && Objects.equals(name, customer.name) && Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, credit);
    }

    public Customer(Customer otherCustomer) {
        this.name = new String(otherCustomer.getName());
        this.password = new String(otherCustomer.getPassword());
        this.credit = otherCustomer.getCredit();
    }

    public void addCredit(int money) {
        credit += money;
    }

    public void subtractCredit(int money) {
        credit -= money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
