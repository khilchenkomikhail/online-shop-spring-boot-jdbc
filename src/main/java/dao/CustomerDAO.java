package dao;

import domain.models.Customer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomerDAO {
    private Map<Integer, Customer> map = new HashMap<>();
    private int nextId = 1;

    public Customer getById(int id) throws DAOException {
        if (!map.containsKey(id)) {
            throw new DAOException();
        }
        return new Customer(map.get(id));
    }

    public int addCustomer(Customer customer) {
        map.put(nextId, new Customer(customer));
        return nextId++;
    }

    public void deleteById(int id) {
        map.remove(id);
    }

    public int getIdByCustomer(Customer customer) throws DAOException {
        for (Map.Entry<Integer, Customer> entry:
             map.entrySet()) {
            if (entry.getValue().equals(customer)) {
                return entry.getKey();
            }
        }
        throw new DAOException();
    }

    public void updateById(int id, Customer customer) {
        map.replace(id, new Customer(customer));
    }
}
