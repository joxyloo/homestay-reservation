package data;

public class TempOwnerRecord {
	private String ownerID;
	private String name;
	private String password;

	public TempOwnerRecord(String i, String n, String p) {
		ownerID = i;
		name = n;
		password = p;
	}

	public String getOwnerID() {
		return ownerID;
	}

	public String getName() {
		return this.name;
	}

	public String getPassword() {
		return password;
	}
}
