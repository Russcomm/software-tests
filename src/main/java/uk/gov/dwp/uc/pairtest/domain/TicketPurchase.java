package uk.gov.dwp.uc.pairtest.domain;

/**
 * Public final TicketPurchase class holds the current customer accountId, calculated instance variables, as well as success flags for primary methods being used
 */
public final class TicketPurchase {
    
    /**
     * Private TicketPurchase() constructor
     */
    private TicketPurchase() {
    }
    
    //The accountId for this instance
    public static long accountId = 0L;
    
    //The calculated values for this instance
    public static int numTickets = 0;
    public static int numAdults = 0;
    public static int numChildren = 0;
    public static int numInfants = 0;
    public static int numSeats = 0;
    public static int totalToPay = 0;
    
    //The successful processes for this instance
    public static boolean accountIdOK = false;
    public static boolean ticketTypeRequestsOK = false;
    public static boolean calculateTicketPurchaseOK = false;
    public static boolean ticketsPurchaseOK = false;
    public static boolean makePaymentOK = false;
    public static boolean reserveSeatsOK = false;
    
}
