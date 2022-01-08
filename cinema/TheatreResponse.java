package cinema;

public class TheatreResponse {
	
	private String responseNarrative = "";
	private boolean userInputIsValid = true;
	private int ticketPriceForSeat = 10;
	private int[] seatCoordinates = {0,0};
	private int[] seatRowIndex = {0,0};
	private boolean seatPurchased = false;
	private boolean seatIsAvailable = true;
	
	public TheatreResponse(int[] seatCoordinates, int[] seatRowIndex)
	{
		this.seatCoordinates = seatCoordinates;
		this.seatRowIndex = seatRowIndex;
	}

	
	public boolean isSeatPurchased() {
		return seatPurchased;
	}
	
	public void setSeatPurchased(boolean seatPurchased) {
		this.seatPurchased = seatPurchased;
	}
	

	
	public boolean isSeatIsAvailable() {
		return seatIsAvailable;
	}
	
	public void setSeatIsAvailable(boolean seatIsAvailable) {
		this.seatIsAvailable = seatIsAvailable;
	}
	
	public String getResponseNarrative() {
		return responseNarrative;
	}
	
	public void setResponseNarrative(String responseNarrative) {
		this.responseNarrative = responseNarrative;
	}
	
	public boolean isUserInputIsValid() {
		return userInputIsValid;
	}
	
	public void setUserInputIsValid(boolean userInputIsValid) {
		this.userInputIsValid = userInputIsValid;
	}
	
	public int getTicketPriceForSeat() {
		return ticketPriceForSeat;
	}
	
	public void setTicketPriceForSeat(int ticketPriceForSeat) {
		this.ticketPriceForSeat = ticketPriceForSeat;
	}
	
	public int[] getSeatCoordinates() {
		return seatCoordinates;
	}
	
	public void setSeatCoordinates(int[] seatCoordinates) {
		this.seatCoordinates = seatCoordinates;
	}
	
	public int[] getSeatRowIndex() {
		return seatRowIndex;
	}
	
	public void setSeatRowIndex(int[] seatRowIndex) {
		this.seatRowIndex = seatRowIndex;
	}
	
}
