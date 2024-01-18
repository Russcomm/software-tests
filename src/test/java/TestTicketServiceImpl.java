
//Using Junit 4 as required, but Assert is depreciated
import static junit.framework.Assert.assertTrue;
import junit.framework.AssertionFailedError;
import org.junit.Test;

//Ticket Service main class
import uk.gov.dwp.uc.pairtest.TicketServiceImpl;

//Ticket Servcie domain classes
import uk.gov.dwp.uc.pairtest.domain.TicketTypeRequest;
import static uk.gov.dwp.uc.pairtest.domain.TicketPurchase.*;

/**
 * Public TestTicketServiceImpl class is the primary test class for this project
 */
public class TestTicketServiceImpl {
    
    //Instantiate the test data class for this test
    private final TestPurchaseRequest[] arrData = new TestDataTicketService().getTestData();
    
    //Instantiate the test class for this test
    private final TicketServiceImpl objTest = new TicketServiceImpl();
    
    /**
     * Public main() method can be used to start the testing process for the target project 
     * @param args
     */
    public static void main(String[] args){
        new TestTicketServiceImpl().addition();
    }
    
    /**
     * public addition() method starts the test and can be called by the main() method
     * Takes no parameters and returns void
     */
    @Test
    public void addition() {
        
        try {
            
            //Print start the tests
            System.out.println("");
            System.out.println("Start Tests...");
            
            //Incrementor for print purposes
            int i = 0;
            
            //Loop the test data array
            for (TestPurchaseRequest objData : arrData) {
                
                try {
                    
                    //Check if the data object is null
                    if (objData == null) {
                        System.out.println("");
                        System.out.println("Test Data Object " + i + ": Null");
                        System.out.println("");
                        continue;
                    }
                    
                    //Print the test description
                    System.out.println("");
                    System.out.println(objData.getDescription());
                    
                    //Print the test input data
                    System.out.println("");
                    System.out.println("Input Data:");
                    System.out.println("AccountId: " + objData.getAccountId());
                    System.out.println("TicketTypeRequest[]: " + (objData.getTicketTypeRequests() == null ? "Null" : "Not Null"));
                    
                    //Check if the TicketTypeRequests[] is not null and print more test input data
                    if (objData.getTicketTypeRequests() != null)
                        for (TicketTypeRequest objReq : objData.getTicketTypeRequests())
                            System.out.println("Ticket: " + objReq.getTicketType().name() + ", Num: " + objReq.getNoOfTickets());
                    
                    //Call the test object with the test input data
                    objTest.purchaseTickets(objData.getAccountId(),objData.getTicketTypeRequests());
                    
                    //Print the output data from the current TicketPurchase class
                    System.out.println("");
                    System.out.println("Output Data:");
                    System.out.println("Adults: " + numAdults + ", Children: " + numChildren + ", Infants: " + numInfants + ", Seats: " + numSeats + ", Payment (pence): " + totalToPay);
                    
                    //Perform a series of Asserts relating to the methods being tested, from the current TicketPurchase class
                    assertTrue("Account Id is NOT OK", accountIdOK);
                    assertTrue("Ticket Request is NOT OK", ticketTypeRequestsOK);
                    assertTrue("Tickets Calculated is NOT OK", calculateTicketPurchaseOK);
                    assertTrue("Tickets Purchased is NOT OK", ticketsPurchaseOK);
                    assertTrue("Made Payment is NOT OK", makePaymentOK);
                    assertTrue("Seats Reserved is NOT OK", reserveSeatsOK);
                
                } catch (AssertionFailedError ex) {
                    //Catch an AssertionFailedError (failed Assert above) and print the basic class information
                    System.out.println("Assertion Failed: " + ex.toString());
                } catch (Exception ex) {
                    //Catch an Exception and print the basic class information
                    System.out.println("Exception Encountered: " + ex.toString());
                }

                //If one more data object, print a continue message
                if (i < (arrData.length - 1)) {
                    System.out.println("");
                    System.out.println("Continue testing...");
                }
                
                //Add one to the incrementor
                i++;
            }
            
            //Print the end of the tests
            System.out.println("");
            System.out.println("End of tests");
            System.out.println("");
            
        } catch (Exception ex) {           
            //Catch an Exception and print the basic class information
            System.out.println("");
            System.out.println(ex.toString());
            System.out.println("");
        }
    }
    
}