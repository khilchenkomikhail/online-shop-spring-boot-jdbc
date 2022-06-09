package dao;

import domain.models.OrderLine;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderLineDAO {
    private Map<Integer, OrderLine> map = new HashMap<>();
    private int nextId = 1;

    public OrderLine getById(int id) throws DAOException {
        if (!map.containsKey(id)) {
            throw new DAOException();
        }
        return map.get(id);
    }

    public int addOrderLine(OrderLine orderLine) {
        map.put(nextId, orderLine);
        return nextId++;
    }

    public void deleteById(int id) {
        map.remove(id);
    }

    public int getIdByOrderLine(OrderLine orderLine) throws DAOException {
        for (Map.Entry<Integer, OrderLine> entry:
                map.entrySet()) {
            if (entry.getValue().equals(orderLine)) {
                return entry.getKey();
            }
        }
        throw new DAOException();
    }

    public void updateById(int id, OrderLine orderLine) {
        map.replace(id, orderLine);
    }
}
