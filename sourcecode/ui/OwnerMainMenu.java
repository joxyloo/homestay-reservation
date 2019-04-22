package ui;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import logic.HomestaySystem;
import logic.Owner;
import logic.Reservation;

public class OwnerMainMenu extends JFrame implements ActionListener {

	private JButton btnRecordPayment = new JButton("Record Payment");
	private JButton btnBack = new JButton("Back");

	private HomestaySystem homestaySys;
	private OwnerLoginForm prev;
	
	private ArrayList<Reservation> reservationList;//

	public OwnerMainMenu(OwnerLoginForm prev, HomestaySystem homestaySys) {
		this.homestaySys = homestaySys;
		this.prev = prev;
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Owner Main Menu");
		setSize(600, 250);
		setLocationRelativeTo(null);
		setVisible(true);

		Container pane = getContentPane();
		pane.setBackground(Color.white);
		pane.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.add(new JLabel("Welcome back!"));

		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(2, 1, 10, 10));
		p2.add(btnRecordPayment);
		p2.add(btnBack);

		JPanel p3 = new JPanel();
		p3.add(p2);

		pane.add(p1, BorderLayout.NORTH);
		pane.add(p3, BorderLayout.CENTER);

		btnRecordPayment.addActionListener(this);
		btnBack.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == btnRecordPayment) { //xinyong
			this.setVisible(false);
			new ReservationOrNewScreen(this, homestaySys); 
		}
		else{
			this.setVisible(false);
			prev.setVisible(true);
		}

	}
}
