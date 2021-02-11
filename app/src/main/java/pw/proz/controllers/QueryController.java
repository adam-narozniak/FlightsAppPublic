package pw.proz.controllers;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pw.proz.models.QueryModel;


/**
 * Manipulates all fields responsible for creating api request.
 */
public class QueryController {

    private final QueryModel queryModel;

    @FXML
    private TextField fromTextField;
    @FXML
    private TextField toTextField;
    @FXML
    private TextField dateFromTextField;
    @FXML
    private TextField dateToTextField;
    @FXML
    private ComboBox<String> typeOfTripComboBox;
    
    public QueryController(){
        queryModel = new QueryModel();
    }

    /**
     * Initializes type of trip comboBox and adds listener to text fields.
     */
    @FXML
    private void initialize(){
        //fromTextField.textProperty().addListener(FlightsController::stringChangeListener);
        initializeTypeOfTipComboBox();
        bindProperties();
        dateToTextField.setEditable(false);
    }

    /**
     * Populates typeOfTripComboBox with two options: one way, round trip.
     */
    private void initializeTypeOfTipComboBox() {
        typeOfTripComboBox.setItems(queryModel.getTripTypeList());
        typeOfTripComboBox.setValue(queryModel.getBundle().getString("one_way"));
    }

    /**
     * Bind query parameters (from, formDate, to, toDate) to QueryModel's class fields, it allows to send api request.
     */
    private void bindProperties() {
        fromTextField.textProperty().bindBidirectional(queryModel.fromProperty());
        toTextField.textProperty().bindBidirectional(queryModel.toProperty());
        dateFromTextField.textProperty().bindBidirectional(queryModel.dateFromProperty());
        dateToTextField.textProperty().bindBidirectional(queryModel.dateToProperty());

    }
    /**
     * Depending on selected parameter makes text toDateTextField (return date) editable or not.
     */
    @FXML
    void comboBoxOnAction() {
        boolean editable = !typeOfTripComboBox.getSelectionModel().getSelectedItem().equals(queryModel.getBundle().
                getString("one_way"));
        dateToTextField.clear();
        dateToTextField.setEditable(editable);
    }

    public QueryModel getQueryModel() {
        return queryModel;
    }
    private static void stringChangeListener(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        System.out.println(oldValue);
        System.out.println(newValue);
    }
}
