package domain.models;

import java.util.Objects;

public class Good {
    private String name;
    private int cost;

    public Good() {
    }

    public Good(Good otherGood) {
        this.name = new String(otherGood.getName());
        this.cost = otherGood.getCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return cost == good.cost && name.equals(good.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cost);
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
}
