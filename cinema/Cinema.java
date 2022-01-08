package cinema;

import java.util.Scanner;

public class Cinema {
    
    public Theatre theatreOne;
    public CinemaState currentState;
    public boolean exitSelected = false;
    public Scanner scanner;
    
    
    public static void main(String[] args)
    {
        
        Cinema myCinema = new Cinema();
        myCinema.handleState();
    }
    public Cinema()
    {
        this.scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int seats = scanner.nextInt();
        
        this.theatreOne = new Theatre(rows, seats);
        this.currentState = CinemaState.MENU;
        
    }
    public void handleState()
    {
        while (this.currentState != CinemaState.EXIT)
        {
            switch (this.currentState)
            {
                case MENU:
                    switch (this.menuHandler())
                    {
                        case 1:
                            this.currentState = CinemaState.SHOW_SEATS;
                            break;
                        case 2:
                            this.currentState = CinemaState.PURCHASE_TICKET;
                            break;
                        case 3:
                            this.currentState = CinemaState.SHOW_STATS;
                            break;
                        case 0:
                            this.currentState = CinemaState.EXIT;
                            break;
                        default:
                            this.currentState = CinemaState.MENU;
                            break;
                    }
                    break;
                case SHOW_SEATS:
                    this.theatreOne.printSeatingChart();
                    this.currentState = CinemaState.MENU;
                    break;
                case PURCHASE_TICKET:
                    seatCheckHandler();
                    this.currentState = CinemaState.MENU;
                    break;
                case SHOW_STATS:
                    showStatisticsHandler();
                    this.currentState = CinemaState.MENU;
                    break;
                case EXIT:
                    this.currentState = CinemaState.EXIT;
                    break;
                default:
                    this.currentState = CinemaState.MENU;
                    break;
            }
        }
    }
    public void seatCheckHandler()
    {
        System.out.println("Enter a row number: ");
        int rowInput = this.scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatInput = this.scanner.nextInt();
        
        TheatreResponse response = theatreOne.seatPurchaseController(rowInput, seatInput);
        if (response.isUserInputIsValid() == false)
        {
            System.out.println(response.getResponseNarrative());
            return;
        }
        if (response.isSeatIsAvailable() == false)
        {
            // Seat entered is already taken,
            // Notify user and give them the opportunity to purchase
            // a new seat.
            System.out.println(response.getResponseNarrative());
            this.seatCheckHandler();
            return;
        }
        if (response.isSeatPurchased() == true)
        {
            System.out.println(response.getResponseNarrative());
            return;
        }
    }
    public void showStatisticsHandler()
    {
        this.theatreOne.getSalesStatistics();
    }
    public int menuHandler()
    {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        return this.scanner.nextInt();
    }
    
    public enum CinemaState {
        EXIT,
        MENU,
        SHOW_SEATS,
        PURCHASE_TICKET,
        SHOW_STATS
    }
    
}
