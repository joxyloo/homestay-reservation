package logic;

public class Customer {
	private String customerID;
	private String name;
	private String password;

	public Customer(String i, String n, String p) {
		customerID = i;
		name = n;
		password = p;
	}

	public String getName() {
		return name;
	}

	public String getCustomerID() {
		// TODO Auto-generated method stub
		return customerID;
	}
}
