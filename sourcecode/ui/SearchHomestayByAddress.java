package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
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

public class SearchHomestayByAddress extends JFrame implements ActionListener {

	private SearchHomestayMainMenu prevScreen;
	private HomestaySystem homestaySys;
	private JTextArea text;
	private JButton btnSearch = new JButton("Search");
	private JButton btnBack = new JButton("Back");
	private JTextField txtfd;

	private ArrayList<Homestay> homestaylist;

	public SearchHomestayByAddress(SearchHomestayMainMenu prevScreen, HomestaySystem homestaySys) {
		this.prevScreen = prevScreen;
		this.homestaySys = homestaySys;

		homestaylist = homestaySys.getAllHomestay();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Search Homestay Screen");
		setSize(600,500);
		setLocationRelativeTo(null);
		setVisible(true);

		Container pane = getContentPane();
		pane.setBackground(Color.white);
		pane.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		p1.add(new JLabel("Please enter partial/full homestay's address"));
		txtfd = new JTextField(10);
		p1.add(txtfd);
		p1.add(btnSearch);

		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		text = new JTextArea(50, 50);
		text.setBackground(Color.white);
		text.setEditable(false);
		p2.add(text);

		JPanel p3 = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p3.add(btnBack);

		pane.add(p1, BorderLayout.NORTH);
		pane.add(p2, BorderLayout.CENTER);
		pane.add(p3, BorderLayout.SOUTH);

		btnBack.addActionListener(this);
		btnSearch.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == btnBack) {
			this.setVisible(false);
			prevScreen.setVisible(true);
		}

		else if (obj == btnSearch) {
			text.setText("");
			String input = txtfd.getText().toLowerCase().trim();
//			txtfd.setEditable(false);
//			btnSearch.setEnabled(false);
			int i = 1;
			
			for (Homestay homestay : homestaySys.getAllHomestay()) {
				if (homestay.getAddress().toLowerCase().contains(input)) {
					text.append(i + ". Name : " + homestay.getName()+" ("+homestay.getHomestayID()+") "+"\n" +"   Address : "+homestay.getAddress()+ "\n" +"   Price : "+homestay.getPrice()+ "\n" +"   Type : "+homestay.getType()+"\n"+"\n");
					i++;
				}
				// else
				// text.append("\n" + "Searching cannot found !");
			}
		}
	}
}