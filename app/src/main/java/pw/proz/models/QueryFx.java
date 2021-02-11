package pw.proz.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Used as an instance to display saved queries.
 */
public class QueryFx {
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to = new SimpleStringProperty();
    private final StringProperty dateFrom = new SimpleStringProperty();
    private final StringProperty dateTo = new SimpleStringProperty();

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

    public QueryFx(String from, String to, String dateFrom, String dateTo) {
        setFrom(from);
        setTo(to);
        setDateFrom(dateFrom);
        setDateTo(dateTo);
    }
}
