package pw.proz.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

/**
 * A class to change between flights and tracking in the application.
 * Handles on action for Flights and Tracking buttons.
 */
public class LeftSideButtonsController {

    public static final String FLIGHTS_ONE_WAY_FXML = "/views/Flights.fxml";
    public static final String TRACKING_FXML = "/views/Tracking.fxml";
    private MainController mainController;

    @FXML
    private ToggleGroup toggleButtons;

    /**
     * Changes center of border pane which is main scene to flights.
     */
    @FXML
    public void openFlights() {
        mainController.setCenter(FLIGHTS_ONE_WAY_FXML);
    }

     /**
     * Changes center of border pane which is main scene to flights.
     */
     @FXML
    public void openTracking() {
        mainController.setCenter(TRACKING_FXML);
    }

    /**
     * Function to hold border pane which is main scene of the application which enables functionality of changing
     * between different scenes (flights and tracking).
     * @param mainController main scene of the application.
     */
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }


}
