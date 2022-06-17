package dao;

import domain.models.Good;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GoodDAO {
    public Good getById(int id) throws DAOException {
        String selectStr = "select name, price from goods where id = cast(? as bigint);";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Good good = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(selectStr);
            statement.setInt(1, id);

            resultSet = statement.executeQuery();
            resultSet.next();

            good = new Good();
            good.setName(resultSet.getString("name"));
            good.setCost(resultSet.getInt("price"));
            good.setId(id);
        } catch (SQLException throwables) {
            throw new DAOException();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return good;
    }

    public Good getByName(String goodName) throws DAOException {
        String selectStr = "select id, price from goods where name = ?;";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Good good = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(selectStr);
            statement.setString(1, goodName);

            resultSet = statement.executeQuery();
            resultSet.next();

            good = new Good();
            good.setName(goodName);
            good.setId(resultSet.getInt(1));
            good.setCost(resultSet.getInt(2));
        } catch (SQLException throwables) {
            throw new DAOException();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return good;
    }

    public int addGood(Good good) throws DAOException {
        String insertStr = "insert into goods (name, price) values (?, ?) returning cast(id as integer);";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int id = -1;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(insertStr);
            statement.setString(1, good.getName());
            statement.setInt(2, good.getCost());
            resultSet = statement.executeQuery();

            resultSet.next();
            id = resultSet.getInt(1);
        } catch (SQLException throwables) {
            throw new DAOException();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return id;
    }

    public void changeCostByGood(Good good) throws DAOException {
        String updateStr = "update goods set price = ? where id = cast(? as bigint);";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(updateStr);
            statement.setInt(1, good.getCost());
            statement.setInt(2, good.getId());
            statement.execute();
        } catch (SQLException throwables) {
            throw new DAOException();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public List<Good> getAll() throws DAOException {
        String selectStr = "select id, name, price from goods;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        ArrayList<Good> goods = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(selectStr);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Good good = new Good();
                good.setId(resultSet.getInt(1));
                good.setName(resultSet.getString(2));
                good.setCost(resultSet.getInt(3));
                goods.add(good);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new DAOException();
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return goods;
    }
}
