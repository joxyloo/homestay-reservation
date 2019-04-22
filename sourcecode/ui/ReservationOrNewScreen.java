package ui;

import java.awt.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import logic.HomestaySystem;
import logic.Owner;

public class ReservationOrNewScreen extends JFrame implements ActionListener {
	
	private OwnerMainMenu prev;
	private HomestaySystem homestaySys;
	
	private JButton btnNew;
	private JButton btnReservation;
	private JButton btnBack;
	
	private JPanel p1;
	
	public ReservationOrNewScreen(OwnerMainMenu prev, HomestaySystem homestaySys){
		this.prev = prev;
		this.homestaySys = homestaySys;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Owner Main Menu");
		setSize(600, 250);
		setLocationRelativeTo(null);
		setVisible(true);

		Container pane = getContentPane();
		pane.setBackground(Color.white);
		pane.setLayout(new BorderLayout());
		
		p1 = new JPanel();
		p1.setLayout(new GridLayout(2, 1, 10, 10));
		
		btnNew = new JButton("New Reservation");
		btnReservation = new JButton("Existing Reservation");
		btnBack = new JButton("Back");
		
		p1.add(btnNew);
		p1.add(btnReservation);
		
		
		JPanel p2 = new JPanel();
		p2.add(p1);
		
		JPanel p3 = new JPanel();
		p3.add(btnBack);
				
		pane.add(p2,BorderLayout.CENTER);
		pane.add(p3,BorderLayout.SOUTH);
		
		btnNew.addActionListener(this);
		btnReservation.addActionListener(this);
		btnBack.addActionListener(this);


		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if (obj == btnNew){
			this.setVisible(false);
			new NewScreen(this,homestaySys);
		}
		else if (obj == btnReservation){
			this.setVisible(false);
			new ReservationListScreen(this,homestaySys);
		}
		else if (obj == btnBack){
			this.setVisible(false);
			prev.setVisible(true);
		}
	}
	
	

}
