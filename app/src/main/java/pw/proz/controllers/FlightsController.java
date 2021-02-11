package pw.proz.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import pw.proz.models.QueryModel;
import pw.proz.models.FlightModel;
import pw.proz.models.SingleFlightFx;
import pw.proz.utils.DialogUtils;
import pw.proz.utils.exceptions.ConnectionException;
import pw.proz.utils.exceptions.DbExceptions;


/**
 * A controller to manipulate Flight model.
 * Enables interaction with search and tracking buttons. Contains QueryController included by fx:id.
 */
public class FlightsController {

    private FlightModel flightModel;
    @FXML
    public TreeTableColumn<SingleFlightFx, String> departureColumn;
    @FXML
    private Button searchButton;
    @FXML
    private Button trackButton;
    @FXML
    private TreeTableView<SingleFlightFx> searchTreeTableView;
    @FXML
    private TreeTableColumn<SingleFlightFx, String> departureTimeColumn;
    @FXML
    private TreeTableColumn<SingleFlightFx, String> arrivalColumn;
    @FXML
    private TreeTableColumn<SingleFlightFx, String> arrivalTimeColumn;
    @FXML
    private TreeTableColumn<SingleFlightFx, String> durationColumn;
    @FXML
    private TreeTableColumn<SingleFlightFx, Double> priceColumn;
    @FXML
    private TreeTableColumn<SingleFlightFx, Double> currencyColumn;
    @FXML
    private QueryController queryController;
    private QueryModel queryModel;


    public FlightsController()  {
        try {
            flightModel = new FlightModel();
        } catch (DbExceptions e) {
            DialogUtils.dbErrorDialog(e.getMessage());
        }
        //queryModel = flightModel.getQueryModel();

    }

    /**
     * Initializes Search Tree Table View to displays api response.
     */
    @FXML
    private void initialize() {
        initializeSearchTreeTableView();
        queryModel = queryController.getQueryModel();
        flightModel.setQueryModel(queryModel);

    }


    /**
     * Searches for flights based by api request based on text fields' content and then displays results.
     *
     */
    @FXML
    public void searchButtonOnAction(){
        try {
            flightModel.searchForFlights();
        } catch (ConnectionException e) {
            DialogUtils.connectionErrorDialog(e.getMessage());
        }
        populateTreeTableView(flightModel.getSingleFlightObservableList());

    }

    /**
     * Saves query parameters to database for further usage as query parameters and saves corresponding flights results.
     * <p>
     * Order matters cause each flight is has query foreign key, in order to accomplish this query is created fist.
     */
    @FXML
    public void trackButtonOnAction() {
        flightModel.saveQueryParametersInDatabase();
        flightModel.saveSearchResultsInDatabase();

    }


    /**
     * Sets placeholder, root and column names of SearchTreeTableView.
     */
    private void initializeSearchTreeTableView() {
        searchTreeTableView.setPlaceholder(new Label(flightModel.getBundle().getString("placeholder")));
        searchTreeTableView.setRoot(flightModel.getRoot());
        initializeTableTreeViewColumns();
        searchTreeTableView.setShowRoot(false);
        //static data
        //sampleTreeTableData();

    }

    /**
     * Assigns corresponding fields of SingleFlightFx to the columns of TreeTableView.
     */
    private void initializeTableTreeViewColumns() {
        departureColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("departure"));
        departureTimeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("departureTime"));
        arrivalColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("arrival"));
        arrivalTimeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("arrivalTime"));
        durationColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("duration"));
        priceColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("price"));
        currencyColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("currency"));
    }

    /**
     * Debug method to populate SearchTreeTable with static data..
     */
    private void sampleTreeTableData() {
        ObservableList<SingleFlightFx> itineraries = FXCollections.observableArrayList();
        itineraries.add(new SingleFlightFx("WAW", "13:10", "LHR", "20:10", "2h", 16.0, "Euro"));
        itineraries.add(new SingleFlightFx("WAW", "15:10", "LHR", "20:10", "2h", 126.0, "Euro"));
        itineraries.add(new SingleFlightFx("WAW", "16:10", "CDG", "22:10", "2h", 120.0, "Euro"));

        itineraries.stream().forEach((itinerary) -> {
            searchTreeTableView.getRoot().getChildren().add(new TreeItem<>(itinerary));
        });
    }

    /**
     * Populates TreeTableView with instances of observable SingleFlightFx.
     *
     * @param singleFlightFxObservableList List of flights to display in TreeTableView.
     */
    private void populateTreeTableView(ObservableList<SingleFlightFx> singleFlightFxObservableList) {
        searchTreeTableView.getRoot().getChildren().clear();
        singleFlightFxObservableList.stream().forEach((singleFlight) -> {
            searchTreeTableView.getRoot().getChildren().add(new TreeItem<>(singleFlight));
        });
    }




}
