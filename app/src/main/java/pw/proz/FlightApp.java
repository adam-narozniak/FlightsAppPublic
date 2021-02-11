package pw.proz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import pw.proz.database.dbutils.DbManager;
import pw.proz.utils.DialogUtils;
import pw.proz.utils.FxmlUtils;
import pw.proz.utils.exceptions.DbExceptions;

import java.io.IOException;
import java.util.ResourceBundle;


/**
 * Main class of this application.
 */
public class FlightApp extends Application {

    private static final String BORDER_PANE_MAIN_FXML = "/views/BorderPaneMain.fxml";

    /**
     * Creates stage, screen, sets bundles as a resource and initializes connection to the H2 Database.
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        //Locale.setDefault(new Locale("pl"));
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(BORDER_PANE_MAIN_FXML));
        ResourceBundle bundle = FxmlUtils.getResourceBundle();
        loader.setResources(bundle);
        BorderPane borderPane = loader.load();
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(bundle.getString("title.application"));
        primaryStage.show();

        try {
            DbManager.initDatabase();
        } catch (DbExceptions dbExceptions) {
            DialogUtils.dbErrorDialog(dbExceptions.getMessage());
        }
        try {
            DbManager.closeConnectionSource();
        } catch (DbExceptions dbExceptions) {
            DialogUtils.dbErrorDialog(dbExceptions.getMessage());
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}
