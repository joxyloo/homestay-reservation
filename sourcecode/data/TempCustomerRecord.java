package data;

public class TempCustomerRecord {
	private String customerID;
	private String name;
	private String password;

	public TempCustomerRecord(String i, String n, String p) {
		customerID = i;
		name = n;
		password = p;
	}

	public String getCustomerID() {
		return customerID;
	}

	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return password;
	}
}
