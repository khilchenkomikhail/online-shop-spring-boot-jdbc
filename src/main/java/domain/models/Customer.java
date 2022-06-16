package domain.models;

import java.util.Objects;

public class Customer {
    private String name;
    private String surname;
    private String password;
    private int money;
    private int id;

    public Customer(String name, String surname, String password) {
        this.name = name;
        this.surname = surname;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return money == customer.money && Objects.equals(name, customer.name) && Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, money);
    }

    public Customer(Customer otherCustomer) {
        this.name = new String(otherCustomer.getName());
        this.password = new String(otherCustomer.getPassword());
        this.money = otherCustomer.getMoney();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void addMoney(int money) {
        this.money += money;
    }

    public void subtractCredit(int money) {
        this.money -= money;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                '}';
    }
}
