package ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import data.TempReservationRecord;
import logic.Customer;
import logic.Homestay;
import logic.HomestaySystem;

public class CustomerLoginForm extends JFrame implements ActionListener {
	private JTextField txtName = new JTextField(15);
	private JPasswordField txtPwd = new JPasswordField(15);
	private JButton btnBack = new JButton("Back");
	private JButton btnSubmit = new JButton("Submit");

	private MainScreen prevScreen;
	private HomestaySystem homestaySys;

		
	public CustomerLoginForm(MainScreen prevScreen, HomestaySystem homestaySys) {
		this.prevScreen = prevScreen;
		this.homestaySys = homestaySys;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Customer Login Form");
		setSize(600, 250);
		setLocationRelativeTo(null);
		setVisible(true);

		Container pane = getContentPane();
		pane.setBackground(Color.white);
		pane.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		p1.setLayout(new GridLayout(3, 2));

		p1.add(new JLabel("Username: "));
		p1.add(txtName);
		p1.add(new JLabel("Password: "));
		p1.add(txtPwd);
		p1.add(btnBack);
		p1.add(btnSubmit);

		JPanel p2 = new JPanel();
		p2.add(p1);

		pane.add(p2, BorderLayout.CENTER);

		btnBack.addActionListener(this);
		btnSubmit.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == btnBack) {
			this.setVisible(false);
			prevScreen.setVisible(true);
		}

		else {
			String username = txtName.getText();
			String password = txtPwd.getText();

			if (homestaySys.isValidCustomer(username, password)) {
				Customer theCustomer = homestaySys.getCustomer(username, password);

				this.setVisible(false);
				new CustomerMainMenu(this, homestaySys, theCustomer);
			}

			else
				JOptionPane.showMessageDialog(this, "Wrong ID or password", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}