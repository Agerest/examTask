package data.daoIF;

import data.model.Manager;

import java.util.List;

public interface ManagerDAO {

    boolean insert(Manager manager);

    List<Manager> getAll();

    List<Manager> findByMonth(int month);

    int getBalance();

}
