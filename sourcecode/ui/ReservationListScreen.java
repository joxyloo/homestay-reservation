package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.TempReservationRecord;
import logic.HomestaySystem;
import logic.*;

public class ReservationListScreen extends JFrame implements ActionListener {
	
	private ReservationOrNewScreen prev;
	private HomestaySystem homestaySys;
	
	private JButton btnBack = new JButton("Back"); 
	
	private ArrayList<Reservation> reservationList;
	private JButton[] btnList;
	
	public ReservationListScreen(ReservationOrNewScreen prev, HomestaySystem homestaySys){
		
		this.prev = prev;
		this.homestaySys = homestaySys;
		
		reservationList = homestaySys.getAllReservation();
		
		btnList = new JButton[reservationList.size()];
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Reservation List");
		setSize(600, 250);
		setLocationRelativeTo(null);
		setVisible(true);

			
		Container pane = getContentPane();
		pane.setBackground(Color.white);
		pane.setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.add(new JLabel("List of reservation:"));
		
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(reservationList.size(), 1));
		
		System.out.println(reservationList.size());
		
		for (int i=0; i<reservationList.size(); i++) {
			Reservation reservation = reservationList.get(i);
			String label = reservation.getReservationID(); 			
			
			JButton btnView = new JButton(label);
			btnView.addActionListener(this);
			btnList[i] = btnView;
			
			JPanel p = new JPanel();
			p.setLayout(new FlowLayout(FlowLayout.LEFT));
			
			p.add(new JLabel((i+1)+"."));
			p.add(btnView);
			
			p2.add(p);
		}
		
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.add(btnBack);
		
		pane.add(p1, BorderLayout.NORTH);
		pane.add(p2, BorderLayout.CENTER);
		pane.add(p3, BorderLayout.SOUTH);
		
		btnBack.addActionListener(this);
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			
	Object obj = e.getSource();
		
		if (obj == btnBack) {
			this.setVisible(false);
			prev.setVisible(true);
		}
		
		else {
			for (int i=0; i<btnList.length; i++) {
				if (obj == btnList[i]) {
					Reservation reservation = reservationList.get(i);
					
					this.setVisible(false);
					new ViewReservationDetailScreen(this, reservation, homestaySys);
				}
			}	
		}
		
	}

}