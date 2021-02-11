package pw.proz.models;

import com.amadeus.resources.FlightOfferSearch;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;
import pw.proz.database.dao.QueryDao;
import pw.proz.database.dao.SingleFlightDao;
import pw.proz.database.models.Query;
import pw.proz.database.models.SingleFlight;
import pw.proz.utils.DialogUtils;
import pw.proz.utils.FxmlUtils;
import pw.proz.utils.exceptions.DbExceptions;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TrackingModel {

    private final ResourceBundle bundle = FxmlUtils.getResourceBundle();
    private final TreeItem<QueryFx> root = new TreeItem<>();

    //search results converted to displayable format
    private final ObservableList<QueryFx> queryObservableList;
    private final ObservableList<SingleFlightFx> singleFlightObservableList;

    //for search results extracted from db
    private final List<SingleFlight> singleFlightList;
    private List<Query> queryList;

    private final SingleFlightDao singleFlightDao;
    private final QueryDao queryDao;

    private QueryModel queryModel;

    public TrackingModel() throws DbExceptions, SQLException {
        singleFlightList = new ArrayList<>();
        queryList = new ArrayList<>();

        singleFlightDao = new SingleFlightDao(SingleFlight.class);
        queryDao = new QueryDao(Query.class);

        singleFlightObservableList = FXCollections.observableArrayList();
        queryObservableList = FXCollections.observableArrayList();
    }

    public void searchForAllQueries(){
        try {
            queryList = queryDao.queryForAll();
        } catch (DbExceptions e) {
            DialogUtils.dbErrorDialog(e.getMessage());
        }
        convertQueryToQueryFx();

    }
    public void convertQueryToQueryFx(){

        queryList.forEach((singleQuery)->{
            String arrivalDate = singleQuery.getArrivalDate();
            if(arrivalDate == null || arrivalDate.isEmpty()){
                arrivalDate = "--------";
            }
            QueryFx queryFx=new QueryFx(singleQuery.getFrom(), singleQuery.getTo(),singleQuery.getDepartureDate(),
                    arrivalDate);
            queryObservableList.add(queryFx);

        });
    }


    public void deleteQueryFromDatabase(){

    }


    public TreeItem<QueryFx> getRoot() {
        return root;
    }



    public void setQueryModel(QueryModel queryModel) {
        this.queryModel = queryModel;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public ObservableList<QueryFx> getQueryObservableList() {
        return queryObservableList;
    }
}
