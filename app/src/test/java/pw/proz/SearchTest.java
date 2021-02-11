package pw.proz;

import com.amadeus.resources.FlightOfferSearch;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pw.proz.api.Connection;
import pw.proz.utils.exceptions.ConnectionException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@DisplayName("Search test")
public class SearchTest {
    private static String departure;
    private static String departureTime;
    private static String arrival;
    private static String returnTime;
    static private FlightOfferSearch[] flightOfferSearches;

    @BeforeAll
    static void init(){
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        LocalDate oneDayAfterTomorrow = tomorrow.plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        departureTime = tomorrow.format(formatter);
        returnTime = oneDayAfterTomorrow.format(formatter);
        departure = "AVV";
        arrival = "SYD";
    }
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Direct one way flight")
    class OneWay {

        @Test
        @Order(1)
        @DisplayName("Search for one of the most popular flight")
        void searchAVV_SYD_Test() throws ConnectionException {
            flightOfferSearches = Connection.flightOfferSearch(departure, arrival, departureTime, null);
            assertNotNull(flightOfferSearches, "There are no round trip flight from Melbourne to Sydney");
        }

        @Test
        @Order(2)
        @DisplayName("Test search results correctness")
        void searchResultsTest() {
            for (var flightSearch : flightOfferSearches) {
                for (var itinerary : flightSearch.getItineraries()) {
                    
                    for (var segment : itinerary.getSegments()) {
                        String departureResult = segment.getDeparture().getIataCode();
                        assertEquals(departureResult, departure, "departure from query should be the same as " +
                                "in the returned result");
                        String departureTimeResult = segment.getDeparture().getAt().substring(0,10);
                        assertEquals(departureTimeResult, departureTime, "departure dates should be the same");
                        String arrivalResult = segment.getArrival().getIataCode();
                        assertEquals(arrivalResult, arrival, "arrival from query should be the same as" +
                                "in the returned result");

                    }
                }

            }
        }

    }
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Direct round flight")
   class RoundTrip{
       @Test
       @Order(1)
       @DisplayName("Search for one of the most popular flight")
       void searchAVV_SYD_Test() throws ConnectionException {
           flightOfferSearches = Connection.flightOfferSearch(departure, arrival, departureTime, returnTime);
           assertNotNull(flightOfferSearches, "There are no single flight from Melbourne to Sydney");
       }

       @Test
       @Order(2)
       @DisplayName("Test search results correctness")
       void searchResultsTest() {
           for (var flightSearch : flightOfferSearches) {
               var itinerary = flightSearch.getItineraries()[0];

               for (var segment : itinerary.getSegments()) {
                   String departureResult = segment.getDeparture().getIataCode();
                   assertEquals(departureResult, departure, "departure from query should be the same as " +
                           "in the returned result");
                   String departureTimeResult = segment.getDeparture().getAt().substring(0, 10);
                   assertEquals(departureTimeResult, departureTime, "departure dates should be the same");
                   String arrivalResult = segment.getArrival().getIataCode();
                   assertEquals(arrivalResult, arrival, "arrival from query should be the same as" +
                           "in the returned result");

               }
               var itineraryReturn = flightSearch.getItineraries()[1];

               for (var segment : itineraryReturn.getSegments()) {
                   String departureResult = segment.getDeparture().getIataCode();
                   assertEquals(departureResult, arrival, "initial arrival should be the same as" +
                           "return departure");
                   String departureTimeResult = segment.getDeparture().getAt().substring(0, 10);
                   assertEquals(departureTimeResult, returnTime, "return dates should be the same");
                   String arrivalResult = segment.getArrival().getIataCode();
                   assertEquals(arrivalResult, departure, "initial departure should be the same as " +
                           "return arrival");

               }
           }
       }
   }
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("Incorrect data format flight")
   class IncorrectData {
        @ParameterizedTest
        @ValueSource(strings = {"12-10-2021", "10-June-2021", "10/02/2021", "2021/03/12", ""})
        @DisplayName("Should throw wrong format of date connection exception")
        void incorrectDateFormatException(String date){
            Exception exception = assertThrows(ConnectionException.class, () ->
                    Connection.flightOfferSearch(departure, arrival, date, null) );

        }
        @ParameterizedTest
        @ValueSource(strings = {"WAWA", "warszawa", ""})
        @DisplayName("Should throw wrong format of date connection exception")
        void incorrectDeparture(String incorrectDeparture){
            Exception exception = assertThrows(ConnectionException.class, () ->
                    Connection.flightOfferSearch(incorrectDeparture, arrival, departureTime, null) );

        }

   }

}
