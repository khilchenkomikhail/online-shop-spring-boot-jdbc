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
        String selectSQL = "select name, surname, password, money from customers where id = Cast( ? AS bigint);";
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
            customer.setId(id);
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
        String sqlSelect = "select id from customers where name = ? and surname = ?;";
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
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                preparedStatement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        if (id == -1) {
            throw new DAOException();
        }

        return id;
    }

    public Customer getByName(String name, String surname) throws DAOException{
        String selectSQL = "select id, name, surname, password, money from customers where name = ? and surname = ?;";
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        Customer customer = null;

        try {
            connection = ConnectionFactory.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);

            resultSet = preparedStatement.executeQuery();
            resultSet.next();

            name = resultSet.getString(2);
            surname = resultSet.getString(3);
            String password = resultSet.getString(4);
            int money = resultSet.getInt(5);

            customer = new Customer(name, surname, password);
            customer.setMoney(money);
            customer.setId(resultSet.getInt(1));
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

    public int addCustomer(Customer customer) throws DAOException {
        String insertStr = "insert into customers (name, surname, money, password) values (?, ?, cast(? as integer), ?) returning id;";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        int newId = -1;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(insertStr);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getSurname());
            statement.setInt(3, customer.getMoney());
            statement.setString(4, customer.getPassword());

            resultSet = statement.executeQuery();

            resultSet.next();

            newId = resultSet.getInt(1);
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

        return newId;
    }

    public void deleteById(int id) throws DAOException {
        String deleteStr = "delete from customers where id = cast(? as bigint);";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(deleteStr);
            statement.setInt(1, id);
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

    public int getIdByCustomer(Customer customer) throws DAOException {
        return getIdByName(customer.getName(), customer.getSurname());
    }

    public void updateById(int id, Customer customer) throws DAOException {
        String updateStr = "update customers set name = ?, surname = ?, money = cast(? as integer), password = ? where id = cast(? as bigint);";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(updateStr);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getSurname());
            statement.setInt(3, customer.getMoney());
            statement.setString(4, customer.getPassword());
            statement.setInt(5, id);

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

    public void updateMoney(Customer customer) throws DAOException {
        String updateStr = "update customers set money = cast(? as integer) where name = ? and surname = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(updateStr);
            statement.setInt(1, customer.getMoney());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getSurname());

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

    public void updatePassword(Customer customer) throws DAOException {
        String updateStr = "update customers set password = ? where name = ? and surname = ?;";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(updateStr);
            statement.setString(1, customer.getPassword());
            statement.setString(2, customer.getName());
            statement.setString(3, customer.getSurname());

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
}
