package domain.models;

import java.util.Objects;

public class Good {
    private String name;
    private int cost;
    private int id;

    public Good() {
    }

    public Good(Good otherGood) {
        this.name = new String(otherGood.getName());
        this.cost = otherGood.getCost();
        this.id = otherGood.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
