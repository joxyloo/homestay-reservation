package data;

public class TempReservationRecord {

	private String reservationID;
	private String custID;
	private String homestayID;
	private String month;
	private boolean paymentStatus;//xy change to boolean
	
	public TempReservationRecord(String rID, String cID, String hID, String m) {//xy remove payment status
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
	

	public void setReservationID(String r) {
		this.reservationID = r;
	}
	
	public void setCustID(String c) {
		this.custID = c;
	}
	
	public void setHomestayID(String h) {
		this.homestayID = h;
	}
	
	public void setMonth(String m) {
		this.month = m;
	}
	
	public String getPaymentStatus() {//xy
		String a = "";
		
		if(paymentStatus == true)
			a = "Paid";
		else
			a = "Unpaid";
		return a;
	}
	public String getDetails(){ //xy
		return ("Reservation ID: " + this.getReservationID() + "\nMonth: " + this.getMonth());
	}
	
	public void updatePayment(){
		
		if (this.paymentStatus == false)
			paymentStatus = true;
		
	}

	
	
	
}
