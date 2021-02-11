package pw.proz.database.dao;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.logger.Logger;
import com.j256.ormlite.logger.LoggerFactory;
import com.j256.ormlite.stmt.QueryBuilder;
import pw.proz.database.dbutils.DbManager;
import pw.proz.database.models.BaseModel;
import pw.proz.utils.FxmlUtils;
import pw.proz.utils.exceptions.DbExceptions;

import java.sql.SQLException;
import java.util.List;


/**
 * Abstract, generic dao for database management.
 * @param <T> Child class name.
 * @param <I> Type of id of the table represented by the class T.
 */
public abstract class CommonDao<T extends BaseModel, I> {

    private final Dao<T, I> dao;
    /**
     * Type of child class required for Dao creation.
     */
    private final Class<T> entityBeanType;

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonDao.class);
    /**
     * Assigns the type of child class and creates dao using DaoManager - helper class.
     * @param cls Type of child class required for Dao creation.
     * @throws SQLException
     */
    public CommonDao(Class<T> cls) throws DbExceptions {
        this.entityBeanType = cls;
        try {
            dao = DaoManager.createDao(DbManager.getConnectionSource(), entityBeanType);
        } catch (SQLException e) {
            LOGGER.error(e.getCause().getMessage());
            throw new DbExceptions(FxmlUtils.getResourceBundle().getString("db.exception"));
        }
    }

    /**
     * Create or update table's record.
     * @param baseModel Class for which a row will be updated or created (depending on id parameter)
     * @throws SQLException
     */
    public void createOrUpdate(T baseModel) throws DbExceptions {
        try {
            dao.createOrUpdate(baseModel);
        } catch (SQLException e) {
            LOGGER.error(e.getCause().getMessage());
            throw new DbExceptions(FxmlUtils.getResourceBundle().getString("db.exception"));
        }
    }

    /**
     * Delete table's record.
     * @param baseModel Class for which a row will be updated or created (depending on id parameter)
     * @throws SQLException
     */
    public void delete(T baseModel) throws DbExceptions {
        try {
            dao.delete(baseModel);
        } catch (SQLException e) {
            LOGGER.error(e.getCause().getMessage());
            throw new DbExceptions(FxmlUtils.getResourceBundle().getString("db.exception"));
        }
    }

    public List<T> queryForAll() throws DbExceptions {
        List<T> all = null;
        try {
            all = dao.queryForAll();
        } catch (SQLException e) {
            LOGGER.error(e.getCause().getMessage());
            throw new DbExceptions(FxmlUtils.getResourceBundle().getString("db.exception"));
        }
        return all;
    }

    public List<T> findByStringValue(String columnName, String strValue) throws DbExceptions {
        QueryBuilder<T,I> queryBuilder = dao.queryBuilder();
        List <T> result =null;
        try {
            result = queryBuilder.where().eq(columnName, strValue).query();
        } catch (SQLException e) {
            LOGGER.error(e.getCause().getMessage());
            throw new DbExceptions(FxmlUtils.getResourceBundle().getString("db.exception"));
        }
        return result;

    }

}

