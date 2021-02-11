package pw.proz.api;


import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.FlightOfferSearch;
import pw.proz.utils.exceptions.ConnectionException;

/**
 * This class allows establish connection and manage requests and responses of Amadeus API.
 */
public class Connection {
    private static final String API_KEY = "FIXME";
    private static final String API_SECRET = "FIXME";
    private static final Amadeus client = Amadeus
            .builder(API_KEY, API_SECRET)
            .build();


    /**
     * Method to get array of Flight Offers based on searching criteria - GET.
     * It answers the question: What are the cheapest flights from X to Y on Z?"
     */
    public static FlightOfferSearch[] flightOfferSearch(String origin, String destination, String departureDate,
                                                        String arrivalDate) throws ConnectionException {
        if (arrivalDate == null || arrivalDate.isEmpty()) {
            try {
                return client.shopping.flightOffersSearch.get(
                        Params.with("originLocationCode", origin)
                                .and("destinationLocationCode", destination)
                                .and("departureDate", departureDate)
                                .and("adults", 1)
                                .and("max", 3));
            } catch (ResponseException e ) {
                throw new ConnectionException(e.getMessage());
            }

        } else {
            try {
                return client.shopping.flightOffersSearch.get(
                        Params.with("originLocationCode", origin)
                                .and("destinationLocationCode", destination)
                                .and("departureDate", departureDate)
                                .and("returnDate", arrivalDate)
                                .and("adults", 1)
                                .and("max", 3));
            } catch (ResponseException e) {
                throw new ConnectionException(e.getMessage());
            }

        }


    }
}


