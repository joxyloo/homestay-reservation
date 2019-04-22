package data;

import java.util.ArrayList;
import java.util.Scanner;

import ui.SearchHomestayByName;

public class DatabaseManager {

	private TempOwnerRecord owner;
	private TempCustomerRecord customer;
	private TempReservationRecord reservation;
	private ArrayList<TempHomestayRecord> homestaylist = new ArrayList<TempHomestayRecord>();
	private ArrayList<TempCustomerRecord> customerlist = new ArrayList<TempCustomerRecord>();
	private ArrayList<TempReservationRecord> reservationlist = new ArrayList<TempReservationRecord>();//xy

	public DatabaseManager() {
		owner = new TempOwnerRecord("o001", "Jack", "jack123");
		customer = new TempCustomerRecord("c001", "Zhang", "zhang123");
		reservation = new TempReservationRecord("r001", "c001", "H004","December");
		
		//xinyong added ownerID in constructor to all homestay
		//alex delete the"RM" from price ( needed ) !
		//alex change the homestay4 type to Single Story Terrace House for display result for the same type
		TempHomestayRecord homestay1 = new TempHomestayRecord("H001", "Perfect homestay","o001","Jalan Aman 2, Taman Kajang Utama, 43000 Bandar baru Bangi, Selangor.", "250","Single Story Terrace House", "img/H001.jpg");
		TempHomestayRecord homestay2 = new TempHomestayRecord("H002", "Villa Meena Homestay","o001","Jalan Kajang Impian 2/9, Taman Kajang Impian, 43000 Bandar Baru Bangi, Selangor.", "450","Apartment House Semi D", "img/H002.jpg");
		TempHomestayRecord homestay3 = new TempHomestayRecord("H003", "Sunsuria Homestay","o001","Jalan 15/1a, Seksyen 15, 43650 Bandar Baru Bangi, Selangor.", "350", "Double Story Terrace House","img/H003.jpg");
		TempHomestayRecord homestay4 = new TempHomestayRecord("H004", "Sunny Homestay","o001","Jalan 20/3b, Seksyen 20, 43650 Bandar Baru Bangi, Selangor.", "150", "Single Story Terrace House", "img/H004.jpg");

		homestaylist.add(homestay1);
		homestaylist.add(homestay2);
		homestaylist.add(homestay3);
		homestaylist.add(homestay4);
		
		customerlist.add(customer);
		
		reservationlist.add(reservation);
	}

	public boolean verifyOwner(String id, String pwd) {
		if (owner.getOwnerID().equals(id) && owner.getPassword().equals(pwd))
			return true;

		return false;
	}

	public TempOwnerRecord getOwner(String id, String pwd) {
		if (owner.getOwnerID().equals(id) && owner.getPassword().equals(pwd))
			return owner;

		return null;
	}

	public boolean verifyCustomer(String id, String pwd) {

		if (customer.getCustomerID().equals(id) && customer.getPassword().equals(pwd))
			return true;

		return false;
	}
	

	public boolean isValidCustomer(String id) { //newxy

		for (TempCustomerRecord c : customerlist){
			
			if (id.equals(c.getCustomerID()))
				return true;
			else 
				return false;
		}
		return (Boolean) null;
	}
	

	public TempCustomerRecord getCustomer(String id, String pwd) {
		if (customer.getCustomerID().equals(id) && customer.getPassword().equals(pwd))
			return customer;

		return null;
	}

	public TempHomestayRecord getHomestay(String homestayID, String name, String address, String price, String type,
			String photo) {
		for (TempHomestayRecord homestay : homestaylist) {
			if (homestay.getName().equals(homestayID) && homestay.equals(name) && homestay.equals(address)
					&& homestay.equals(price) && homestay.equals(type) && homestay.equals(photo))
				return homestay;
		}
		return null;
	}

	public ArrayList<TempHomestayRecord> getAllHomestay() {
		return homestaylist;
	}

	public TempHomestayRecord getHomestay(String homestayID) {//xy
		// TODO Auto-generated method stub
		for (TempHomestayRecord homestay : homestaylist) {
			if (homestay.getHomestayID().equals(homestayID))
				return homestay;
		}
		return null;
	}

	public ArrayList<TempCustomerRecord> getAllCustomer() {
		// TODO Auto-generated method stub
		return customerlist;
	}

	public ArrayList<TempReservationRecord> getAllReservation(){
		// TODO Auto-generated method stub
		return reservationlist;
	}

	public void addNewReservation(String newID, String text, String selectedHomestayID, String selectedMonth) {
		// TODO Auto-generated method stub
		TempReservationRecord t = new TempReservationRecord(newID, text, selectedHomestayID, selectedMonth);
		reservationlist.add(t);
	}
	public TempReservationRecord getLastReservation(){
		return this.getAllReservation().get(this.getAllReservation().size()-1);
	}

}
