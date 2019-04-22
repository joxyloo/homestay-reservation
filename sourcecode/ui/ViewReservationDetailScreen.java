package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import data.DatabaseManager;
import data.TempReservationRecord;
import logic.Homestay;
import logic.HomestaySystem;
import logic.Reservation;

public class ViewReservationDetailScreen extends JFrame implements ActionListener{
	
	private ReservationListScreen prevScreen;
	
	private Reservation reservation;
	
	private JButton btnBack = new JButton("Back");
	private JButton btnPay = new JButton("Pay");
	
	private JTextArea txt;

	private HomestaySystem homestaySys;
	private Homestay homestay;
	
	private String name;
	private String address;
	
		public ViewReservationDetailScreen(ReservationListScreen prevScreen, Reservation reservation, HomestaySystem homestaySys){
		
		this.prevScreen = prevScreen;
		this.reservation = reservation;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Reservation Details");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setVisible(true);
		
		Container pane = getContentPane();
		pane.setBackground(Color.white);
		pane.setLayout(new BorderLayout());
		
		
		JPanel p1 = new JPanel();
		p1.setLayout(new BorderLayout());

		
		JPanel p2 = new JPanel();
		
		homestay = homestaySys.getHomestay(reservation.getHomestayID());
		name = homestay.getName();
		address = homestay.getAddress();
		txt = new JTextArea("Payment Status: " + reservation.getPaymentStatus() + "\nCustomer ID: " + reservation.getCustID() + "\nHomestay Name: " + name + "\nAddress: " + address + "\nReserved month: " + reservation.getMonth()); 
		p1.add(txt, BorderLayout.SOUTH);
		
		ImageIcon icon = new ImageIcon("img/" + homestay.getHomestayID()+ ".jpg");
		JLabel image = new JLabel(icon);
		p1.add(image, BorderLayout.CENTER);
			
		p2.add(btnPay);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.add(btnBack);
		
		JPanel p4 = new JPanel();
		p4.setLayout(new BorderLayout());
		p4.add(p1, BorderLayout.CENTER);
		p4.add(p2,BorderLayout.SOUTH);
		
		pane.add(p4, BorderLayout.CENTER);
		pane.add(p3, BorderLayout.SOUTH);
		
		btnBack.addActionListener(this);
		btnPay.addActionListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object obj = e.getSource();
		
		if( obj == btnBack){
			this.setVisible(false);
			prevScreen.setVisible(true);
		}
		else{
			reservation.updatePayment();
			txt.setText("Payment Status: " + reservation.getPaymentStatus() + "\nCustomer ID: " + reservation.getCustID() + "\nHomestay Name: " + name + "\nAddress: " + address + "\nReserved month: " + reservation.getMonth());
			
		}
	}

}
