package uk.gov.dwp.uc.pairtest;

//Import the TicketPurchase, TicketTypeRequest and InvalidPurchaseException classes
import uk.gov.dwp.uc.pairtest.domain.TicketPurchase;
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import uk.gov.dwp.uc.pairtest.exception.InvalidPurchaseException;

//Import the thirdparty TicketPaymentServiceImpl and SeatReservationServiceImpl classes
import thirdparty.paymentgateway.TicketPaymentServiceImpl;
import thirdparty.seatbooking.SeatReservationServiceImpl;

//Static import the public members of the TicketSettings and TicketPurchase classes
import static uk.gov.dwp.uc.pairtest.domain.TicketSettings.*;
import static uk.gov.dwp.uc.pairtest.domain.TicketPurchase.*;

/**
 * Public TicketServiceImpl class implements the TicketService interface and overrides the purchaseTickets() single abstract method
 */
public class TicketServiceImpl implements TicketService {
    
    /**
     * Public purchaseTickets() method is an override of the TicketService interface single abstract method
     * @param accountId - customer accountId
     * @param ticketTypeRequests - customer TickTypeRequest[] array
     */
    @Override
    public void purchaseTickets(Long accountId, TicketTypeRequest... ticketTypeRequests) throws InvalidPurchaseException {
        try {
            //Call the checkAccountId() method
            checkAccountId(accountId);
            
            //Call the checkTicketTypeRequests() method
            checkTicketTypeRequests(ticketTypeRequests);
            
            //Call the calculateTicketPurchase() method
            calculateTicketPurchase(ticketTypeRequests);
            
            //Call the checkTicketsPurchase() method
            checkTicketsPurchase();
            
            //Call the makePayment() method
            makePayment(accountId);
            
            //Call the reserveSeats() method
            reserveSeats(accountId);
        } catch (Exception ex) {
            throw new InvalidPurchaseException();
        }    
    }

    //The checkAccountId() method determines if accountId is valid and informs the TicketPurchase class
    private void checkAccountId (Long accountId) {
        try {
            TicketPurchase.accountId = accountId;
            if (accountId >= MIN_ACCOUNT_ID)
                accountIdOK = true;
            else    
                throw new InvalidPurchaseException();
        } catch (Exception ex) {
            throw new InvalidPurchaseException();
        }
    }
    
    //The checkTicketTypeRequests() method determines if the TicketTypeRequest[] is viable and informs the TicketPurchase class
    private void checkTicketTypeRequests (TicketTypeRequest... ticketTypeRequests) {
        try {
            if (ticketTypeRequests == null || ticketTypeRequests.length == 0)
                throw new InvalidPurchaseException();
            else
                ticketTypeRequestsOK = true;
        } catch (Exception ex) {
            throw new InvalidPurchaseException();
        }
    }
    
    //The calculateTicketPurchase() method loops the TicketTypeRequest[], calculates the variables and informs the TicketPurchase class
    private void calculateTicketPurchase (TicketTypeRequest... ticketTypeRequests) {
        try {
            int numTypeTickets = 0;
            
            numTickets = 0;
            numAdults = 0;
            numChildren = 0;
            numInfants = 0;
            numSeats = 0;
            totalToPay = 0;
            
            for (TicketTypeRequest ticketTypeRequest : ticketTypeRequests) {
                
                if (ticketTypeRequest == null) {
                    throw new InvalidPurchaseException();
                }
                    
                numTypeTickets = ticketTypeRequest.getNoOfTickets();
                
                switch (ticketTypeRequest.getTicketType()) {
                    case ADULT -> {
                        numTickets = numTickets + numTypeTickets;
                        numAdults = numAdults + numTypeTickets;
                        numSeats = numSeats + numTypeTickets;
                        totalToPay = totalToPay + (ADULT_PRICE * numTypeTickets);
                    }
                    case CHILD -> {
                        numTickets = numTickets + numTypeTickets;
                        numChildren = numChildren + numTypeTickets;
                        numSeats = numSeats + numTypeTickets;
                        totalToPay = totalToPay + (CHILD_PRICE * numTypeTickets);
                    }
                    case INFANT -> {
                        numTickets = numTickets + numTypeTickets;
                        numInfants = numInfants + numTypeTickets;
                        totalToPay = totalToPay + (INFANT_PRICE * numTypeTickets);
                    }
                    default -> {
                        throw new InvalidPurchaseException();
                    }
                }
            }

            calculateTicketPurchaseOK = true;
        } catch (Exception ex) {
            throw new InvalidPurchaseException();
        }
    }
    
    //The checkTicketsPurchase() method determines if the calculated variables are valis and informs the TicketPurchase class
    private void checkTicketsPurchase () {
        try {
            if (numTickets >= MIN_ADULTS & numTickets <= MAX_TICKETS & numAdults >= MIN_ADULTS & numAdults >= numInfants)
                ticketsPurchaseOK = true; 
            else
                throw new InvalidPurchaseException();
        } catch (Exception ex) {
            throw new InvalidPurchaseException();
        }   
    }
    
    //The makePayment() performs the payment with the accountId and totalToPay in pence, then informs the TicketPurchase class
    private void makePayment (long accountId) {
        try {
            TicketPaymentServiceImpl ticketPaymentService = new TicketPaymentServiceImpl();

            if (ticketPaymentService == null)
                throw new InvalidPurchaseException();

            ticketPaymentService.makePayment(accountId, totalToPay);
            
            makePaymentOK = true;
        } catch (Exception ex) {        
            throw new InvalidPurchaseException();        
        }
    }
    
    //The reserveSeats() performs the seat reservation with the accountId and calculated number of seats, then informs the TicketPurchase class
    private void reserveSeats (long accountId) {
        try {            
            SeatReservationServiceImpl seatReservationService = new SeatReservationServiceImpl();
            
            if (seatReservationService == null)
                throw new InvalidPurchaseException();
            
            seatReservationService.reserveSeat(accountId, numSeats);
            
            reserveSeatsOK = true;
        } catch (Exception ex) {        
            throw new InvalidPurchaseException();        
        }
    }
}
