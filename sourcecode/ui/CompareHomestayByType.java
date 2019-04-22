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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logic.Homestay;
import logic.HomestaySystem;


public class CompareHomestayByType extends JFrame implements ActionListener {

	private CompareHomestayMainMenu prevScreen;
	private HomestaySystem homestaySys;
	private JTextArea text;
	private JTextArea text1;
	private JButton btnSearch = new JButton("Search");
	private JButton btnBack = new JButton("Back");
	JComboBox<String> box1 = new JComboBox();
	JComboBox<String> box2 = new JComboBox();
	
	private JButton btn1 = new JButton("Single Story Terrace House");
	private JButton btn2 = new JButton("Apartment House Semi D");
	private JButton btn3 = new JButton("Double Story Terrace House");
	
	
	private ArrayList<Homestay> homestaylist;
	
	public CompareHomestayByType(CompareHomestayMainMenu prevScreen, HomestaySystem homestaySys) {
		this.prevScreen = prevScreen;
		this.homestaySys=homestaySys;

		homestaylist=homestaySys.getAllHomestay();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Compare Homestay Screen By Type");
		setSize(900, 500);
		setLocationRelativeTo(null);
		setVisible(true);

		Container pane = getContentPane();
		pane.setBackground(Color.white);
		pane.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(0,4));
		JLabel lbl = new JLabel("Homestay 1");
		JLabel lbl2 = new JLabel("Homestay 2");
		p1.add(lbl);
		p1.add(box1);
		p1.add(lbl2);
		p1.add(box2);
		

		JPanel p2 = new JPanel();
		p2.setLayout(new GridLayout(2,4));
		p2.add(btn1);
		p2.add(btn2);
		p2.add(btn3);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout());
		text = new JTextArea(20, 20);
		text.setBackground(Color.white);
		text.setEditable(false);
		text1 = new JTextArea(20, 20);
		text1.setBackground(Color.white);
		text1.setEditable(false);
		p3.add(new JScrollPane(text));
		p3.add(new JScrollPane(text1));
	

		JPanel p4 = new JPanel();
		p4.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p4.add(btnBack);

		pane.add(p1, BorderLayout.NORTH);
		pane.add(p2, BorderLayout.WEST);
		pane.add(p3, BorderLayout.CENTER);
		pane.add(p4, BorderLayout.SOUTH);

		btnBack.addActionListener(this);
		btnSearch.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		box1.addActionListener(this);
		box2.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == btnBack) {
			this.setVisible(false);
			prevScreen.setVisible(true);
		}
		
		else if(obj == btn1) {
			text.setText(null);
			text1.setText(null);
			
			//search for matched type for homestay
			for (Homestay homestay : homestaylist) {
				if (homestay.getType().equals("Single Story Terrace House")) {
					
					box1.addItem(homestay.getName());
					box2.addItem(homestay.getName());
				}
					
			}
		
			
		}
			
		else if(obj == btn2) {
			text.setText(null);
			text1.setText(null);
		
			for (Homestay homestay :  homestaylist) {
				if (homestay.getType().equals("Apartment House Semi D")) {
					box1.addItem(homestay.getName());
					box2.addItem(homestay.getName());
					
				}
					
			}
		}
		
		else if(obj == btn3) {
			text.setText(null);
			text1.setText(null);
	
			for (Homestay homestay :  homestaylist) {
				if (homestay.getType().equals("Double Story Terrace House")) {
					box1.addItem(homestay.getName());
					box2.addItem(homestay.getName());
					
				}
					
			}
		}
		
		else if(obj == box1) {
			Object selected = box1.getSelectedItem();
			
			//get the selected homestay name in combobox and display it details
			for (Homestay homestay :  homestaylist) {
			if(selected.toString().equals(homestay.getName()))
				//text.setText(homestay.getName() + " " + homestay.getHomestayID());
				homestay.getDetails(text);

			}
			
		}
		
		else if(obj == box2) {
			Object selected = box2.getSelectedItem();
			for (Homestay homestay :  homestaylist) {
			if(selected.toString().equals(homestay.getName()))
				homestay.getDetails(text1);

			}
			
		}
		
	}
	
}	
