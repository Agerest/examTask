package data.storage;

import data.managers.PreferencesManager;
import utils.PreferencesManagerConstants;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DataSourceFactory {

    public static DataSource createDataSource() throws SQLException {
        MysqlDataSource mds = new MysqlDataSource();
        PreferencesManager pm = PreferencesManager.getInstance();
        mds.setServerName(pm.getProperty(PreferencesManagerConstants.hostName));
        mds.setPortNumber(Integer.parseInt(pm.getProperty(PreferencesManagerConstants.port)));
        mds.setDatabaseName(pm.getProperty(PreferencesManagerConstants.DBName));
        mds.setUser(pm.getProperty(PreferencesManagerConstants.user));
        mds.setPassword(pm.getProperty(PreferencesManagerConstants.pass));
        mds.setServerTimezone("UTC");
        mds.setUseSSL(false);
        return mds;
    }

    public static DataSource createDataSource(String host, int port, String dbName, String user, String password) throws SQLException {
        MysqlDataSource mds = new MysqlDataSource();
        mds.setServerName(host);
        mds.setPortNumber(port);
        mds.setDatabaseName(dbName);
        mds.setUser(user);
        mds.setPassword(password);
        mds.setServerTimezone("UTC");
        mds.setUseSSL(false);
        return mds;
    }

}
