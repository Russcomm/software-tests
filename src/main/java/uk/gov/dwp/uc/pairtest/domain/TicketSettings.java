package uk.gov.dwp.uc.pairtest.domain;

/**
 * Public final TicketSettings class holds the ticket prices for each ticket type, and project rule minimums and maximums
 */
public final class TicketSettings {
    
    /**
     * Private TicketSettings() constructor and static members
     */
    private TicketSettings() {
    }
    
    public static final int ADULT_PRICE = 2000; //In pennies, ie £20.00
    public static final int CHILD_PRICE = 1000; //In pennies, ie £10.00
    public static final int INFANT_PRICE = 0; //In pennies, ie £0.00
    
    public static final int MIN_ACCOUNT_ID = 1;    
    public static final int MAX_TICKETS = 20;
    public static final int MIN_ADULTS = 1;    
    
}
