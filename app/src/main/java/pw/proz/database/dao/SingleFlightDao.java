package pw.proz.database.dao;

import pw.proz.database.models.SingleFlight;
import pw.proz.utils.exceptions.DbExceptions;


public class SingleFlightDao extends CommonDao<SingleFlight, Integer> {

    public SingleFlightDao(Class<SingleFlight> cls) throws DbExceptions {
        super(cls);
    }

}
