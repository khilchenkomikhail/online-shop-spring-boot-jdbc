package dao;

import domain.models.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderDAO {
    private Map<Integer, Order> map = new HashMap<>();
    private int nextId = 1;

    public Order getById(int id) throws DAOException {
        if (!map.containsKey(id)) {
            throw new DAOException();
        }
        return map.get(id);
    }

    public int addOrder(Order order) {
        map.put(nextId, order);
        return nextId++;
    }

    public void deleteById(int id) {
        map.remove(id);
    }

    public int getIdByOrder(Order order) throws DAOException {
        for (Map.Entry<Integer, Order> entry:
                map.entrySet()) {
            if (entry.getValue().equals(order)) {
                return entry.getKey();
            }
        }
        throw new DAOException();
    }

    public void updateById(int id, Order order) {
        map.replace(id, order);
    }
}