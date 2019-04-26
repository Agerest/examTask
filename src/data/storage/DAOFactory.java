package data.storage;

import data.daoIF.ManagerDAO;

public abstract class DAOFactory {

    private static DAOFactory instance;

    protected DAOFactory() {
    }

    public static DAOFactory getDAOFactory(){
        if (instance == null) {
            instance = new sqlPerRequestDAOFactory();
        }
        return instance;
    }

    public abstract ManagerDAO getManagerDAO();

}
