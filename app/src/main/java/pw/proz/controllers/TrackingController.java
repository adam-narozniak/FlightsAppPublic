package pw.proz.controllers;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import pw.proz.models.QueryModel;
import pw.proz.models.QueryFx;
import pw.proz.models.TrackingModel;
import pw.proz.utils.DialogUtils;
import pw.proz.utils.exceptions.DbExceptions;

import java.sql.SQLException;

public class TrackingController {

    private TrackingModel trackingModel;
    @FXML
    private TreeTableView<QueryFx> queryTreeTableView;
    @FXML
    private Button searchButton;
    @FXML
    private Button showButton;
    @FXML
    private Button deleteButton;
    @FXML
    private TreeTableColumn<QueryFx, String> arrivalColumn;
    @FXML
    private TreeTableColumn<QueryFx, String> arrivalTimeColumn;
    @FXML
    private TreeTableColumn<QueryFx, String> departureColumn;
    @FXML
    private TreeTableColumn<QueryFx, String> departureTimeColumn;
    @FXML
    private QueryController queryController;
    private QueryModel queryModel;

    public TrackingController()  {
        try {
            trackingModel = new TrackingModel();
        } catch (DbExceptions | SQLException e) {
            DialogUtils.dbErrorDialog(e.getMessage());
        }
        //queryModel = trackingModel.getQueryModel();
        trackingModel.searchForAllQueries();
    }


    @FXML
    private void initialize() {
        initializeQueryTreeTableView();
        queryModel = queryController.getQueryModel();
        trackingModel.setQueryModel(queryModel);
        populateTreeTableView(trackingModel.getQueryObservableList());

    }

    @FXML
    void deleteButtonOnAction(ActionEvent event) {

    }


    @FXML
    void searchButtonOnAction(ActionEvent event) {
        //sarch database for query
    }

    @FXML
    void showButtonOnAction(ActionEvent event) {

    }


    /**
     * Sets placeholder, root and column names of SearchTreeTableView.
     */
    private void initializeQueryTreeTableView() {
        queryTreeTableView.setPlaceholder(new Label(trackingModel.getBundle().getString("placeholder.tracking")));
        queryTreeTableView.setRoot(trackingModel.getRoot());
        initializeQueryTableTreeViewColumns();
        queryTreeTableView.setShowRoot(false);
    }

    private void initializeQueryTableTreeViewColumns() {
        departureColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("from"));
        departureTimeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("dateFrom"));
        arrivalColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("to"));
        arrivalTimeColumn.setCellValueFactory(new TreeItemPropertyValueFactory<>("dateTo"));
    }

    /**
     * Populates TreeTableView with instances of observable QueryFx.
     *
     * @param queryObservableList List of queries to display in TreeTableView.
     */
    private void populateTreeTableView(ObservableList<QueryFx> queryObservableList) {
        queryTreeTableView.getRoot().getChildren().clear();
        queryObservableList.stream().forEach((singleQuery) -> {
            queryTreeTableView.getRoot().getChildren().add(new TreeItem<>(singleQuery));
        });
    }


}
