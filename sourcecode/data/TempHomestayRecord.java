package data;

public class TempHomestayRecord {
	private String homestayID;
	private String homestayname;
	private String ownerID; //xinyong
	private String address;
	private String price;
	private String type;
	private String photo;
	private boolean availability;

	public TempHomestayRecord(String id, String name, String ownerID,String address, String price, String type, String photo) { //xinyong added ownerID
		homestayID = id;
		homestayname = name;
		this.ownerID = ownerID; //xinyong
		this.address = address;
		this.price = price;
		this.type = type;
		this.photo = photo;
		this.availability = true;
	}

	public String getHomestayID() {
		return homestayID;
	}

	public String getName() {
		return homestayname;
	}

	public String getAddress() {
		return this.address;
	}

	public String getPrice() {
		return this.price;
	}

	public String getType() {
		return this.type;
	}

	public String getPhoto() {
		return this.photo;
	}

	public boolean isAvailable() {
		return this.availability;
	}

	public String getOwnerID() { //xinyong
		return ownerID;
	}
}
