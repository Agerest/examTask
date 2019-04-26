package data.storage;


import data.dao.ManagerDAOImpl;
import data.daoIF.ManagerDAO;

public class sqlPerRequestDAOFactory extends DAOFactory {

    @Override
    public ManagerDAO getManagerDAO() {
        return new ManagerDAOImpl();
    }

}
