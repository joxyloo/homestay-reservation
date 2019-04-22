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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logic.Homestay;
import logic.HomestaySystem;


public class CompareHomestayMainMenu extends JFrame implements ActionListener {


	private JButton btnByPrice = new JButton("By Price");
	private JButton btnByType = new JButton("By Type");
	private JButton btnBack = new JButton("Back"); 
	
	private CustomerMainMenu prevScreen;
	private HomestaySystem homestaySys;
	
	public CompareHomestayMainMenu(CustomerMainMenu prevScreen, HomestaySystem homestaySys) {
		this.prevScreen=prevScreen;
		this.homestaySys=homestaySys;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Search Homestay Main Menu");
		setSize(600, 250);
		setLocationRelativeTo(null);
		setVisible(true);
		
		Container pane = getContentPane();
		pane.setBackground(Color.white);
		pane.setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));
		p1.add(new JLabel("Search homestay by : "));
		
		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(4, 1, 10, 10));
		p2.add(btnByPrice);
		p2.add(btnByType);
		
		JPanel p3 = new JPanel();
		p3.add(p2);	
		
		JPanel p4=new JPanel();
		p4.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p4.add(btnBack);
		
		pane.add(p1, BorderLayout.NORTH);
		pane.add(p3, BorderLayout.CENTER);
		pane.add(p4,BorderLayout.SOUTH);
		
		btnByPrice.addActionListener(this);
		btnByType.addActionListener(this);
		
		btnBack.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if (obj == btnBack) {
			this.setVisible(false);
			prevScreen.setVisible(true);
		}
		
		
		else if (obj == btnByPrice) {
			this.setVisible(false);
			new CompareHomestayByPrice(this, homestaySys);
		}
		
		else if (obj == btnByType) {
			this.setVisible(false);
			new CompareHomestayByType(this, homestaySys);
			
		}
		
	}

}