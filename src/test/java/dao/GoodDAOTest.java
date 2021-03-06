package dao;

import domain.models.Good;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Import(DAOTestContextConfiguration.class)
class GoodDAOTest {

    @Autowired
    GoodDAO goodDAO;

    @Test
    void getById() {
        try {
            Good good = goodDAO.getById(1);
            Assertions.assertEquals("first_good", good.getName());
            Assertions.assertEquals(900, good.getCost());
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void addGood() {
        Good good = new Good();
        good.setName("second_good");
        good.setCost(100);
        try {
            int id = goodDAO.addGood(good);
            Good anotherGood = goodDAO.getById(id);
            Assertions.assertEquals(good.getName(), anotherGood.getName());
            Assertions.assertEquals(good.getCost(), anotherGood.getCost());
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void changeCostByGood() {
        try {
            Good good = goodDAO.getById(1);
            good.setCost(10);
            goodDAO.changeCostByGood(good);
            good = goodDAO.getById(1);
            Assertions.assertEquals(10, good.getCost());
            good.setCost(900);
            goodDAO.changeCostByGood(good);
            good = goodDAO.getById(1);
            Assertions.assertEquals(900, good.getCost());
        } catch (DAOException e) {
            e.printStackTrace();
            fail();
        }
    }
}