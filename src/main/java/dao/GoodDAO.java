package dao;

import domain.models.Good;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GoodDAO {
    private Map<Integer, Good> map = new HashMap<>();
    private int nextId = 1;

    public Good getById(int id) throws DAOException {
        if (!map.containsKey(id)) {
            throw new DAOException();
        }
        return new Good(map.get(id));
    }

    public int addGood(Good good) {
        map.put(nextId, new Good(good));
        return nextId++;
    }

    public void deleteById(int id) {
        map.remove(id);
    }

    public int getIdByGood(Good good) throws DAOException {
        for (Map.Entry<Integer, Good> entry:
                map.entrySet()) {
            if (entry.getValue().equals(good)) {
                return entry.getKey();
            }
        }
        throw new DAOException();
    }

    public void updateById(int id, Good good) {
        map.replace(id, new Good(good));
    }
}
