package pw.proz.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pw.proz.utils.FxmlUtils;

import java.util.ResourceBundle;

/**
 * Represents all data required for making an api request.
 */
public class QueryModel {


    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    private final StringProperty dateFrom = new SimpleStringProperty();
    private final StringProperty dateTo = new SimpleStringProperty();

    private final ResourceBundle bundle = FxmlUtils.getResourceBundle();
    private final ObservableList<String> tripTypeList;

    public QueryModel() {
        tripTypeList = FXCollections.
                observableArrayList(bundle.getString("one_way"), bundle.getString("round_trip"));
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public ObservableList<String> getTripTypeList() {
        return tripTypeList;
    }

    public String getFrom() {
        return from.get();
    }

    public StringProperty fromProperty() {
        return from;
    }

    public void setFrom(String from) {
        this.from.set(from);
    }

    public String getTo() {
        return to.get();
    }

    public StringProperty toProperty() {
        return to;
    }

    public void setTo(String to) {
        this.to.set(to);
    }

    public String getDateFrom() {
        return dateFrom.get();
    }

    public StringProperty dateFromProperty() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom.set(dateFrom);
    }

    public String getDateTo() {
        return dateTo.get();
    }

    public StringProperty dateToProperty() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo.set(dateTo);
    }

    @Override
    public String toString() {
        return "QueryModel{" +
                "from=" + from +
                ", to=" + to +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                '}';
    }
}
