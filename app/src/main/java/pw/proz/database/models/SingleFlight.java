package pw.proz.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


/**
 * A class represents a single flight.
 *
 */
@DatabaseTable(tableName = "Itineraries")
public class SingleFlight implements BaseModel{
    public SingleFlight() {
    }

    /**
     * Itinerary constructor.
     */
    public SingleFlight(String departure, String departureTime, String arrival, String arrivalTime,
                        String duration, double price, String currency, Query query) {
        this.departure = departure;
        this.departureTime = departureTime;
        this.arrival = arrival;
        this.arrivalTime = arrivalTime;
        this.duration = duration;
        this.price = price;
        this.currency = currency;
        this.query = query;
    }

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "Departure")
    private String departure;

    @DatabaseField(columnName = "departureTime")
    private String departureTime;

    @DatabaseField(columnName = "Arrival")
    private String arrival;

    @DatabaseField(columnName = "arrivalTime")
    private String arrivalTime;
    /**
     *  Duration time in ISO8601 format
     */
    @DatabaseField(columnName = "Duration")
    private String duration;

    @DatabaseField(columnName = "Price")
    private double price;

    @DatabaseField
    private String currency;

    @DatabaseField(foreign = true, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Query query;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
