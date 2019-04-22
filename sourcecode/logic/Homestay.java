package logic;

import java.util.ArrayList;

import javax.swing.JTextArea;

import data.DatabaseManager;
import data.TempReservationRecord;

public class Homestay {
	private String homestayID;
	public String homestayname;
	private String ownerID; //xinyong
	private String address;
	private String price;
	private String type;
	private String photo;
	private boolean availability;
	private boolean paymentStatus; //xinyong
	
	public DatabaseManager db = new DatabaseManager(); //xinyong
	
	public Homestay(String id, String name, String ownerID, String address, String price, String type, String photo) { //xinyong added ownerID
		homestayID = id;
		homestayname = name;
		this.ownerID = ownerID; //xinyong
		this.address = address;
		this.price = price;
		this.type = type;
		this.photo = photo;
		
		this.availability = true;
		
		//reservationList = db.getAllReservation();
	}

	public String getHomestayID() {
		return homestayID;
	}

	public String getName() {
		return homestayname;
	}

	public String getAddress() {
		return this.address;
	}

	public String getPrice() {
		return this.price;
	}

	public String getType() {
		return this.type;
	}

	public String getPhoto() {
		return this.photo;
	}

	public boolean isAvailable() {
		return this.availability;
	}

	public String getPaymentStatus() {//xinyong
		// TODO Auto-generated method stub
		String a = "";
		
		if (this.paymentStatus == true)
			a = "Paid";
		else
			a = "Unpaid";
		
		return a;
	}
	
	public void updatePayment(){//xinyong
		
		if(this.paymentStatus == true)
			paymentStatus = false;
		else
			paymentStatus = true;

	}
	
	public void updateAvailability(){//xinyong
		
			
		if(this.availability == false)
			availability = true;
		else
			availability = false;

	}
	
	public void getDetails(JTextArea txt){//xinyong , alex add "RM" to price
		txt.setText("Homestay ID : " + this.getHomestayID() + "\n" + "Name : " + this.getName() + "\n" + "Price : RM" + this.getPrice()+ "\n"+ "Address: " + this.getAddress() + "\nStatus: " + this.getPaymentStatus() + "\n");
	}

	public String getAvailability(String selectedMonth, ArrayList<Reservation> reservationList) { //xinyong
		// TODO Auto-generated method stub
		String a = "Available";
		
		for (Reservation r : reservationList){
			if (this.getHomestayID().equals(r.getHomestayID())){
				if(selectedMonth.equals(r.getMonth())){
					availability = false;
					a = "Unavailable";
					break;				
					}
				
			}
		}
		return a;
	}
	
}
