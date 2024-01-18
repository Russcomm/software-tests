
//Import the TicketTypeRequest class
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;

/**
 * TestPurchaseRequest class is immutable and holds test data for a single purchase request
 */
public final class TestPurchaseRequest {

    //Declare final instance members - test description, accountId and the TicketTypeRequest[]
    private final String description;
    private final long accountId;
    private final TicketTypeRequest[] ticketTypeRequests;
    
    /**
     * TestPurchaseRequest constructor taking arguments for test description, accountId and TicketTypeRequest[]
     * @param description - the test description
     * @param accountId - the customer accountId
     * @param ticketTypeRequests - the customer array of TicketTypeRequests
     */
    public TestPurchaseRequest (String description, long accountId, TicketTypeRequest[] ticketTypeRequests) {
        this.description = description;
        this.accountId = accountId;
        this.ticketTypeRequests = ticketTypeRequests;
    }

    //Public getter for the text description
    public String getDescription () {
        return description;
    }

    //Public getter for the accountId
    public long getAccountId () {
        return accountId;
    }

    //Public getter for the TicketTypeRequest[]
    public TicketTypeRequest[] getTicketTypeRequests () {
        return ticketTypeRequests;
    }
}
