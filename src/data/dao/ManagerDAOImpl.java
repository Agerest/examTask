package data.dao;

import data.daoIF.ManagerDAO;
import data.model.Manager;
import data.storage.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAOImpl implements ManagerDAO {

    private static DataSource dataSource;

    public ManagerDAOImpl() {
        try {
            dataSource = DataSourceFactory.createDataSource();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insert(Manager manager) {
        String query = "insert into manager (income, consumption, date, description) values (?,?,?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,manager.getIncome());
            statement.setInt(2,manager.getConsumption());
            statement.setDate(3,manager.getDate());
            statement.setString(4, manager.getDescription());
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Manager> getAll() {
        String query = "select * from manager ORDER BY date DESC";
        List<Manager> managerList = new ArrayList<>();
        Manager manager;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                manager = new Manager();
                manager.setId(result.getInt("id"));
                manager.setConsumption(result.getInt("consumption"));
                manager.setDate(result.getDate("date"));
                manager.setDescription(result.getString("description"));
                manager.setIncome(result.getInt("income"));
                managerList.add(manager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return managerList;
    }

    @Override
    public List<Manager> findByMonth(int month) {
        String query = "select * from manager where MONTH(date)= ?";
        List<Manager> managerList = new ArrayList<>();
        Manager manager;
        ResultSet result = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1,month);
            result = statement.executeQuery();
            while (result.next()) {
                manager = new Manager();
                manager.setId(result.getInt("id"));
                manager.setConsumption(result.getInt("consumption"));
                manager.setDate(result.getDate("date"));
                manager.setDescription(result.getString("description"));
                manager.setIncome(result.getInt("income"));
                managerList.add(manager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try { result.close(); } catch (Exception e) { /* ignored */ }
        }
        return managerList;
    }

    @Override
    public int getBalance() {
        String query = "select (income-consumption) as balance from manager";
        int balance = 0;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {
            while (result.next()) {
                balance += result.getInt("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }
}
