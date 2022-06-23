package domain.utils;

import dao.DAOException;
import dao.GoodDAO;
import domain.models.Good;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodUtils {
    @Autowired
    private GoodDAO goodDAO;

    public Good getById(int id) {
        try {
            return goodDAO.getById(id);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }

    public List<Good> getAll() {
        try {
            return goodDAO.getAll();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Good getByName(String goodName) {
        try {
            return goodDAO.getByName(goodName);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        }
    }
}
