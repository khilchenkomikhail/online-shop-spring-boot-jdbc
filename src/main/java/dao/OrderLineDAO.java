package dao;

import domain.models.Good;
import domain.models.OrderLine;
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
public class OrderLineDAO {
    public List<OrderLine> getAllByOrderId(int id) throws DAOException {
        String selectStr = "select good_id, amount from order_lines where order_id = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        ArrayList<OrderLine> list = new ArrayList<>();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(selectStr);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                OrderLine orderLine = new OrderLine();
                orderLine.setOrder_id(id);
                orderLine.setGood_id(resultSet.getInt(1));
                orderLine.setAmount(resultSet.getInt(2));
                list.add(orderLine);
            }
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
        return list;
    }

    public void saveAll(List<OrderLine> list) throws DAOException {
        String insertStr = "insert into order_lines (order_id, good_id, amount) values (cast(? as bigint), cast(? as bigint), cast(? as bigint));";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            for (OrderLine line : list) {
                statement = connection.prepareStatement(insertStr);
                statement.setInt(1, line.getOrder_id());
                statement.setInt(2, line.getOrder_id());
                statement.setInt(3, line.getAmount());
                statement.execute();
            }
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
}
