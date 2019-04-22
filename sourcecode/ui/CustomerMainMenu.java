package ui;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;

import data.TempReservationRecord;
import logic.Owner;
import logic.Customer;
import logic.Homestay;
import logic.HomestaySystem;

public class CustomerMainMenu extends JFrame implements ActionListener {
	private JButton btnSearchHomestay = new JButton("Search Homestay");
	private JButton btnCompareHomestay = new JButton("Compare Homestay");
	private JButton btnReserveHomestay = new JButton("Reserve Homestay");
	private JButton btnBack = new JButton("Back");
	
	private HomestaySystem homestaySys;
	private CustomerLoginForm prev;
	private Customer customer;
	private ArrayList<TempReservationRecord> rList;
	
	public CustomerMainMenu(CustomerLoginForm prev, HomestaySystem homestaySys,  Customer customer) {
		
		this.homestaySys = homestaySys;
		this.prev = prev;
		this.customer = customer;
		this.rList = rList;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Customer Main Menu");
		setSize(600, 250);
		setLocationRelativeTo(null);
		setVisible(true);

		Container pane = getContentPane();
		pane.setBackground(Color.white);
		pane.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.add(new JLabel("Welcome back " + customer.getName() + "!"));

		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(4, 1, 10, 10));
		p2.add(btnSearchHomestay);
		p2.add(btnCompareHomestay);
		p2.add(btnReserveHomestay);
		p2.add(btnBack);	
	
		JPanel p3 = new JPanel();
		p3.add(p2);
		pane.add(p3);
		
	
		btnSearchHomestay.addActionListener(this);
		btnCompareHomestay.addActionListener(this);
		btnReserveHomestay.addActionListener(this);
		btnBack.addActionListener(this);

		
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == btnSearchHomestay) {
			this.setVisible(false);
			new SearchHomestayMainMenu(this, homestaySys);
		}

		else if (obj == btnCompareHomestay) {
			this.setVisible(false);
			new CompareHomestayMainMenu(this, homestaySys); // alex
		}

		else if (obj == btnReserveHomestay) {
			this.setVisible(false);
			new CustomerReservation(this, homestaySys, customer); // not implemented in this stage
		}
		
		else if (obj == btnBack){
			this.setVisible(false);
			prev.setVisible(true);

		}
		
		
	}
}
