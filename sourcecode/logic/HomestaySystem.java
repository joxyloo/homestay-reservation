package logic;

import java.util.ArrayList;
import java.util.Scanner;

import data.DatabaseManager;
import data.TempOwnerRecord;
import data.TempReservationRecord;
import data.TempCustomerRecord;
import data.TempHomestayRecord;
import ui.CustomerMainMenu;

public class HomestaySystem {
	private String name;
	private Owner owner;
	private Customer customer;
	private Homestay homestay;
	private Reservation reservation;//xy

	public DatabaseManager db = new DatabaseManager();
	
//	private ArrayList<Reservation> ReservationList;//newxy

	public HomestaySystem(String n) {
		name = n;
//		tempReservationList = this.getAllReservation();//newxy- record from database
//		ReservationList = this.getAllReservation();
	}

	public String getName() {
		return this.name;
	}

	public Owner getOwner(String id, String pwd) {
		TempOwnerRecord tempOwner = db.getOwner(id, pwd);
		owner = new Owner(tempOwner.getOwnerID(), tempOwner.getName(), tempOwner.getPassword());
		return owner;
	}

	public boolean isValidOwner(String id, String pwd) {
		return db.verifyOwner(id, pwd);
	}

	public Customer getCustomer(String id, String pwd) {
		TempCustomerRecord tempCustomer = db.getCustomer(id, pwd);
		customer = new Customer(tempCustomer.getCustomerID(), tempCustomer.getName(), tempCustomer.getPassword());
		return customer;
	}

	public boolean isValidCustomer(String id, String pwd) {
		return db.verifyCustomer(id, pwd);
	}

	public ArrayList<Homestay> getAllHomestay() {
		ArrayList<Homestay> homestaylist = new ArrayList<Homestay>();

		ArrayList<TempHomestayRecord> homestayrecord = db.getAllHomestay();

		for (TempHomestayRecord homestayRecord : homestayrecord) { //xinyong added ownerID
			homestay = new Homestay(homestayRecord.getHomestayID(), homestayRecord.getName(),homestayRecord.getOwnerID(),
					homestayRecord.getAddress(), homestayRecord.getPrice(), homestayRecord.getType(),
					homestayRecord.getPhoto());

			homestaylist.add(homestay);
		}
		return homestaylist;
	}

	public Homestay getHomestay(String homestayID, String name, String address, String price, String type,
			String photo) { //xinyong added ownerID
		TempHomestayRecord tempHomestay = db.getHomestay(homestayID, name, address, price, type, photo);
		homestay = new Homestay(tempHomestay.getHomestayID(), tempHomestay.getName(), tempHomestay.getOwnerID(),tempHomestay.getAddress(),
				tempHomestay.getPrice(), tempHomestay.getType(), tempHomestay.getPhoto());
		return homestay;
	}
	
	public Homestay getHomestay(String homestayID) { //xinyong 
		TempHomestayRecord tempHomestay = db.getHomestay(homestayID);
		homestay = new Homestay(tempHomestay.getHomestayID(), tempHomestay.getName(), tempHomestay.getOwnerID(),tempHomestay.getAddress(),
				tempHomestay.getPrice(), tempHomestay.getType(), tempHomestay.getPhoto());
		return homestay;
	}
	
	public ArrayList<Customer> getAllCustomer() { //xinyong
		ArrayList<Customer> Customerlist = new ArrayList<Customer>();

		ArrayList<TempCustomerRecord> Customerrecord = db.getAllCustomer();

		for (TempCustomerRecord CustomerRecord : Customerrecord) { //xinyong added ownerID
			customer = new Customer(CustomerRecord.getCustomerID(), CustomerRecord.getName(),CustomerRecord.getPassword());

			Customerlist.add(customer);
		}
		return Customerlist;
	}
	
	public ArrayList<Reservation> getAllReservation() { //xinyong
		ArrayList<Reservation> Reservationlist = new ArrayList<Reservation>();

		ArrayList<TempReservationRecord> Reservationrecord = db.getAllReservation();

		for (TempReservationRecord reservationRecord : Reservationrecord) { //xinyong added ownerID
			
			reservation = new Reservation(reservationRecord.getReservationID(), reservationRecord.getCustID(),reservationRecord.getHomestayID(),reservationRecord.getMonth());

			
			if(reservationRecord.getPaymentStatus().equals("Paid")){
				reservation.updatePayment();
			}
			

			Reservationlist.add(reservation);
		}
		return Reservationlist;
	}
	
	
	public void addReservation(Reservation t){ //newxy
		this.getAllReservation().add(t);
	}
}
