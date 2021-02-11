package pw.proz.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SingleFlightFx {

    private final StringProperty departure = new SimpleStringProperty();
    private final StringProperty departureTime = new SimpleStringProperty();
    private final StringProperty arrival = new SimpleStringProperty();
    private final StringProperty arrivalTime = new SimpleStringProperty();
    private final StringProperty duration = new SimpleStringProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final SimpleStringProperty currency = new SimpleStringProperty();


    public SingleFlightFx(String departure, String departureTime, String arrival, String arrivalTime, String duration,
                          Double price, String currency) {
        setDeparture(departure);
        setDepartureTime(departureTime);
        setArrival(arrival);
        setArrivalTime(arrivalTime);
        setDuration(duration);
        setPrice(price);
        setCurrency(currency);
    }

    public SingleFlightFx() {
    }

    public String getDepartureTime() {
        return departureTime.get();
    }

    public StringProperty departureTimeProperty() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime.set(departureTime);
    }

    public String getArrivalTime() {
        return arrivalTime.get();
    }

    public StringProperty arrivalTimeProperty() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime.set(arrivalTime);
    }

    public String getDuration() {
        return duration.get();
    }

    public StringProperty durationProperty() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }

    public String getDeparture() {
        return departure.get();
    }

    public StringProperty departureProperty() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure.set(departure);
    }

    public String getArrival() {
        return arrival.get();
    }

    public StringProperty arrivalProperty() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival.set(arrival);
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getCurrency() {
        return currency.get();
    }

    public SimpleStringProperty currencyProperty() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency.set(currency);
    }
}
