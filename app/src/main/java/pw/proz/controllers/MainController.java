package pw.proz.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import pw.proz.utils.DialogUtils;

import java.io.IOException;
import java.util.ResourceBundle;


public class MainController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private LeftSideButtonsController leftSideButtonsController;

    @FXML
    private void initialize(){
        leftSideButtonsController.setMainController(this);
        leftSideButtonsController.openFlights();
    }

    public void setCenter(String fxmlPath){
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
        ResourceBundle bundle = ResourceBundle.getBundle("bundles.messages");
        loader.setResources(bundle);
        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            DialogUtils.IOErrorDialog(e.getMessage());
        }
        borderPane.setCenter(parent);
    }


}
