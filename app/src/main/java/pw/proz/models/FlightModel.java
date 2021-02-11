package pw.proz.models;

import com.amadeus.resources.FlightOfferSearch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import pw.proz.api.Connection;
import pw.proz.database.dao.QueryDao;
import pw.proz.database.dao.SingleFlightDao;
import pw.proz.database.models.Query;
import pw.proz.database.models.SingleFlight;
import pw.proz.utils.DialogUtils;
import pw.proz.utils.FxmlUtils;
import pw.proz.utils.exceptions.ConnectionException;
import pw.proz.utils.exceptions.DbExceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Representation of logic in Flight tab.
 * Contains fields needed to search flight, results of search in format that enables to display it (Itinerary Fx
 * observable list), results of search in format that enables to store them in database to track them.
 */
public class FlightModel {
    //to enable static proper view
    private final ResourceBundle bundle = FxmlUtils.getResourceBundle();
    private final TreeItem<SingleFlightFx> root = new TreeItem<>();


    //search results converted to displayable format
    private final ObservableList<SingleFlightFx> singleFlightObservableList;
    //response of api
    private FlightOfferSearch[] flightOfferSearches;
    //search results converted to storable(in db) format
    private final List<SingleFlight> singleFlightList;
    private final SingleFlightDao singleFlightDao;
    private final QueryDao queryDao;
    private Query query;
    private QueryModel queryModel;


    public FlightModel() throws DbExceptions {
        singleFlightList = new ArrayList<>();
        try {
            singleFlightDao = new SingleFlightDao(SingleFlight.class);
        } catch (DbExceptions e) {
            throw new DbExceptions(e.getCause().getMessage());
        }
        singleFlightObservableList = FXCollections.observableArrayList();
        queryDao = new QueryDao(Query.class);
    }

    public void searchForFlights() throws ConnectionException {
        flightOfferSearches = Connection.flightOfferSearch(queryModel.getFrom(), queryModel.getTo(), queryModel.getDateFrom(), queryModel.getDateTo());

        singleFlightObservableList.clear();
        convertApiResponseToSingleFlightObservableList();

    }

    public void saveSearchResultsInDatabase() {
        convertApiResponseToSingleFlightList();
        singleFlightList.forEach((singleFlight -> {

            try {
                singleFlightDao.createOrUpdate(singleFlight);
            } catch (DbExceptions e) {
                DialogUtils.dbErrorDialog(e.getMessage());
            }

        }));

    }
    public void saveQueryParametersInDatabase() {
        query = new Query(queryModel.getFrom(), queryModel.getTo(), queryModel.getDateFrom(), queryModel.getDateTo());
        try {
            queryDao.createOrUpdate(query);
        } catch (DbExceptions e) {
            DialogUtils.dbErrorDialog(e.getMessage());

        }

    }

    public void convertApiResponseToSingleFlightObservableList(){
        for (var flightSearch : flightOfferSearches) {
            for (var itinerary : flightSearch.getItineraries()) {
                for (var segment : itinerary.getSegments()) {
                    String departure = segment.getDeparture().getIataCode();
                    String departureTime = segment.getDeparture().getAt();
                    String arrival = segment.getArrival().getIataCode();
                    String arrivalTime = segment.getArrival().getAt();
                    String duration = segment.getDuration();
                    double price = flightSearch.getPrice().getTotal();
                    String currency = flightSearch.getPrice().getCurrency();
                    SingleFlightFx singleFlightFx = new SingleFlightFx(departure, departureTime, arrival, arrivalTime,
                            duration, price, currency);
                    singleFlightObservableList.add(singleFlightFx);
                }
            }
        }
    }


    public void convertApiResponseToSingleFlightList() {
        for (var flightSearch : flightOfferSearches) {
            for (var itinerary : flightSearch.getItineraries()) {
                for (var segment : itinerary.getSegments()) {
                    String departure = segment.getDeparture().getIataCode();
                    String departureTime = segment.getDeparture().getAt();
                    String arrival = segment.getArrival().getIataCode();
                    String arrivalTime = segment.getArrival().getAt();
                    String duration = segment.getDuration();
                    double price = flightSearch.getPrice().getTotal();
                    String currency = flightSearch.getPrice().getCurrency();
                    SingleFlight singleFlight = new SingleFlight(departure, departureTime, arrival, arrivalTime,
                            duration, price, currency, query);
                    singleFlightList.add(singleFlight);
                }
            }
        }
    }


    public TreeItem<SingleFlightFx> getRoot() {
        return root;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public ObservableList<SingleFlightFx> getSingleFlightObservableList() {
        return singleFlightObservableList;
    }

    public QueryModel getQueryModel() {
        return queryModel;
    }

    public void setQueryModel(QueryModel queryModel) {
        this.queryModel = queryModel;
    }



}
