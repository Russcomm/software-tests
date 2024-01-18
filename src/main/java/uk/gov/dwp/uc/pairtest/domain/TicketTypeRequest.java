package uk.gov.dwp.uc.pairtest.domain;

/**
 * Public final TicketTypeRequest class is immutable and holds the ticket type and number of specified ticket types
 */
public final class TicketTypeRequest {

    private final Type type;
    private final int noOfTickets;
    
    /**
     * TicketTypeRequest constructor that accepts the ticket type and number of tickets of the specified type
     * @param type - is an enum ticket type, either ADULT, CHILD or INFANT
     * @param noOfTickets - is an integer of the number of tickets of a specified type
     */
    public TicketTypeRequest(Type type, int noOfTickets) {
        this.type = type;
        this.noOfTickets = noOfTickets;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public Type getTicketType() {
        return type;
    }

    public enum Type {
        ADULT, CHILD , INFANT
    }

}
