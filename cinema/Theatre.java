package cinema;

public class Theatre {
	
	private String[][] seatingChart;
	private final int numOfRows;
	private final int numOfSeatsPerRow;
	private boolean isLarge = false;
	private int numOfTicketsSold = 0;

	
	private final int totalNumberOfSeats;
	private int totalSales = 0;
	
	public Theatre(int numOfRows, int numOfSeatsPerRow)
	{
		this.seatingChart = this.buildSeatingChart(numOfRows, numOfSeatsPerRow);
		this.numOfRows = numOfRows;
		this.numOfSeatsPerRow = numOfSeatsPerRow;
		if (numOfRows * numOfSeatsPerRow >= 60)
		{
			this.isLarge = true;
		}
		this.totalNumberOfSeats = this.getNumOfSeatsPerRow() * this.getNumOfRows();
	}
	public String[][] buildSeatingChart(int numOfRows, int numOfSeatsPerRow)
	{
		String[][] cinemaSeatingChart = new String[numOfRows][numOfSeatsPerRow];
		
		for (int rowIndex = 0; rowIndex < numOfRows; rowIndex++)
		{
			for (int seatIndex = 0; seatIndex < numOfSeatsPerRow; seatIndex++)
			{
				cinemaSeatingChart[rowIndex][seatIndex] = "S";
			}
		}
		return cinemaSeatingChart;
	}
	public int getPriceOfSeatAt(int row)
	{
		if(isLarge)
		{
			int frontHalfOfRows = this.numOfRows / 2;
			if (row <= frontHalfOfRows)
			{
				return 10;
			} else {
				return 8;
			}
		}
		return 10;
	}
	public boolean isSeatAvailable(int rowIndex, int seatIndex)
	{
		String seatAvailabilityCode = this.getSeatingChart()[rowIndex][seatIndex];
		if ("S".equalsIgnoreCase(seatAvailabilityCode))
		{
			return true;
		} else {
			return false;
		}
	}
	public int totalTheatreRevenue(){
		if (!this.isLarge)
		{
			return 10 * this.getNumOfRows() * this.getNumOfSeatsPerRow();
		} else {
			int frontHalf = this.getNumOfRows() / 2;
			int frontHalfRevenue = frontHalf * this.getNumOfSeatsPerRow() * 10;
			int remainderHalf = this.getNumOfRows() - frontHalf;
			int backHalfRevenue = remainderHalf * this.getNumOfSeatsPerRow() * 8;
			return backHalfRevenue + frontHalfRevenue;
		}
	}
	public boolean checkInputValidity(int rowIndex, int seatIndex)
	{
		if (rowIndex < 0 || rowIndex > this.getNumOfRows() - 1)
		{
			return false;
		}
		if (seatIndex < 0 || seatIndex > this.getNumOfSeatsPerRow() - 1)
		{
			return false;
		}
		return true;
	}
	public TheatreResponse seatPurchaseController(int row, int seat){
		
		int[] userCoordinates = {row, seat};
		
		// get row and seat indexes from user input.
		int rowIndex = row - 1;
		int seatIndex = seat - 1;
		
		int[] indexCoordinates = {rowIndex, seatIndex};
		// Build response with coordinates and indexes
		
		TheatreResponse response = new TheatreResponse(userCoordinates, indexCoordinates);
		
		// Validate input from user
		if (!this.checkInputValidity(rowIndex, seatIndex))
		{
			// User input is invalid.
			response.setUserInputIsValid(false);
			response.setResponseNarrative("Wrong input!");
			return response;
		}
		
		// Check if seat is available
		if (!isSeatAvailable(rowIndex, seatIndex))
		{
			// Seat is not available
			response.setSeatIsAvailable(false);
			response.setResponseNarrative("That ticket has already been purchased!");
			return response;
		}
		// If input is valid, and the seat is available, get the price of the ticket.
		int ticketPrice = this.getPriceOfSeatAt(row);
		response.setTicketPriceForSeat(ticketPrice);
		
		// Indicate seat as purchased in database.
		this.purchaseSeatAt(rowIndex, seatIndex);
		response.setSeatPurchased(true);
		
		
		// Increase sales by ticketPrice amount
		int tempSales = this.getTotalSales();
		tempSales += ticketPrice;
		this.setTotalSales(tempSales);
		
		response.setResponseNarrative(String.format("Ticket price: $%s", response.getTicketPriceForSeat()));
		return response;
	}
	public void purchaseSeatAt(int rowIndex, int seatIndex)
	{
		//Increase number of tickets sold
		int tempTicketsSold = this.getNumOfTicketsSold();
		tempTicketsSold++;
		this.setNumOfTicketsSold(tempTicketsSold);
		
		// Indicate purchased by a B in the array.
		this.seatingChart[rowIndex][seatIndex] = "B";
	}
	public void printSeatingChart()
	{
		System.out.println("Cinema: ");
		System.out.print("  ");
		for (int i = 0; i < this.numOfSeatsPerRow; i++)
		{
			System.out.print(i+1 + " ");
		}
		System.out.println("");
		for (int j = 0; j < numOfRows; j++ )
		{
			System.out.print(j+1 + " ");
			for (int k = 0; k < numOfSeatsPerRow; k++)
			{
				System.out.print(seatingChart[j][k] + " ");
			}
			System.out.println("");
		}
	}
	public float getPercentTicketsSold(){
		float numOfTicketsSold = this.getNumOfTicketsSold();
		float totalSeats = this.getTotalNumberOfSeats();

		float percentTicketsSold = (numOfTicketsSold / totalSeats) * 100;
		return percentTicketsSold;
	}
	public void getSalesStatistics()
	{
		float percentageOfSeatsSold = this.getPercentTicketsSold();
		int currentSales = this.getTotalSales();
		int totalRevenuePossible = this.totalTheatreRevenue();
		int numberOfTicketsSold = this.getNumOfTicketsSold();
		System.out.println(String.format("Number of purchased tickets: %s", numberOfTicketsSold));
		System.out.println(String.format("Percentage: %.2f%%", percentageOfSeatsSold));
		System.out.println(String.format("Current Income: $%s", currentSales));
		System.out.println(String.format("Total income: $%s", totalRevenuePossible));
	}
	public int getNumOfRows()
	{
		return this.numOfRows;
	}
	public int getNumOfSeatsPerRow()
	{
		return this.numOfSeatsPerRow;
	}
	public String[][] getSeatingChart()
	{
		return this.seatingChart;
	}
	public int getNumOfTicketsSold() {
		return numOfTicketsSold;
	}
	
	public void setNumOfTicketsSold(int numOfTicketsSold) {
		this.numOfTicketsSold = numOfTicketsSold;
	}
	
	public int getTotalSales() {
		return totalSales;
	}
	
	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}
	
	public int getTotalNumberOfSeats() {
		return totalNumberOfSeats;
	}
}
