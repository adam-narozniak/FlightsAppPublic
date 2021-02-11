package pw.proz.database.models;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;


public class Query implements BaseModel{

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "From")
    private String from;
    @DatabaseField(columnName = "To")
    private String to;
    @DatabaseField(columnName = "DepartureDate")
    private String departureDate;
    @DatabaseField(columnName = "ArrivalDate")
    private String arrivalDate;
    @ForeignCollectionField(eager=true)
    private ForeignCollection<SingleFlight> singleFlights;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Query(String from, String to, String departureDate, String arrivalDate) {
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public Query() {
    }

    public ForeignCollection<SingleFlight> getSingleFlights() {
        return singleFlights;
    }

    public void setSingleFlights(ForeignCollection<SingleFlight> singleFlights) {
        this.singleFlights = singleFlights;
    }
}
