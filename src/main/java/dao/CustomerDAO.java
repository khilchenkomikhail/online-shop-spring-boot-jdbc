package dao;

import domain.models.Customer;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerDAO {
    public Customer getById(int id) throws DAOException {
        String selectSQL = "select name, surname, password, money from customers where id = Cast( ? AS bigint)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Customer customer = null;

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            preparedStatement.setInt(1 , id);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String password = resultSet.getString("password");
            int money = resultSet.getInt("money");

            customer = new Customer(name, surname, password);
            customer.setMoney(money);
        } catch (SQLException throwables) {
            throw new DAOException();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
            }
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
            }
            try {
                resultSet.close();
            } catch (SQLException throwables) {
            }
        }

        if (customer == null) {
            throw new DAOException();
        }

        return customer;
    }

    public int getIdByName(String name, String surname) throws DAOException{
        String sqlSelect = "select id from customers where name = ? and surname = ?";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = -1;
        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(sqlSelect);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);

            resultSet = preparedStatement.executeQuery();

            resultSet.next();

            id = resultSet.getInt("id");
        } catch (SQLException throwables) {
            throw new DAOException();
        }

        if (id == -1) {
            throw new DAOException();
        }

        return id;
    }

    public int addCustomer(Customer customer) {
//        map.put(nextId, new Customer(customer));
//        return nextId++;
        return 0;
    }

    public void deleteById(int id) {
        //map.remove(id);
    }

    public int getIdByCustomer(Customer customer) throws DAOException {
//        for (Map.Entry<Integer, Customer> entry:
//             map.entrySet()) {
//            if (entry.getValue().equals(customer)) {
//                return entry.getKey();
//            }
//        }
        throw new DAOException();
    }

    public void updateById(int id, Customer customer) {
        //map.replace(id, new Customer(customer));
    }
}
