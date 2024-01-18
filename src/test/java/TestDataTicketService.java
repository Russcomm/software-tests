
//Import the TicketTypeRequest class
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

//Static import the TicketTypeRequest Type and TicketSettings constants
import static uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest.Type.*;
import static uk.gov.dwp.uc.pairtest.domain.TicketSettings.*;

/**
 * Public final TestDataTicketService class simply holds the getTestData() method
 */
public final class TestDataTicketService {

    /**
     * public getTestData() method builds and returns predetermined test data for each input data scenario - based on the project rules provided
     * @return TestPurchaseRequest[] - returns an array of TestPurchaseRequest objects
     */
    public TestPurchaseRequest[] getTestData() {
        try {
            return new TestPurchaseRequest[] {
                //Test for null TicketTypeRequest[]
                new TestPurchaseRequest(
                    "Test for TicketTypeRequest[] is null",
                    1, //Valid accountId
                    null //null TicketTypeRequest[]
                ),
                
                //Test for invalid accountId >= minimum account id
                new TestPurchaseRequest(
                    "Test for invalid account id - AccountId: 0 >= minimum AccountId: " + MIN_ACCOUNT_ID,
                    0, //Invalid accountId
                    new TicketTypeRequest[]{ //Valid TicketTypeRequest[]
                        new TicketTypeRequest(ADULT, 1),
                        new TicketTypeRequest(CHILD, 1),
                        new TicketTypeRequest(INFANT, 1)
                    }
                ),
                
                //Test for number of adults < minimum adults
                new TestPurchaseRequest(
                    "Test for minimum adults - number of Adults: 0 < minimum Adults: " + MIN_ADULTS,
                    1, //Valid accountId
                    new TicketTypeRequest[]{ //Not enough adults
                        new TicketTypeRequest(ADULT, 0)
                    }
                ),
                
                //Test for number of adults >= number of infants
                new TestPurchaseRequest(
                    "Test for not enough adults - number of Adults: 1 >= number of Infants: 2",
                    1, //Valid accountId
                    new TicketTypeRequest[]{ //Too many infants for adults
                        new TicketTypeRequest(ADULT, 1),
                        new TicketTypeRequest(INFANT, 2)
                    }
                ),
                
                //Test for number of tickets > maximum tickets
                new TestPurchaseRequest(
                    "Test for too many tickets - number of Tickets: 21 > maximum Tickets: " + MAX_TICKETS,
                    1, //Valid accountId
                    new TicketTypeRequest[]{ //Too many tickets
                        new TicketTypeRequest(ADULT, 19),
                        new TicketTypeRequest(CHILD, 1),
                        new TicketTypeRequest(INFANT, 1)
                    }
                ),
                
                //Test for valid purchase request - Adults: 2, Children: 2, Infants: 1, Seats: 4, Payment (pence): 6000
                new TestPurchaseRequest(
                    "Test for valid purchase request - Adults: 2, Children: 2, Infants: 1, Seats: 4, Payment (pence): 6000",
                    1, //Valid accountId
                    new TicketTypeRequest[]{ //Valid TicketTypeRequest[]
                        new TicketTypeRequest(ADULT, 2),
                        new TicketTypeRequest(CHILD, 2),
                        new TicketTypeRequest(INFANT, 1)
                    }
                )
            };
        } catch (Exception ex) {
            return null;
        }
    }
}