package pw.proz.database.dao;

import pw.proz.database.models.Query;
import pw.proz.utils.exceptions.DbExceptions;


public class QueryDao extends CommonDao<Query,Integer>{
    public QueryDao(Class<Query> cls) throws DbExceptions {
        super(cls);
    }
}
