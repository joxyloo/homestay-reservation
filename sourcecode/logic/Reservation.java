package logic;

public class Reservation {
	
	private String reservationID;
	private String custID;
	private String homestayID;
	private String month;
	private boolean paymentStatus;//xy change to boolean
	
	public Reservation(String rID, String cID, String hID, String m) { //xy remove payment status
		this.reservationID = rID;
		this.custID = cID;
		this.homestayID= hID;
		this.month = m;
		paymentStatus = false;//xy
	}
	
	public String getReservationID() {
		return reservationID;
	}
	
	public String getCustID() {
		return custID;
	}
	
	public String getHomestayID() {
		return homestayID;
	}
	
	public String getMonth() {
		return month;
	}
	
	public String getPaymentStatus() {
		
		String a = "";
		if(paymentStatus == true)
			a = "Paid";
		else
			a = "Unpaid";
		return a;
	}
	
	public void updatePayment(){
		
		if (this.paymentStatus == false)
			paymentStatus = true;
		
	}
	
	public String getDetails(){ //xy
		return ("Reservation ID: " + this.getReservationID() + "\nMonth: " + this.getMonth());
	}
	
		
}
