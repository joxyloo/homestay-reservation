package ui;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import logic.HomestaySystem;
import logic.Customer;

public class MainScreen extends JFrame implements ActionListener {
	private JButton btnCustomer = new JButton("Customer");
	private JButton btnOwner = new JButton("Owner");

	private HomestaySystem homestaySys = new HomestaySystem("Bandar Baru Bangi Area Homestays");

	public MainScreen() {
		Container pane = getContentPane();
		pane.setBackground(Color.white);
		pane.setLayout(new BorderLayout());

		JPanel p1 = new JPanel();
		p1.add(new JLabel(homestaySys.getName()));

		ImageIcon icon = new ImageIcon("img/homestay.png");
		JLabel label = new JLabel(icon);

		JPanel p2 = new JPanel();
		p2.add(btnCustomer);
		p2.add(btnOwner);

		pane.add(p1, BorderLayout.NORTH);
		pane.add(label, BorderLayout.CENTER);
		pane.add(p2, BorderLayout.SOUTH);

		btnCustomer.addActionListener(this);
		btnOwner.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == btnCustomer) {
			this.setVisible(false);
			new CustomerLoginForm(this, homestaySys);
		}

		if (obj == btnOwner) {
			this.setVisible(false);
			new OwnerLoginForm(this, homestaySys);
		}
	}

	public static void main(String[] args) {
		MainScreen frame = new MainScreen();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Main Screen");
		frame.setSize(600, 250);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
