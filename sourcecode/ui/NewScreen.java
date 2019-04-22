package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import data.DatabaseManager;
import data.TempReservationRecord;
import logic.*;

public class NewScreen extends JFrame implements ActionListener {
	

	private JTextField txtReservationID = new JTextField(15);
	private JTextField txtAvailable = new JTextField(15);
	private JTextField txtCustomerID = new JTextField(15);
	private JTextField txtHomestayID = new JTextField(15);
	private JTextField txtMonth = new JPasswordField(15);
	private JTextField txtCustomer;
    
	private JLabel lblCustomerID;
	private JLabel lblPay;
	private JLabel lblAvailability;
	
	private JButton btnBack = new JButton("Back");
	private JButton btnSubmit = new JButton("Submit");
	
	private JComboBox<String> hotelList;
	private JComboBox<String> monthList;
	private JComboBox<String> availableList;
	
	private ReservationOrNewScreen prevScreen;
	private HomestaySystem homestaySys;	
	private Customer customer;	
	
	private ArrayList<Reservation> rList;
	private ArrayList<Homestay> hList;
	
	private String newID;
		
	private DatabaseManager db = new DatabaseManager();
	
	public NewScreen(ReservationOrNewScreen prev, HomestaySystem homestaySys) {
		
		this.prevScreen = prev;
		this.homestaySys = homestaySys;
		
		hList = homestaySys.getAllHomestay();//xy
		rList = homestaySys.getAllReservation(); //newxy
		
		//xy
		int currentCount = rList.size();
		newID = "r00" + (currentCount + 1);
		//
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("New Reservation");
		setSize(900, 250);
		setLocationRelativeTo(null);
		setVisible(true);

		Container pane = getContentPane();
		pane.setBackground(Color.white);
		pane.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(6,2));

		p1.add(new JLabel("Reservation ID: "));
		p1.add(lblCustomerID = new JLabel(newID));//xy
		p1.add(new JLabel("Customer ID: "));
		p1.add(txtCustomer = new JTextField());//xy
			
		
		String[] homestayID = new String[] {"Perfect homestay(H001)", "Villa Meena Homestay(H002)",  "Sunsuria Homestay(H003)", "Sunny Homestay(H004)"};
		hotelList = new JComboBox<>(homestayID);
		p1.add(new JLabel("Homestay ID: "));
		p1.add(hotelList);
		
		
		String[] month = new String[] {"January","February","March","April","May","June","July","August","September","October","November","December"};
		monthList = new JComboBox<>(month);

		p1.add(new JLabel("Month: "));
		p1.add(monthList);
		
		p1.add(new JLabel("Availablity Status: "));
		lblAvailability = new JLabel("Available");
		p1.add(lblAvailability);
		
		p1.add(new JLabel("Status:"));
		p1.add(lblPay = new JLabel("Unpaid"));
		
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
			if (txtCustomer.getText().equals(""))
				
				JOptionPane.showMessageDialog(this, "Please key in customer",
					    "Reservation fail",
					    JOptionPane.ERROR_MESSAGE);
			
			else if (availability.equals("Available") && txtCustomer.getText().equals("") == false ) {
				
				db.isValidCustomer(txtCustomer.getText());
				
				if (db.isValidCustomer(txtCustomer.getText()) == true){
			
				//xy
					Reservation res = new Reservation(newID, txtCustomer.getText(), selectedHomestayID,  selectedMonth);

				rList.add(res);//
				homestaySys.db.addNewReservation(newID, txtCustomer.getText(), selectedHomestayID,  selectedMonth);
				homestaySys.db.getLastReservation().updatePayment();
//				
				JOptionPane.showMessageDialog(this, "Reservation and payment is successful.");
		
				int currentCount = rList.size() + 1;
				newID = "r00" + (currentCount);
				lblCustomerID.setText(newID);	
				lblAvailability.setText(selectedHomestay.getAvailability(selectedMonth,rList));
				
				res.updatePayment();
				lblPay.setText(res.getPaymentStatus());
				}
				else
					JOptionPane.showMessageDialog(this, "Customer does not exist");
				
						}
			}
			
			
		if(obj == btnBack) {
			this.setVisible(false);
			prevScreen.setVisible(true);
		}
		
		//xy
	else if (obj == monthList ){
			
						
			lblAvailability.setText(selectedHomestay.getAvailability(selectedMonth,rList));
			if(lblAvailability.getText().equals("Available"))
				lblPay.setText("Unpaid");
			
			else{ //for unavailable homestay
				
				for(Reservation res : rList){
					if (res.getHomestayID().equals(selectedHomestayID) && res.getMonth().equals(selectedMonth))
						lblPay.setText(res.getPaymentStatus());
				}
			}
		}
		
	else if (obj == hotelList){
	
		lblAvailability.setText(selectedHomestay.getAvailability(selectedMonth,rList));
		
		if(lblAvailability.getText().equals("Available"))
			lblPay.setText("UnPaid");
		
		else{ //for unavailable homestay
			
			for(Reservation res : rList){
				if (res.getHomestayID().equals(selectedHomestayID) && res.getMonth().equals(selectedMonth))
					lblPay.setText(res.getPaymentStatus());
			}
		}
		
	}
		
		
	}
	

}