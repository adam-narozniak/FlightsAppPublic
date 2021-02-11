package pw.proz.database.dbutils;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import pw.proz.database.models.Query;
import pw.proz.database.models.SingleFlight;
import pw.proz.utils.exceptions.DbExceptions;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Helper class to manage database initialization, creation and closer of the connection and creation and dropping of
 * the tables (if they don't exist). It's based on Singleton, it contains only static fields and methods and it is
 * never instantiated.
 */
public class DbManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbManager.class);

    private static final String JDBC_DRIVER = "jdbc:h2:./FlightsDB";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "1234";

    private static ConnectionSource connectionSource;

    public static void initDatabase() throws DbExceptions {
        createConnectionSource();
        //dropTable();
        createTable();
        //closeConnectionSource();
    }
    public static void initDatabase(String customUsername, String customPassword) throws DbExceptions {
        createConnectionSource(customUsername, customPassword);
        createTable();
    }

    private static void createConnectionSource() throws DbExceptions {
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER, USERNAME, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DbExceptions(e.getMessage());
        }
    }
    private static void createConnectionSource(String customUsername, String customPassword) throws DbExceptions {
        try {
            connectionSource = new JdbcConnectionSource(JDBC_DRIVER, customUsername, customPassword);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DbExceptions(e.getMessage());
        }
    }

    public static ConnectionSource getConnectionSource() throws DbExceptions {
        if (connectionSource == null) {
            createConnectionSource();
        }
        return connectionSource;
    }

    public static void closeConnectionSource() throws DbExceptions {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                throw new DbExceptions(e.getMessage());
            }
        }
    }

    private static void createTable() throws DbExceptions {
        try {
            TableUtils.createTableIfNotExists(connectionSource, SingleFlight.class);
            TableUtils.createTableIfNotExists(connectionSource, Query.class);


        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DbExceptions(e.getMessage());
        }
    }

    private static void dropTable() throws DbExceptions {
        try {
            TableUtils.dropTable(connectionSource, SingleFlight.class, true);
            TableUtils.dropTable(connectionSource, Query.class, true);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DbExceptions(e.getMessage());
        }
    }


}

