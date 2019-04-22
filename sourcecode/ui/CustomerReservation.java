package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;

import data.DatabaseManager;
import data.TempReservationRecord;
import logic.Customer;
import logic.Homestay;
import logic.HomestaySystem;
import logic.Reservation;

public class CustomerReservation extends JFrame implements ActionListener{
	private JTextField txtReservationID = new JTextField(15);
	private JTextField txtAvailable = new JTextField(15);
	private JTextField txtCustomerID = new JTextField(15);
	private JTextField txtHomestayID = new JTextField(15);
	private JTextField txtMonth = new JPasswordField(15);
	private JButton btnBack = new JButton("Back");
	private JButton btnSubmit = new JButton("Submit");
	private JComboBox<String> hotelList;
	private JComboBox<String> monthList;
	private CustomerMainMenu prevScreen;
	private HomestaySystem homestaySys;
	private JComboBox<String> availableList;
	
	
	private ArrayList<Reservation> rList;
	private ArrayList<Homestay> hList;
	
	private String newID;
	
	private Customer customer;	
	
	JLabel lblAvailability;
	
	public DatabaseManager db = new DatabaseManager();
	
	private JLabel lblCustomerID;
	 
	    
	public CustomerReservation(CustomerMainMenu prevScreen, HomestaySystem homestaySys, Customer customer) {
		
		this.prevScreen = prevScreen;
		this.homestaySys = homestaySys;
		this.customer = customer;//xy
		hList = homestaySys.getAllHomestay();//xy
		rList = homestaySys.getAllReservation(); //xy
		
		//txtReservationID.setText(HomestaySystem.getReserveID());
		
		//xy
		int currentCount = rList.size();
		newID = "r00" + (currentCount + 1);
		//
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Customer Reservation");
		setSize(900, 250);
		setLocationRelativeTo(null);
		setVisible(true);

		Container pane = getContentPane();
		pane.setBackground(Color.white);
		pane.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(3, 4));

		p1.add(new JLabel("Reservation ID: "));
		//p1.add(txtReservationID);
		p1.add(lblCustomerID = new JLabel(newID));//xy
		p1.add(new JLabel("Customer ID: "));
		//p1.add(txtCustomerID);
		p1.add(new JLabel(customer.getCustomerID()));//xy
		
		
		String[] Available = new String[] {"Available", "Unavailable"};
		//availableList = new JComboBox<>(Available);
		p1.add(new JLabel("Availablity Status: "));
		//p1.add(availableList);
		lblAvailability = new JLabel("Available");
		p1.add(lblAvailability);
		
		
	

		String[] homestayID = new String[] {"Perfect homestay(H001)", "Villa Meena Homestay(H002)",  "Sunsuria Homestay(H003)", "Sunny Homestay(H004)"};
		hotelList = new JComboBox<>(homestayID);
		p1.add(new JLabel("Homestay ID: "));
		p1.add(hotelList);
		
		
		String[] month = new String[] {"January","February","March","April","May","June","July","August","September","October","November","December"};
		monthList = new JComboBox<>(month);

		p1.add(new JLabel("Month: "));
		p1.add(monthList);
		
		JPanel pButton = new JPanel();
		pButton.setLayout(new GridLayout(1, 4));

		pButton.add(btnBack);
		pButton.add(btnSubmit);

		JPanel p2 = new JPanel();
		p2.add(p1);

		pane.add(p2, BorderLayout.CENTER);
		pane.add(pButton, BorderLayout.SOUTH);

		btnBack.addActionListener(this);
		btnSubmit.addActionListener(this);
		monthList.addActionListener(this);
		hotelList.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		//xy
		String selectedMonth = monthList.getSelectedItem().toString();
		int index = hotelList.getSelectedIndex();
		
		Homestay selectedHomestay = hList.get(index);
		String selectedHomestayID = selectedHomestay.getHomestayID();
				
		if(obj == btnSubmit) {
			
			//xy
			String availability = selectedHomestay.getAvailability(selectedMonth,rList);
						
			if (availability.equals("Unavailable")){
				
				JOptionPane.showMessageDialog(this,
					    "This homestay is not available.\nPlease select other homestay or month.",
					    "Reservation fail",
					    JOptionPane.ERROR_MESSAGE);
			}
			else{
			//
				//HomestaySystem.IDnumber += 1;
				//String selectedMonth = (String) monthList.getSelectedItem();
				//String AvailableStatus = "Avalaible";
				
				
				//Reservation res = new Reservation(txtReservationID.getText(), txtCustomerID.getText(), txtHomestayID.getText(),  selectedMonth, AvailableStatus);
			
				//xy
				Reservation res = new Reservation(newID, customer.getCustomerID(), selectedHomestayID,  selectedMonth);
				
//				System.out.println("Reservation ID:" + res.getReservationID());
//				System.out.println("Customer ID:" + res.getCustID());
//				System.out.println("Homestay ID:" + res.getHomestayID());
//				System.out.println("Month:" + res.getMonth());
//				System.out.println("AvailableStatus:" + res.getAvailable());
				
				rList.add(res);
				homestaySys.db.addNewReservation(newID, customer.getCustomerID(), selectedHomestayID,  selectedMonth);
				//db.getAllReservation().add(res);
//				
				JOptionPane.showMessageDialog(this, "Reservation is successful.\n" + res.getDetails());
				
				int currentCount = rList.size();
				newID = "r00" + (currentCount + 1);
				lblCustomerID.setText(newID);	
				lblAvailability.setText(selectedHomestay.getAvailability(selectedMonth,rList));
				//this.setVisible(false);
				//
				//new CustomerReservation(prevScreen, homestaySys, customer);
				//prevScreen.setVisible(true);
						}
			}
			
			
		if(obj == btnBack) {
			this.setVisible(false);
			prevScreen.setVisible(true);
		}
		
		//xy
	else if (obj == monthList ){
			
//			String selectedMonth = monthList.getSelectedItem().toString();
//			int index = hotelList.getSelectedIndex();
//			
//			//String selectedHomestayID = hotelList.getSelectedItem().toString();
//			//Homestay selectedHomestay = homestaySys.getHomestay(selectedHomestayID);
//			Homestay selectedHomestay = hList.get(index);
//			String selectedHomestayID = selectedHomestay.getHomestayID();
						
			lblAvailability.setText(selectedHomestay.getAvailability(selectedMonth,rList));
			
		}
		
	else if (obj == hotelList){
		lblAvailability.setText(selectedHomestay.getAvailability(selectedMonth,rList));
	}
		//
		
	}
}