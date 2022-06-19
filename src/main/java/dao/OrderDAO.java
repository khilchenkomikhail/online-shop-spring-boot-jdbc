package dao;

import domain.models.Customer;
import domain.models.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class OrderDAO {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ConnectionFactory connectionFactory;

    public List<Order> getAllByCustomerId(int id) throws DAOException {
        String selectStr = "select id, is_processed from orders where customer_id = cast(? as bigint);";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        ArrayList<Order> orders = new ArrayList<>();

        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(selectStr);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setCustomer_id(id);
                order.setId(resultSet.getInt(1));
                order.setProcessed(resultSet.getBoolean(2));
                orders.add(order);
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

        return orders;
    }

    public Order setUpNewOrder(Order order) throws DAOException {
        String insertStr = "insert into orders (customer_id, is_processed) values (cast(? as bigint), ?);";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Order newOrder = new Order(order);

        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(insertStr, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getCustomer_id());
            statement.setBoolean(2, order.isProcessed());
            statement.execute();
            resultSet = statement.getGeneratedKeys();
            resultSet.next();
            newOrder.setId(resultSet.getInt(1));
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

        return newOrder;
    }

    public void updateOrder(Order order) throws DAOException {
        String updateStr = "update orders set customer_id = cast(? as bigint), is_processed = ? where id = cast(? as bigint);";
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(updateStr);
            statement.setInt(1, order.getCustomer_id());
            statement.setBoolean(2, order.isProcessed());
            statement.setInt(3, order.getId());
            statement.execute();
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
        }
    }

    public Order getById(int id) throws DAOException {
        String selectStr = "select customer_id, is_processed from orders where id = cast(? as bigint);";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        Order order = null;

        try {
            connection = connectionFactory.getConnection();
            statement = connection.prepareStatement(selectStr);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            resultSet.next();
            order = new Order();
            order.setCustomer_id(resultSet.getInt(1));
            order.setId(id);
            order.setProcessed(resultSet.getBoolean(2));
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

        return order;
    }
}
